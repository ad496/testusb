package com.company.data.document;



import com.company.data.tag.FiscalDocumentTag;
import com.company.data.tag.FiscalDocumentType;
import com.company.data.tag.annotation.DocumentType;
import com.company.data.tag.annotation.PropertyTag;

import java.util.List;


/**
 * Фискальный документ -Отчет о фискализации / отчет о регистрации
 */
@DocumentType(value = FiscalDocumentType.REGISTRATION_REPORT, protocolVersion = {"1.0", "1.1"})
public class RegistrationReport extends BaseFiscalDocument {
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
     * системы налогообложения
     * tag: 1062
     */
    @PropertyTag(value = FiscalDocumentTag.TAXATION_TYPE, protocolVersion = {"1.0", "1.1"})
    private Long taxationType;

    /**
     * автономный режим
     * tag: 1002
     */
    @PropertyTag(value = FiscalDocumentTag.OFFLINE_MODE, protocolVersion = {"1.0", "1.1"})
    private String offlineMode;

    /**
     * Применение платежными агентами (субагентами)
     * tag: 1057
     */
    @PropertyTag(value = FiscalDocumentTag.PAYMENT_AGENT_MODE, protocolVersion = {"1.0"})
    private String paymentAgentMode;

    /**
     * Применение банковскими агентами (субагентами)
     * tag: 1058
     */
    @PropertyTag(value = FiscalDocumentTag.BANK_AGENT_MODE, protocolVersion = {"1.0"})
    private String bankAgentMode;

    /**
     * признак БСО
     * tag: 1110
     */
    @PropertyTag(value = FiscalDocumentTag.BSO_SIGN, protocolVersion = {"1.1"})
    private String bsoSign;

    /**
     * признак услуги
     * tag: 1109
     */
    @PropertyTag(value = FiscalDocumentTag.SERVICE_SIGN, protocolVersion = {"1.1"})
    private String serviceSign;

    /**
     * признак шифрования
     * tag: 1056
     */
    @PropertyTag(value = FiscalDocumentTag.ENCRYPTION_SIGN, protocolVersion = {"1.0", "1.1"})
    private String encryptionSign;

    /**
     * автоматический режим
     * tag: 1001
     */
    @PropertyTag(value = FiscalDocumentTag.AUTO_MODE, protocolVersion = {"1.0", "1.1"})
    private String autoMode;

    /**
     * номер автомата
     * tag: 1036
     */
    @PropertyTag(value = FiscalDocumentTag.MACHINE_NUMBER, protocolVersion = {"1.0", "1.1"})
    private String machineNumber;

    /**
     * признак расчетов в Интернете
     * tag: 1108
     */
    @PropertyTag(value = FiscalDocumentTag.INTERNET_PAYMENTS_SIGN, protocolVersion = {"1.1"})
    private String internetSign;

    /**
     * кассир
     * tag: 1021
     */
    @PropertyTag(value = FiscalDocumentTag.OPERATOR, protocolVersion = {"1.0", "1.1"})
    private String operator;

    /**
     * Почтовый индекс
     * tag: 1049
     */
    @PropertyTag(value = FiscalDocumentTag.POSTCODE, protocolVersion = {"1.0"})
    private String postcode;

    /**
     * адрес (место) расчетов
     * tag: 1009
     */
    @PropertyTag(value = FiscalDocumentTag.RETAIL_PLACE_ADDRESS, protocolVersion = {"1.0", "1.1"})
    private String retailPlaceAddress;

    /**
     * ОФД
     * tag: 1046
     */
    @PropertyTag(value = FiscalDocumentTag.OFD, protocolVersion = {"1.0"})
    private String ofd;

    /**
     * Сайт ОФД
     * tag: 1061
     */
    @PropertyTag(value = FiscalDocumentTag.OFD_SITE, protocolVersion = {"1.0"})
    private String ofdSite;

    /**
     * Сайт налогового органа
     * tag: 1060
     */
    @PropertyTag(value = FiscalDocumentTag.TAX_AUTHORITY_SITE, protocolVersion = {"1.0"})
    private String taxAuthoritySite;

    /**
     * ИНН ОФД
     * tag: 1017
     */
    @PropertyTag(value = FiscalDocumentTag.OFD_INN, protocolVersion = {"1.0", "1.1"})
    private String ofdInn;

    /**
     * заводской номер ККТ
     * tag: 1013
     */
    @PropertyTag(value = FiscalDocumentTag.KKT_FACTORY_NUMBER, protocolVersion = {"1.0", "1.1"})
    private String kktNumber;

    /**
     * сообщение оператору
     * tag: 1069
     */
    @PropertyTag(value = FiscalDocumentTag.MESSAGE_TO_OPERATOR, listItemTag = FiscalDocumentTag.MESSAGE_TYPE_STRING, protocolVersion = {"1.0"})
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

    public Long getTaxationType() {
        return taxationType;
    }

    public void setTaxationType(Long taxationType) {
        this.taxationType = taxationType;
    }

    public String getOfflineMode() {
        return offlineMode;
    }

    public void setOfflineMode(String offlineMode) {
        this.offlineMode = offlineMode;
    }

    public String getPaymentAgentMode() {
        return paymentAgentMode;
    }

    public void setPaymentAgentMode(String paymentAgentMode) {
        this.paymentAgentMode = paymentAgentMode;
    }

    public String getBankAgentMode() {
        return bankAgentMode;
    }

    public void setBankAgentMode(String bankAgentMode) {
        this.bankAgentMode = bankAgentMode;
    }

    public String getBsoSign() {
        return bsoSign;
    }

    public void setBsoSign(String bsoSign) {
        this.bsoSign = bsoSign;
    }

    public String getServiceSign() {
        return serviceSign;
    }

    public void setServiceSign(String serviceSign) {
        this.serviceSign = serviceSign;
    }

    public String getEncryptionSign() {
        return encryptionSign;
    }

    public void setEncryptionSign(String encryptionSign) {
        this.encryptionSign = encryptionSign;
    }

    public String getAutoMode() {
        return autoMode;
    }

    public void setAutoMode(String autoMode) {
        this.autoMode = autoMode;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getInternetSign() {
        return internetSign;
    }

    public void setInternetSign(String internetSign) {
        this.internetSign = internetSign;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRetailPlaceAddress() {
        return retailPlaceAddress;
    }

    public void setRetailPlaceAddress(String retailPlaceAddress) {
        this.retailPlaceAddress = retailPlaceAddress;
    }

    public String getOfd() {
        return ofd;
    }

    public void setOfd(String ofd) {
        this.ofd = ofd;
    }

    public String getOfdSite() {
        return ofdSite;
    }

    public void setOfdSite(String ofdSite) {
        this.ofdSite = ofdSite;
    }

    public String getTaxAuthoritySite() {
        return taxAuthoritySite;
    }

    public void setTaxAuthoritySite(String taxAuthoritySite) {
        this.taxAuthoritySite = taxAuthoritySite;
    }

    public String getOfdInn() {
        return ofdInn;
    }

    public void setOfdInn(String ofdInn) {
        this.ofdInn = ofdInn;
    }

    public String getKktNumber() {
        return kktNumber;
    }

    public void setKktNumber(String kktNumber) {
        this.kktNumber = kktNumber;
    }

    public List<String> getMessageToOperator() {
        return messageToOperator;
    }

    public void setMessageToOperator(List<String> messageToOperator) {
        this.messageToOperator = messageToOperator;
    }
}
