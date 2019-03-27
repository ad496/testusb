package com.company.data;

import com.company.data.document.FiscalDocument;
import com.company.data.session.SessionHeader;
import com.company.data.session.SessionPackage;
import com.company.data.support.CRC16;
import com.company.data.support.Int16LE;
import com.company.data.support.MessageOutputStream;
import com.company.data.tag.FiscalDocumentMapper;
import com.company.data.tag.FiscalDocumentTag;
import com.company.data.tag.FiscalDocumentType;
import com.google.common.io.BaseEncoding;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;


public class KktDataParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(KktDataParser.class);

    private final static int RECEIPT_BODY_DEFAULT_LENGTH = 88;
    private final static int RECEIPT_HEADER_LENGTH = 4;
    private final static int[] RECEIPT_FIELD_BODY_LENGTH_INDEX = {2, 3};

    /**
     * Кодирование SessionPackage в бинарные данные ККТ
     *
     * @param msg     объект SessionPackage
     * @param kktData пакет бинарных данных ККТ
     */
    public static void encodeSessionPackage(SessionPackage msg, ByteBuf kktData) {
        LOGGER.info("KktDataParser: Encoding session package");

        int length = msg.getContainer().length;

        CRC16 crc16 = new CRC16();
        crc16.update(msg.getHeader().getSignature());
        crc16.update(msg.getHeader().getSProtocolVersion());
        crc16.update(msg.getHeader().getAProtocolVersion());
        crc16.update(msg.getHeader().getFnNumber());
        crc16.update(new Int16LE(length).bytes());
        crc16.update(new Int16LE(0).bytes());
        crc16.update(msg.getContainer());

        kktData.writeBytes(msg.getHeader().getSignature());
        kktData.writeBytes(msg.getHeader().getSProtocolVersion());
        kktData.writeBytes(msg.getHeader().getAProtocolVersion());
        kktData.writeBytes(msg.getHeader().getFnNumber());
        kktData.writeBytes(new Int16LE(length).bytes());
        kktData.writeBytes(new byte[]{0x14, 0x00}); // flags
        kktData.writeBytes(new Int16LE(crc16.checksum()).bytes());
        kktData.writeBytes(msg.getContainer());
    }

    /**
     * Формирование SessionPackage из бинарных данных ККТ
     *
     * @param dataServerId идентификатор DataServer, который получил данные KKT
     * @param clientId     идентификатор соединения с KKT
     * @param kktData      пакет бинарных данных ККТ
     * @return данные ККТ сконвертированные в объект SessionPackage
     */
    public static SessionPackage decodeSessionPackage(String dataServerId, String clientId, ByteBuf kktData) {
        LOGGER.info("KktDataParser: Decoding session package");

        byte[] signature = new byte[4];
        byte[] sProtocolVersion = new byte[2];
        byte[] aProtocolVersion = new byte[2];
        byte[] fnNumber = new byte[16];
        byte[] lengthField = new byte[2];

        kktData.readBytes(signature);
        kktData.readBytes(sProtocolVersion);
        kktData.readBytes(aProtocolVersion);
        kktData.readBytes(fnNumber);
        kktData.readBytes(lengthField);

        int flags = kktData.readUnsignedShort();
        int checksum = kktData.readUnsignedShort();

        int sessionContainerLength = kktData.readableBytes();
        byte[] sessionContainer = new byte[sessionContainerLength];
        kktData.readBytes(sessionContainer);

        SessionHeader sessionHeader = new SessionHeader(signature, sProtocolVersion, aProtocolVersion, fnNumber);

        return new SessionPackage(
                clientId,
                dataServerId,
                sessionHeader,
                sessionContainer
        );
    }

    /**
     * Формирование объекта FiscalDocument
     *
     * @param data   массив байт, полученный путем Base64 декодирования объекта, полученного от метода ПКЗ /message/unwrap (UnwrappedData)
     * @param header заголовок данных документа
     * @return данные ККТ преобразованные в FiscalDocument
     * @throws ParseException         в случае ошибок создания объекта из массива тэгов
     * @throws IOException            в случае ошибок преобразования rawData в массив тэгов
     * @throws InstantiationException в случае ошибки нахождения класса документа запрощенного типа
     * @throws IllegalAccessException в случае ошибок создания объекта из массива тэгов
     */
    public static FiscalDocument buildFiscalDocument(byte[] data, byte[] header) throws ParseException, IOException, InstantiationException, IllegalAccessException {
        LOGGER.info("Reading FiscalDocument from unwrapped by HSM kkt data");

        FiscalDocumentMapper fiscalDocumentBuilder = new FiscalDocumentMapper(
                new String[]{
                        "fdserver.kkt.data.document"
                }
        );

        return fiscalDocumentBuilder.read(data, header);
    }

    /**
     * Формирование квитанции (ответа оператора)
     *
     * @param ofdInn               ИНН ОФД
     * @param ofdFiscalSign        фискальный признак ОФД
     * @param ofdResponseCode      код ответа оператора ОФД
     * @param createDateTime       дата и время формирования квитанции в секундах
     * @param kktFiscalDriveNumber номер фискального накопителя ККТ
     * @param documentNumber       номер фискального документа
     * @return массив байт с данными квитанции
     * @throws IOException при ошибке преобразования данных
     */
    public static byte[] buildOperatorConfirmation(
            String ofdInn,
            String ofdFiscalSign,
            byte ofdResponseCode,
            int createDateTime,
            String kktFiscalDriveNumber,
            int documentNumber
    ) throws IOException {
        BaseEncoding base16 = BaseEncoding.base16();

        MessageOutputStream receiptData = MessageOutputStream.defaultSize();

        //заголовок документа
        // код документа
        receiptData.writeShort(FiscalDocumentType.OPERATOR_CONFIRMATION);
        // длина документа
        receiptData.writeShort(RECEIPT_BODY_DEFAULT_LENGTH);

        //тело документа
        // Код ответа ОФД отдельно
        receiptData.writeShort(FiscalDocumentTag.OFD_RESPONSE_CODE.getId()); //1022 //tag
        receiptData.writeShort(1); //length
        receiptData.write(new byte[]{ofdResponseCode}); //data

        //дата, время
        receiptData.writeShort(FiscalDocumentTag.DATE_TIME.getId()); //1012
        receiptData.writeShort(4);
        receiptData.writeInt(createDateTime);

        // ИНН ОФД
        receiptData.writeShort(FiscalDocumentTag.OFD_INN.getId()); //1017
        receiptData.writeShort(12);
        receiptData.write(ofdInn.getBytes(Charset.forName("CP866")));

        //заводской номер фискального накопителя
        receiptData.writeShort(FiscalDocumentTag.FISCAL_DRIVE_NUMBER.getId()); //1041
        receiptData.writeShort(kktFiscalDriveNumber.length());
        receiptData.write(kktFiscalDriveNumber.getBytes(Charset.forName("CP866")));

        //номер фискального документа
        receiptData.writeShort(FiscalDocumentTag.FISCAL_DOCUMENT_NUMBER.getId()); //1040
        receiptData.writeShort(4);
        receiptData.writeInt(documentNumber);

        //сообщение оператора для ФН
        receiptData.writeShort(FiscalDocumentTag.OFD_MESSAGES_FOR_FISCAL_DRIVE.getId()); //1068
        receiptData.writeShort(5);

        // ==> текст сообщения: Код ответа ОФД
        receiptData.writeShort(FiscalDocumentTag.OFD_RESPONSE_CODE.getId()); //1022
        receiptData.writeShort(1);
        receiptData.write(new byte[]{ofdResponseCode});

        //фискальный признак оператора
        receiptData.writeShort(FiscalDocumentTag.OFD_FISCAL_SIGN.getId()); //1078
        receiptData.writeShort(18);
        receiptData.write(base16.decode(ofdFiscalSign));

        byte[] receiptBytes = receiptData.toByteArray();
        int receiptBodySize = receiptBytes.length - RECEIPT_HEADER_LENGTH;
        receiptBytes[RECEIPT_FIELD_BODY_LENGTH_INDEX[0]] = (byte) (0xFF & receiptBodySize);
        receiptBytes[RECEIPT_FIELD_BODY_LENGTH_INDEX[1]] = (byte) (0xFF & (receiptBodySize >> 8));

        return receiptBytes;
    }
}
