package com.company.data.document;

import com.company.data.tag.FiscalDocumentTag;
import com.company.data.tag.FiscalDocumentType;
import com.company.data.tag.annotation.DocumentType;
import com.company.data.tag.annotation.PropertyTag;


import java.util.List;

/**
 * Фискальный документ - Отчет об закрытии смены
 */
@DocumentType(value = FiscalDocumentType.CLOSE_SHIFT, protocolVersion = {"1.0", "1.1"})
public class CloseShift extends BaseFiscalDocument {
    /**
     * наименование пользователя
     * tag: 1048
     */
    @PropertyTag(value = FiscalDocumentTag.USER_NAME, protocolVersion = {"1.1"})
    private String userName;

    /**
     * ИНН пользователя
     * tag: 1018
     */
    @PropertyTag(value = FiscalDocumentTag.USER_INN, protocolVersion = {"1.0", "1.1"})
    private String userInn;

    /**
     * адрес (место) расчетов
     * tag: 1009
     */
    @PropertyTag(value = FiscalDocumentTag.RETAIL_PLACE_ADDRESS, protocolVersion = {"1.1"})
    private String retailPlaceAddress;

    /**
     * кассир
     * tag: 1021
     */
    @PropertyTag(value = FiscalDocumentTag.OPERATOR, protocolVersion = {"1.0", "1.1"})
    private String operator;

    /**
     * номер смены
     * tag: 1038
     */
    @PropertyTag(value = FiscalDocumentTag.SHIFT_NUMBER, protocolVersion = {"1.0", "1.1"})
    private long shiftNumber;

    /**
     * количество кассовых чеков за смену
     * tag: 1118
     */
    @PropertyTag(value = FiscalDocumentTag.RECEIPTS_BY_SHIFT_QUANTITY, protocolVersion = {"1.1"})
    private Long receiptsQuantity;

    /**
     * количество фискальных документов за смену
     * tag: 1111
     */
    @PropertyTag(value = FiscalDocumentTag.FISCAL_DOCS_BY_SHIFT_QUANTITY, protocolVersion = {"1.1"})
    private Long documentsQuantity;

    /**
     * кол-во неподтвержденных документов ФД
     * tag: 1097
     */
    @PropertyTag(value = FiscalDocumentTag.NOT_SENT_DOCUMENT_NUMBER, protocolVersion = {"1.1"})
    private Long notTransmittedDocumentsQuantity;

    /**
     * дата и время первого из непереданных ФД
     * tag: 1098
     */
    @PropertyTag(value = FiscalDocumentTag.FIRST_NOT_SENT_DOCUMENT_DATE_TIME, protocolVersion = {"1.1"})
    private Long notTransmittedDocumentsDateTime;

    /**
     * признак превышения времени ожидания ответа ОФД
     * tag: 1053
     */
    @PropertyTag(value = FiscalDocumentTag.OFD_RESPONSE_TIMEOUT_SIGN, protocolVersion = {"1.0", "1.1"})
    private String ofdResponseTimeoutSign;

    /**
     * признак необходимости срочной замены ФН
     * tag: 1051
     */
    @PropertyTag(value = FiscalDocumentTag.FISCAL_DRIVE_REPLACE_REQUIRED_SIGN, protocolVersion = {"1.0", "1.1"})
    private String fiscalDriveReplaceRequiredSign;

    /**
     * признак переполнения памяти ФН
     * tag: 1052
     */
    @PropertyTag(value = FiscalDocumentTag.FISCAL_DRIVE_MEMORY_EXCEEDED_SIGN, protocolVersion = {"1.0", "1.1"})
    private String fiscalDriveMemoryExceededSign;

    /**
     * признак исчерпания ресурса ФН
     * tag: 1050
     */
    @PropertyTag(value = FiscalDocumentTag.FISCAL_DRIVE_EXHAUSTION_SIGN, protocolVersion = {"1.0", "1.1"})
    private String fiscalDriveExhaustionSign;

    /**
     * сообщение оператору
     * tag: 1069
     */
    @PropertyTag(value = FiscalDocumentTag.MESSAGE_TO_OPERATOR, listItemTag = FiscalDocumentTag.MESSAGE_TYPE_STRING, protocolVersion = {"1.0", "1.1"})
    private List<String> messageToOperator;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserInn() {
        return userInn;
    }

    public void setUserInn(String userInn) {
        this.userInn = userInn;
    }

    public String getRetailPlaceAddress() {
        return retailPlaceAddress;
    }

    public void setRetailPlaceAddress(String retailPlaceAddress) {
        this.retailPlaceAddress = retailPlaceAddress;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public long getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(long shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public Long getReceiptsQuantity() {
        return receiptsQuantity;
    }

    public void setReceiptsQuantity(Long receiptsQuantity) {
        this.receiptsQuantity = receiptsQuantity;
    }

    public Long getDocumentsQuantity() {
        return documentsQuantity;
    }

    public void setDocumentsQuantity(Long documentsQuantity) {
        this.documentsQuantity = documentsQuantity;
    }

    public Long getNotTransmittedDocumentsQuantity() {
        return notTransmittedDocumentsQuantity;
    }

    public void setNotTransmittedDocumentsQuantity(Long notTransmittedDocumentsQuantity) {
        this.notTransmittedDocumentsQuantity = notTransmittedDocumentsQuantity;
    }

    public Long getNotTransmittedDocumentsDateTime() {
        return notTransmittedDocumentsDateTime;
    }

    public void setNotTransmittedDocumentsDateTime(Long notTransmittedDocumentsDateTime) {
        this.notTransmittedDocumentsDateTime = notTransmittedDocumentsDateTime;
    }

    public String getOfdResponseTimeoutSign() {
        return ofdResponseTimeoutSign;
    }

    public void setOfdResponseTimeoutSign(String ofdResponseTimeoutSign) {
        this.ofdResponseTimeoutSign = ofdResponseTimeoutSign;
    }

    public String getFiscalDriveReplaceRequiredSign() {
        return fiscalDriveReplaceRequiredSign;
    }

    public void setFiscalDriveReplaceRequiredSign(String fiscalDriveReplaceRequiredSign) {
        this.fiscalDriveReplaceRequiredSign = fiscalDriveReplaceRequiredSign;
    }

    public String getFiscalDriveMemoryExceededSign() {
        return fiscalDriveMemoryExceededSign;
    }

    public void setFiscalDriveMemoryExceededSign(String fiscalDriveMemoryExceededSign) {
        this.fiscalDriveMemoryExceededSign = fiscalDriveMemoryExceededSign;
    }

    public String getFiscalDriveExhaustionSign() {
        return fiscalDriveExhaustionSign;
    }

    public void setFiscalDriveExhaustionSign(String fiscalDriveExhaustionSign) {
        this.fiscalDriveExhaustionSign = fiscalDriveExhaustionSign;
    }

    public List<String> getMessageToOperator() {
        return messageToOperator;
    }

    public void setMessageToOperator(List<String> messageToOperator) {
        this.messageToOperator = messageToOperator;
    }
}
