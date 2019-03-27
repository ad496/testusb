package com.company.data.document;


import com.company.data.tag.FiscalDocumentTag;
import com.company.data.tag.annotation.PropertyTag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.io.BaseEncoding;


import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

/**
 * Фискальный документ - Базовый класс
 */
public abstract class BaseFiscalDocument implements FiscalDocument {

    /**
     * Версия протокола
     */
    protected String protocolVersion;

    /**
     * Код документа
     */
    protected int documentCode;

    /**
     * Наименование документа
     */
    protected String documentName;

    /**
     * Бинарные данные документа ККТ (после unwrapping ПКЗ)
     */
    protected byte[] rawData;

    /**
     * дата, время
     * tag: 1012
     */
    @PropertyTag(value = FiscalDocumentTag.DATE_TIME, protocolVersion = {"1.0", "1.1"})
    protected Long dateTime;

    /**
     * регистрационный номер ККТ
     * tag: 1037
     */
    @PropertyTag(value = FiscalDocumentTag.KKT_REG_ID, protocolVersion = {"1.0", "1.1"})
    protected String kktRegId;

    /**
     * заводской номер фискального накопителя
     * tag: 1041
     */
    @PropertyTag(value = FiscalDocumentTag.FISCAL_DRIVE_NUMBER, protocolVersion = {"1.0", "1.1"})
    protected String fiscalDriveNumber;

    /**
     * порядковый номер фискального документа
     * tag: 1040
     */
    @PropertyTag(value = FiscalDocumentTag.FISCAL_DOCUMENT_NUMBER, protocolVersion = {"1.0", "1.1"})
    protected Long fiscalDocumentNumber;

    /**
     * фискальный признак документа
     * tag: 1077
     */
    protected String fiscalSign;
    @PropertyTag(value = FiscalDocumentTag.FISCAL_SIGN, protocolVersion = {"1.0", "1.1"})
    public void setFiscalSignFromTags(ByteBuffer buffer) {
        byte[] arr = buffer.array();
        if (arr.length >= 6) {
            fiscalSign = String.valueOf(ByteBuffer.wrap(Arrays.copyOfRange(arr, 2, arr.length)).getInt() & 0xffffffffl);
        } else {
            fiscalSign = BaseEncoding.base16().encode(arr);
        }
    }

    /**
     * дополнительный реквизит
     * tag: 1084
     */
    @PropertyTag(value = FiscalDocumentTag.EXTRA_DATA, listItemTag = FiscalDocumentTag.MESSAGE_TYPE_STRING, protocolVersion = {"1.0", "1.1"})
    protected List<String> extraData;

    /**
     * фискальный признак сообщения / фискальный признак подтверждения
     */
    //TODO add confirmationFiscalSign parsing
    //@PropertyTag(value = FiscalDocumentTag.DEFAULT, protocolVersion = {"1.0", "1.1"})
    protected byte[] messageFiscalSign;


    @Override
    public String getProtocolVersion() {
        return protocolVersion;
    }

    @Override
    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    @Override
    public int getDocumentCode() {
        return documentCode;
    }

    @Override
    public void setDocumentCode(int documentCode) {
        this.documentCode = documentCode;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @Override
    @JsonIgnore
    public byte[] getRawData() {
        return rawData;
    }

    @Override
    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }

    @Override
    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String getKktRegId() {
        return kktRegId;
    }

    public void setKktRegId(String kktRegId) {
        this.kktRegId = kktRegId;
    }

    @Override
    public String getFiscalDriveNumber() {
        return fiscalDriveNumber;
    }

    public void setFiscalDriveNumber(String fiscalDriveNumber) {
        this.fiscalDriveNumber = fiscalDriveNumber;
    }

    @Override
    public Long getFiscalDocumentNumber() {
        return fiscalDocumentNumber;
    }

    public void setFiscalDocumentNumber(Long fiscalDocumentNumber) {
        this.fiscalDocumentNumber = fiscalDocumentNumber;
    }

    @Override
    public String getFiscalSign() {
        return fiscalSign;
    }

    public void setFiscalSign(String fiscalSign) {
        this.fiscalSign = fiscalSign;
    }

    public List<String> getExtraData() {
        return extraData;
    }

    public void setExtraData(List<String> extraData) {
        this.extraData = extraData;
    }

    public byte[] getMessageFiscalSign() {
        return messageFiscalSign;
    }

    public void setMessageFiscalSign(byte[] messageFiscalSign) {
        this.messageFiscalSign = messageFiscalSign;
    }
}
