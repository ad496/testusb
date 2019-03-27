package com.company.data.document;


import com.company.data.tag.FiscalDocumentTag;
import com.company.data.tag.FiscalDocumentType;
import com.company.data.tag.annotation.DocumentType;
import com.company.data.tag.annotation.PropertyTag;

/**
 * Фискальный документ - Отчет о закрытии фискального накопителя
 */
@DocumentType(value = FiscalDocumentType.CLOSE_FISCAL_DRIVE, protocolVersion = {"1.0", "1.1"})
public class CloseFiscalDrive extends BaseFiscalDocument {
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
}
