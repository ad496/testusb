package com.company.data.document;


import com.company.data.tag.FiscalDocumentTag;
import com.company.data.tag.FiscalDocumentType;
import com.company.data.tag.annotation.DocumentType;
import com.company.data.tag.annotation.PropertyTag;
import com.google.common.io.BaseEncoding;


import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

/**
 * Фискальный документ - Подтверждение оператора фискальных данных
 */
@DocumentType(value = FiscalDocumentType.OPERATOR_CONFIRMATION, protocolVersion = {"1.0", "1.1"})
public class OperatorConfirmation extends BaseFiscalDocument {

    /**
     * ИНН ОФД
     * tag: 1017
     */
    @PropertyTag(value = FiscalDocumentTag.OFD_INN, protocolVersion = {"1.0", "1.1"})
    private String ofdInn;

    /**
     * фискальный признак оператора
     * tag: 1078
     */
    private String ofdFiscalSign;

    @PropertyTag(value = FiscalDocumentTag.OFD_FISCAL_SIGN, protocolVersion = {"1.0", "1.1"})
    public void setFiscalSignFromTags(ByteBuffer buffer) {
        byte[] arr = buffer.array();
        if (arr.length >= 6) {
            ofdFiscalSign = String.valueOf(ByteBuffer.wrap(Arrays.copyOfRange(arr, 2, arr.length)).getInt() & 0xffffffffl);
        } else {
            ofdFiscalSign = BaseEncoding.base16().encode(arr);
        }
    }

    /**
     * сообщение оператора для ФН
     * tag: 1068
     */
    @PropertyTag(value = FiscalDocumentTag.OFD_MESSAGES_FOR_FISCAL_DRIVE, listItemTag = FiscalDocumentTag.OFD_RESPONSE_CODE, protocolVersion = {"1.0", "1.1"})
    private List<String> ofdMessagesForFiscalDrive;

    /**
     * сообщение оператора для ККТ
     * tag: 1067
     */
    @PropertyTag(value = FiscalDocumentTag.OFD_MESSAGES_FOR_KKT, listItemTag = FiscalDocumentTag.MESSAGE_TYPE_STRING, protocolVersion = {"1.0", "1.1"})
    private List<String> ofdMessagesForKkt;

    /**
     * код ответа ОФД
     * tag: 1022
     */
    @PropertyTag(value = FiscalDocumentTag.OFD_RESPONSE_CODE, protocolVersion = {"1.0", "1.1"})
    private String ofdResponseCode;


    public String getOfdInn() {
        return ofdInn;
    }

    public void setOfdInn(String ofdInn) {
        this.ofdInn = ofdInn;
    }

    public String getOfdFiscalSign() {
        return ofdFiscalSign;
    }

    public void setOfdFiscalSign(String ofdFiscalSign) {
        this.ofdFiscalSign = ofdFiscalSign;
    }

    public List<String> getOfdMessagesForFiscalDrive() {
        return ofdMessagesForFiscalDrive;
    }

    public void setOfdMessagesForFiscalDrive(List<String> ofdMessagesForFiscalDrive) {
        this.ofdMessagesForFiscalDrive = ofdMessagesForFiscalDrive;
    }

    public List<String> getOfdMessagesForKkt() {
        return ofdMessagesForKkt;
    }

    public void setOfdMessagesForKkt(List<String> ofdMessagesForKkt) {
        this.ofdMessagesForKkt = ofdMessagesForKkt;
    }

    public String getOfdResponseCode() {
        return ofdResponseCode;
    }

    public void setOfdResponseCode(String ofdResponseCode) {
        this.ofdResponseCode = ofdResponseCode;
    }
}
