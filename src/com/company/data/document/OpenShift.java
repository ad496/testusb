package com.company.data.document;

import com.company.data.tag.FiscalDocumentTag;
import com.company.data.tag.FiscalDocumentType;
import com.company.data.tag.annotation.DocumentType;
import com.company.data.tag.annotation.PropertyTag;

import java.util.List;

/**
 * Фискальный документ - Отчет об открытии смены
 */
@DocumentType(value = FiscalDocumentType.OPEN_SHIFT, protocolVersion = {"1.0", "1.1"})
public class OpenShift extends BaseFiscalDocument {
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
     * кассир
     * tag: 1021
     */
    @PropertyTag(value = FiscalDocumentTag.OPERATOR, protocolVersion = {"1.0", "1.1"})
    private String operator;

    /**
     * адрес (место) расчетов
     * tag: 1009
     */
    @PropertyTag(value = FiscalDocumentTag.RETAIL_PLACE_ADDRESS, protocolVersion = {"1.1"})
    private String retailPlaceAddress;

    /**
     * номер смены
     * tag: 1038
     */
    @PropertyTag(value = FiscalDocumentTag.SHIFT_NUMBER, protocolVersion = {"1.0", "1.1"})
    private long shiftNumber;

    /**
     * признак превышения времени ожидания ответа ОФД
     * tag: 1053
     */
    @PropertyTag(value = FiscalDocumentTag.OFD_RESPONSE_TIMEOUT_SIGN, protocolVersion = {"1.0"})
    private String ofdResponseTimeoutSign;

    /**
     * признак необходимости срочной замены ФН
     * tag: 1051
     */
    @PropertyTag(value = FiscalDocumentTag.FISCAL_DRIVE_REPLACE_REQUIRED_SIGN, protocolVersion = {"1.0"})
    private String fiscalDriveReplaceRequiredSign;

    /**
     * признак переполнения памяти ФН
     * tag: 1052
     */
    @PropertyTag(value = FiscalDocumentTag.FISCAL_DRIVE_MEMORY_EXCEEDED_SIGN, protocolVersion = {"1.0"})
    private String fiscalDriveMemoryExceededSign;

    /**
     * признак исчерпания ресурса ФН
     * tag: 1050
     */
    @PropertyTag(value = FiscalDocumentTag.FISCAL_DRIVE_EXHAUSTION_SIGN, protocolVersion = {"1.0"})
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRetailPlaceAddress() {
        return retailPlaceAddress;
    }

    public void setRetailPlaceAddress(String retailPlaceAddress) {
        this.retailPlaceAddress = retailPlaceAddress;
    }

    public long getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(long shiftNumber) {
        this.shiftNumber = shiftNumber;
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
