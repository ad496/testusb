package com.company.data.document;



import com.company.data.tag.FiscalDocumentTag;
import com.company.data.tag.FiscalDocumentType;
import com.company.data.tag.annotation.DocumentType;
import com.company.data.tag.annotation.PropertyTag;

import java.math.BigDecimal;

/**
 * Фискальный документ - Кассовый чек коррекции / БСО коррекции
 */
@DocumentType(value = FiscalDocumentType.BSO_CORRECTION, protocolVersion = {"1.0", "1.1"})
@DocumentType(value = FiscalDocumentType.RECEIPT_CORRECTION, protocolVersion = {"1.0", "1.1"})
public class ReceiptCorrection extends BaseFiscalDocument {
    /**
     * наименование пользователя
     * tag: 1048
     */
    @PropertyTag(value = FiscalDocumentTag.USER_NAME, protocolVersion = {"1.0", "1.1"})
    private String userName;

    /**
     * ИНН пользователя
     * tag: 1018
     */
    @PropertyTag(value = FiscalDocumentTag.USER_INN, protocolVersion = {"1.0", "1.1"})
    private String userInn;

    /**
     * номер чека за смену
     * tag: 1042
     */
    @PropertyTag(value = FiscalDocumentTag.RECEIPT_NUMBER, protocolVersion = {"1.0", "1.1"})
    private Long receiptNumber;

    /**
     * номер смены
     * tag: 1038
     */
    @PropertyTag(value = FiscalDocumentTag.SHIFT_NUMBER, protocolVersion = {"1.0", "1.1"})
    private long shiftNumber;

    /**
     * признак расчета
     * tag: 1054
     */
    @PropertyTag(value = FiscalDocumentTag.OPERATION_TYPE, protocolVersion = {"1.0", "1.1"})
    private Long operationType;

    /**
     * применяемая система налогообложения
     * tag: 1062
     */
    @PropertyTag(value = FiscalDocumentTag.TAXATION_TYPE, protocolVersion = {"1.0", "1.1"})
    private Long taxationType;

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
    @PropertyTag(value = FiscalDocumentTag.RETAIL_PLACE_ADDRESS, protocolVersion = {"1.0", "1.1"})
    private String retailPlaceAddress;

    /**
     * ИТОГ, в копейках
     * tag: 1020
     */
    private BigDecimal totalSum;

    @PropertyTag(value = FiscalDocumentTag.TOTAL_SUM, protocolVersion = {"1.0", "1.1"})
    public void setTotalSumFromTags(long val) {
        totalSum = BigDecimal.valueOf(val).divide(BigDecimal.valueOf(100));
    }

    /**
     * форма расчета - наличными, в копейках
     * tag: 1031
     */
    private BigDecimal cashTotalSum;

    @PropertyTag(value = FiscalDocumentTag.CASH_TOTAL_SUM, protocolVersion = {"1.1"})
    public void setCashTotalSumFromTags(long val) {
        cashTotalSum = BigDecimal.valueOf(val).divide(BigDecimal.valueOf(100));
    }

    /**
     * форма расчета - электронными, в копейках
     * tag: 1081
     */
    private BigDecimal eCashTotalSum;

    @PropertyTag(value = FiscalDocumentTag.ECASH_TOTAL_SUM, protocolVersion = {"1.1"})
    public void setECashTotalSumFromTags(long val) {
        eCashTotalSum = BigDecimal.valueOf(val).divide(BigDecimal.valueOf(100));
    }


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

    public Long getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(Long receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public long getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(long shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public Long getOperationType() {
        return operationType;
    }

    public void setOperationType(Long operationType) {
        this.operationType = operationType;
    }

    public Long getTaxationType() {
        return taxationType;
    }

    public void setTaxationType(Long taxationType) {
        this.taxationType = taxationType;
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

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public BigDecimal getCashTotalSum() {
        return cashTotalSum;
    }

    public void setCashTotalSum(BigDecimal cashTotalSum) {
        this.cashTotalSum = cashTotalSum;
    }

    public BigDecimal geteCashTotalSum() {
        return eCashTotalSum;
    }

    public void seteCashTotalSum(BigDecimal eCashTotalSum) {
        this.eCashTotalSum = eCashTotalSum;
    }
}
