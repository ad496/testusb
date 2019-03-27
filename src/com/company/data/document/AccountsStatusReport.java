package com.company.data.document;


import com.company.data.tag.FiscalDocumentTag;
import com.company.data.tag.FiscalDocumentType;
import com.company.data.tag.annotation.DocumentType;
import com.company.data.tag.annotation.PropertyTag;
import com.fasterxml.jackson.annotation.JsonInclude;



/**
 * Фискальный документ - Отчет о текущем состоянии счетов
 */
@DocumentType(value = FiscalDocumentType.ACCOUNTS_STATUS_REPORT, protocolVersion = {"1.0", "1.1"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountsStatusReport extends BaseFiscalDocument {
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
    @PropertyTag(value = FiscalDocumentTag.RETAIL_PLACE_ADDRESS, protocolVersion = {"1.0", "1.1"})
    private String retailPlaceAddress;

    /**
     * номер смены
     * tag: 1038
     */
    @PropertyTag(value = FiscalDocumentTag.SHIFT_NUMBER, protocolVersion = {"1.0", "1.1"})
    private long shiftNumber;

    /**
     * автономный режим
     * tag: 1002
     */
    @PropertyTag(value = FiscalDocumentTag.OFFLINE_MODE, protocolVersion = {"1.0", "1.1"})
    private String offlineMode;

    /**
     * номер первого непереданного документа
     * tag: 1116
     */
    @PropertyTag(value = FiscalDocumentTag.FIRST_NOT_SENT_DOCUMENT_INDEX, protocolVersion = {"1.0", "1.1"})
    private String firstNotSentDocumentIndex;

    /**
     * количество непереданных документов
     * tag: 1097
     */
    @PropertyTag(value = FiscalDocumentTag.NOT_SENT_DOCUMENT_NUMBER, protocolVersion = {"1.0", "1.1"})
    private Long notSentDocumentsNumber;

    /**
     * дата и время первого из непереданных ФД
     * tag: 1098
     */
    @PropertyTag(value = FiscalDocumentTag.FIRST_NOT_SENT_DOCUMENT_DATE_TIME, protocolVersion = {"1.0", "1.1"})
    private Long firstNotSentDocumentDateTime;


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

    public long getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(long shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public String getOfflineMode() {
        return offlineMode;
    }

    public void setOfflineMode(String offlineMode) {
        this.offlineMode = offlineMode;
    }

    public String getFirstNotSentDocumentIndex() {
        return firstNotSentDocumentIndex;
    }

    public void setFirstNotSentDocumentIndex(String firstNotSentDocumentIndex) {
        this.firstNotSentDocumentIndex = firstNotSentDocumentIndex;
    }

    public Long getNotSentDocumentsNumber() {
        return notSentDocumentsNumber;
    }

    public void setNotSentDocumentsNumber(Long notSentDocumentsNumber) {
        this.notSentDocumentsNumber = notSentDocumentsNumber;
    }

    public Long getFirstNotSentDocumentDateTime() {
        return firstNotSentDocumentDateTime;
    }

    public void setFirstNotSentDocumentDateTime(Long firstNotSentDocumentDateTime) {
        this.firstNotSentDocumentDateTime = firstNotSentDocumentDateTime;
    }
}
