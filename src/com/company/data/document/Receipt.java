package com.company.data.document;



import com.company.data.document.subitem.ReceiptItem;
import com.company.data.document.subitem.SaleOrMarkup;
import com.company.data.document.subitem.Tax;
import com.company.data.tag.FiscalDocumentTag;
import com.company.data.tag.FiscalDocumentType;
import com.company.data.tag.annotation.DocumentType;
import com.company.data.tag.annotation.PropertyTag;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Фискальный документ - Чек / БСО
 */
@DocumentType(value = FiscalDocumentType.BSO, protocolVersion = {"1.1"})
@DocumentType(value = FiscalDocumentType.RECEIPT, protocolVersion = {"1.0", "1.1"})
public class Receipt extends BaseFiscalDocument {
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
    @PropertyTag(value = FiscalDocumentTag.USER_INN, protocolVersion = {"1.1"})
    private String userInn;

    /**
     * номер чека за смену
     * tag: 1042
     */
    @PropertyTag(value = FiscalDocumentTag.RECEIPT_NUMBER, protocolVersion = {"1.0", "1.1"})
    private long receiptNumber;

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
    private long operationType;

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
    @PropertyTag(value = FiscalDocumentTag.RETAIL_PLACE_ADDRESS, protocolVersion = {"1.1"})
    private String retailPlaceAddress;

    /**
     * адрес покупателя
     * tag: 1008
     */
    @PropertyTag(value = FiscalDocumentTag.BUYER_ADDRESS, protocolVersion = {"1.0", "1.1"})
    private String buyerAddress;

    /**
     * адрес отправителя
     * tag: 1117
     */
    @PropertyTag(value = FiscalDocumentTag.SENDER_ADDRESS, protocolVersion = {"1.1"})
    private String senderAddress;

    /**
     * адрес сайта для проверки ФП
     * tag: 1115
     */
    @PropertyTag(value = FiscalDocumentTag.ADDRESS_TO_CHECK_FISCAL_SIGN, protocolVersion = {"1.1"})
    private String addressToCheckFiscalSign;

    /**
     * наименование товара (реквизиты)
     * tag: 1059
     */
    @PropertyTag(value = FiscalDocumentTag.RECEIPT_ITEMS, protocolVersion = {"1.0", "1.1"})
    private List<ReceiptItem> items;

    /**
     * сторно товара (реквизиты)
     * tag: 1071
     */
    @PropertyTag(value = FiscalDocumentTag.STORNO_ITEMS, protocolVersion = {"1.0", "1.1"})
    private List<ReceiptItem> stornoItems;

    /**
     * налоги
     * tag: 1033
     */
    @PropertyTag(value = FiscalDocumentTag.TAXES, protocolVersion = {"1.0"})
    private List<Tax> taxes;

    /**
     * размер вознаграждения платежного агента (субагента), в копейках
     * tag: 1011
     */
    @PropertyTag(value = FiscalDocumentTag.PAYMENT_AGENT_REMUNERATION, protocolVersion = {"1.0", "1.1"})
    private Long paymentAgentRemuneration;

    /**
     * наименование платежного агента
     * tag: 1027
     */
    @PropertyTag(value = FiscalDocumentTag.PAYMENT_AGENT_NAME, protocolVersion = {"1.0"})
    private String paymentAgentName;

    /**
     * наименование платежного субагента
     * tag: 1028
     */
    @PropertyTag(value = FiscalDocumentTag.PAYMENT_SUBAGENT_NAME, protocolVersion = {"1.0"})
    private String paymentSubAgentName;

    /**
     * телефон платежного агента
     * tag: 1074
     */
    @PropertyTag(value = FiscalDocumentTag.PAYMENT_AGENT_PHONE, protocolVersion = {"1.0", "1.1"})
    private String paymentAgentPhone;

    /**
     * телефон платежного субагента
     * tag: 1083
     */
    @PropertyTag(value = FiscalDocumentTag.PAYMENT_SUBAGENT_PHONE, protocolVersion = {"1.0", "1.1"})
    private String paymentSubAgentPhone;

    /**
     * телефон оператора по приему платежей
     * tag: 1119
     */
    @PropertyTag(value = FiscalDocumentTag.RECEIVE_PAYMENTS_OPERATOR_PHONE, protocolVersion = {"1.1"})
    private String receivePaymentsOperatorPhone;

    /**
     * телефон оператора по переводу денежных средств
     * tag: 1075
     */
    @PropertyTag(value = FiscalDocumentTag.TRANSFER_PAYMENTS_OPERATOR_PHONE, protocolVersion = {"1.0", "1.1"})
    private String transferPaymentsOperatorPhone;

    /**
     * телефон банковского агента
     * tag: 1073
     */
    @PropertyTag(value = FiscalDocumentTag.BANK_AGENT_PHONE, protocolVersion = {"1.0", "1.1"})
    private String bankAgentPhone;

    /**
     * телефон банковского субагента
     * tag: 1082
     */
    @PropertyTag(value = FiscalDocumentTag.BANK_SUBAGENT_PHONE, protocolVersion = {"1.0", "1.1"})
    private String bankSubAgentPhone;

    /**
     * операция банковского агента
     * tag: 1044
     */
    @PropertyTag(value = FiscalDocumentTag.BANK_AGENT_OPERATION, protocolVersion = {"1.0", "1.1"})
    private String bankAgentOperation;

    /**
     * операция банковского субагента
     * tag: 1045
     */
    @PropertyTag(value = FiscalDocumentTag.BANK_SUBAGENT_OPERATION, protocolVersion = {"1.0", "1.1"})
    private String bankSubAgentOperation;

    /**
     * размер вознаграждения банковского агента (субагента)
     * tag: 1010
     */
    @PropertyTag(value = FiscalDocumentTag.BANK_AGENT_REMUNERATION, protocolVersion = {"1.0", "1.1"})
    private Long bankAgentRemuneration;

    /**
     * наименование банковского агента
     * tag: 1024
     */
    @PropertyTag(value = FiscalDocumentTag.BANK_AGENT_NAME, protocolVersion = {"1.0"})
    private String bankAgentName;

    /**
     * наименование банковского субагента
     * tag: 1025
     */
    @PropertyTag(value = FiscalDocumentTag.BANK_SUBAGENT_NAME, protocolVersion = {"1.0"})
    private String bankSubAgentName;

    /**
     * наименование оператора по переводу денежных средств
     * tag: 1026
     */
    @PropertyTag(value = FiscalDocumentTag.TRANSFER_PAYMENTS_OPERATOR_NAME, protocolVersion = {"1.0", "1.1"})
    private String transferPaymentsOperatorName;

    /**
     * адрес оператора по переводу денежных средств
     * tag: 1005
     */
    @PropertyTag(value = FiscalDocumentTag.TRANSFER_PAYMENTS_OPERATOR_ADDRESS, protocolVersion = {"1.0", "1.1"})
    private String transferPaymentsOperatorAddress;

    /**
     * ИНН оператора по переводу денежных средств
     * tag: 1016
     */
    @PropertyTag(value = FiscalDocumentTag.TRANSFER_PAYMENTS_OPERATOR_INN, protocolVersion = {"1.0", "1.1"})
    private String transferPaymentsOperatorInn;

    /**
     * адрес банковского агента
     * tag: 1003
     */
    @PropertyTag(value = FiscalDocumentTag.BANK_AGENT_ADDRESS, protocolVersion = {"1.0"})
    private String bankAgentAddress;

    /**
     * адрес банковского субагента
     * tag: 1004
     */
    @PropertyTag(value = FiscalDocumentTag.BANK_SUBAGENT_ADDRESS, protocolVersion = {"1.0"})
    private String bankSubAgentAddress;

    /**
     * Скидки / Наценки
     * tag: 1112
     */
    @PropertyTag(value = FiscalDocumentTag.SALES_OR_MARKUPS, protocolVersion = {"1.1"})
    private List<SaleOrMarkup> saleOrMarkups = new ArrayList<>();

    /**
     * скидка (ставка)
     * tag: 1063
     */
    private BigDecimal saleRate;

    @PropertyTag(value = FiscalDocumentTag.SALE_RATE, protocolVersion = {"1.0"})
    public void setSaleRateFromTags(long val) {
        saleRate = BigDecimal.valueOf(val).divide(BigDecimal.valueOf(100));
    }

    /**
     * наценка (ставка)
     * tag: 1034
     */
    private BigDecimal markupRate;

    @PropertyTag(value = FiscalDocumentTag.MARKUP_RATE, protocolVersion = {"1.0"})
    public void setMarkupRateFromTags(long val) {
        markupRate = BigDecimal.valueOf(val).divide(BigDecimal.valueOf(100));
    }

    /**
     * скидка (сумма)
     * tag: 1064
     */
    @PropertyTag(value = FiscalDocumentTag.SALE_SUM, protocolVersion = {"1.0"})
    private long saleSum;

    /**
     * наценка (сумма)
     * tag: 1035
     */
    @PropertyTag(value = FiscalDocumentTag.MARKUP_SUM, protocolVersion = {"1.0"})
    private long markupSum;

    /**
     * НДС
     * tag: 1102-1107
     */
    @PropertyTag(value = FiscalDocumentTag.NDS_18_SUM, protocolVersion = {"1.1"})
    private Long nds18Rate;

    @PropertyTag(value = FiscalDocumentTag.NDS_10_SUM, protocolVersion = {"1.1"})
    private Long nds10Rate;

    @PropertyTag(value = FiscalDocumentTag.NDS_0_SUM, protocolVersion = {"1.1"})
    private Long nds0Rate;

    @PropertyTag(value = FiscalDocumentTag.NDS_NONE_SUM, protocolVersion = {"1.1"})
    private Long ndsNoneRate;

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
     * сумма уплаченная наличными, в копейках
     * tag: 1031
     */
    private BigDecimal cashTotalSum;

    @PropertyTag(value = FiscalDocumentTag.CASH_TOTAL_SUM, protocolVersion = {"1.0", "1.1"})
    public void setCashTotalSumFromTags(long val) {
        cashTotalSum = BigDecimal.valueOf(val).divide(BigDecimal.valueOf(100));
    }

    /**
     * сумма уплаченная электронными средствами платежа, в копейках
     * tag: 1081
     */
    private BigDecimal eCashTotalSum;

    @PropertyTag(value = FiscalDocumentTag.ECASH_TOTAL_SUM, protocolVersion = {"1.0", "1.1"})
    public void setECashTotalSumFromTags(long val) {
        eCashTotalSum = BigDecimal.valueOf(val).divide(BigDecimal.valueOf(100));
    }

    /**
     * сообщение оператору
     * tag: 1069
     */
    @PropertyTag(value = FiscalDocumentTag.MESSAGE_TO_OPERATOR, listItemTag = FiscalDocumentTag.MESSAGE_TYPE_STRING, protocolVersion = {"1.0"})
    private List<String> messageToOperator;

    /**
     * телефон или электронный адрес покупателя
     * tag: 1008
     */
    private String email;
    private String phone;

    @PropertyTag(value = FiscalDocumentTag.BUYER_ADDRESS, protocolVersion = {"1.0", "1.1"})
    public void setContactFromTags(String contact) {
        if (StringUtils.contains(contact, '@')) {
            this.email = contact;
        } else {
            this.phone = contact;
        }
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

    public long getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(long receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public long getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(long shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public long getOperationType() {
        return operationType;
    }

    public void setOperationType(long operationType) {
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

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getAddressToCheckFiscalSign() {
        return addressToCheckFiscalSign;
    }

    public void setAddressToCheckFiscalSign(String addressToCheckFiscalSign) {
        this.addressToCheckFiscalSign = addressToCheckFiscalSign;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public void setItems(List<ReceiptItem> items) {
        this.items = items;
    }

    public List<ReceiptItem> getStornoItems() {
        return stornoItems;
    }

    public void setStornoItems(List<ReceiptItem> stornoItems) {
        this.stornoItems = stornoItems;
    }

    public List<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Tax> taxes) {
        this.taxes = taxes;
    }

    public Long getPaymentAgentRemuneration() {
        return paymentAgentRemuneration;
    }

    public void setPaymentAgentRemuneration(Long paymentAgentRemuneration) {
        this.paymentAgentRemuneration = paymentAgentRemuneration;
    }

    public String getPaymentAgentName() {
        return paymentAgentName;
    }

    public void setPaymentAgentName(String paymentAgentName) {
        this.paymentAgentName = paymentAgentName;
    }

    public String getPaymentSubAgentName() {
        return paymentSubAgentName;
    }

    public void setPaymentSubAgentName(String paymentSubAgentName) {
        this.paymentSubAgentName = paymentSubAgentName;
    }

    public String getPaymentAgentPhone() {
        return paymentAgentPhone;
    }

    public void setPaymentAgentPhone(String paymentAgentPhone) {
        this.paymentAgentPhone = paymentAgentPhone;
    }

    public String getPaymentSubAgentPhone() {
        return paymentSubAgentPhone;
    }

    public void setPaymentSubAgentPhone(String paymentSubAgentPhone) {
        this.paymentSubAgentPhone = paymentSubAgentPhone;
    }

    public String getReceivePaymentsOperatorPhone() {
        return receivePaymentsOperatorPhone;
    }

    public void setReceivePaymentsOperatorPhone(String receivePaymentsOperatorPhone) {
        this.receivePaymentsOperatorPhone = receivePaymentsOperatorPhone;
    }

    public String getTransferPaymentsOperatorPhone() {
        return transferPaymentsOperatorPhone;
    }

    public void setTransferPaymentsOperatorPhone(String transferPaymentsOperatorPhone) {
        this.transferPaymentsOperatorPhone = transferPaymentsOperatorPhone;
    }

    public String getBankAgentPhone() {
        return bankAgentPhone;
    }

    public void setBankAgentPhone(String bankAgentPhone) {
        this.bankAgentPhone = bankAgentPhone;
    }

    public String getBankSubAgentPhone() {
        return bankSubAgentPhone;
    }

    public void setBankSubAgentPhone(String bankSubAgentPhone) {
        this.bankSubAgentPhone = bankSubAgentPhone;
    }

    public String getBankAgentOperation() {
        return bankAgentOperation;
    }

    public void setBankAgentOperation(String bankAgentOperation) {
        this.bankAgentOperation = bankAgentOperation;
    }

    public String getBankSubAgentOperation() {
        return bankSubAgentOperation;
    }

    public void setBankSubAgentOperation(String bankSubAgentOperation) {
        this.bankSubAgentOperation = bankSubAgentOperation;
    }

    public Long getBankAgentRemuneration() {
        return bankAgentRemuneration;
    }

    public void setBankAgentRemuneration(Long bankAgentRemuneration) {
        this.bankAgentRemuneration = bankAgentRemuneration;
    }

    public String getBankAgentName() {
        return bankAgentName;
    }

    public void setBankAgentName(String bankAgentName) {
        this.bankAgentName = bankAgentName;
    }

    public String getBankSubAgentName() {
        return bankSubAgentName;
    }

    public void setBankSubAgentName(String bankSubAgentName) {
        this.bankSubAgentName = bankSubAgentName;
    }

    public String getTransferPaymentsOperatorName() {
        return transferPaymentsOperatorName;
    }

    public void setTransferPaymentsOperatorName(String transferPaymentsOperatorName) {
        this.transferPaymentsOperatorName = transferPaymentsOperatorName;
    }

    public String getTransferPaymentsOperatorAddress() {
        return transferPaymentsOperatorAddress;
    }

    public void setTransferPaymentsOperatorAddress(String transferPaymentsOperatorAddress) {
        this.transferPaymentsOperatorAddress = transferPaymentsOperatorAddress;
    }

    public String getTransferPaymentsOperatorInn() {
        return transferPaymentsOperatorInn;
    }

    public void setTransferPaymentsOperatorInn(String transferPaymentsOperatorInn) {
        this.transferPaymentsOperatorInn = transferPaymentsOperatorInn;
    }

    public String getBankAgentAddress() {
        return bankAgentAddress;
    }

    public void setBankAgentAddress(String bankAgentAddress) {
        this.bankAgentAddress = bankAgentAddress;
    }

    public String getBankSubAgentAddress() {
        return bankSubAgentAddress;
    }

    public void setBankSubAgentAddress(String bankSubAgentAddress) {
        this.bankSubAgentAddress = bankSubAgentAddress;
    }

    public List<SaleOrMarkup> getSaleOrMarkups() {
        return saleOrMarkups;
    }

    public void setSaleOrMarkups(List<SaleOrMarkup> saleOrMarkups) {
        this.saleOrMarkups = saleOrMarkups;
    }

    public BigDecimal getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(BigDecimal saleRate) {
        this.saleRate = saleRate;
    }

    public BigDecimal getMarkupRate() {
        return markupRate;
    }

    public void setMarkupRate(BigDecimal markupRate) {
        this.markupRate = markupRate;
    }

    public long getSaleSum() {
        return saleSum;
    }

    public void setSaleSum(long saleSum) {
        this.saleSum = saleSum;
    }

    public long getMarkupSum() {
        return markupSum;
    }

    public void setMarkupSum(long markupSum) {
        this.markupSum = markupSum;
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

    public List<String> getMessageToOperator() {
        return messageToOperator;
    }

    public void setMessageToOperator(List<String> messageToOperator) {
        this.messageToOperator = messageToOperator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getNds18Rate() {
        return nds18Rate;
    }

    public void setNds18Rate(Long nds18Rate) {
        this.nds18Rate = nds18Rate;
    }

    public Long getNds10Rate() {
        return nds10Rate;
    }

    public void setNds10Rate(Long nds10Rate) {
        this.nds10Rate = nds10Rate;
    }

    public Long getNds0Rate() {
        return nds0Rate;
    }

    public void setNds0Rate(Long nds0Rate) {
        this.nds0Rate = nds0Rate;
    }

    public Long getNdsNoneRate() {
        return ndsNoneRate;
    }

    public void setNdsNoneRate(Long ndsNoneRate) {
        this.ndsNoneRate = ndsNoneRate;
    }
}
