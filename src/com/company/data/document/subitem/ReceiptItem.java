package com.company.data.document.subitem;




import com.company.data.tag.FiscalDocumentTag;
import com.company.data.tag.annotation.PropertyTag;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Позиция в чеке
 */
public class ReceiptItem {
    /**
     * Наименование позиции
     * tag: 1030
     */
    @PropertyTag(value = FiscalDocumentTag.MERCHANDISE_NAME, protocolVersion = {"1.0", "1.1"})
    private String name;

    /**
     * Штрихкод позиции
     * tag: 1080
     */
    @PropertyTag(value = FiscalDocumentTag.MERCHANDISE_BARCODE, protocolVersion = {"1.0", "1.1"})
    private String barCode;

    /**
     * Цена за единицу
     * tag: 1079
     */
    private BigDecimal price = BigDecimal.ZERO;
    @PropertyTag(value = FiscalDocumentTag.MERCHANDISE_PRICE, protocolVersion = {"1.0", "1.1"})
    public void setReceiptPriceFromTags(long val) {
        price = BigDecimal.valueOf(val).divide(BigDecimal.valueOf(100));
    }

    /**
     * Кол-во
     * tag: 1023
     */
    private BigDecimal quantity = BigDecimal.ZERO;
    @PropertyTag(value = FiscalDocumentTag.MERCHANDISE_QUANTITY, protocolVersion = {"1.0", "1.1"})
    public void setReceiptQuantityFromTags(long val) {
        quantity = BigDecimal.valueOf(val).divide(BigDecimal.valueOf(1000));
    }

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
     * скидка/наценка
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
     * признак системы налогообложения
     * tag: 1055
     */
    @PropertyTag(value = FiscalDocumentTag.TAX_SYSTEM_SIGN, protocolVersion = {"1.0"})
    private String taxSystemSign;

    /**
     * налоги
     * tag: 1033
     */
    @PropertyTag(value = FiscalDocumentTag.TAXES, protocolVersion = {"1.0"})
    private List<Tax> taxes;

    /**
     * общая стоимость позиции с учетом скидок и наценок
     * tag: 1043
     */
    private BigDecimal sum = BigDecimal.ZERO;
    @PropertyTag(value = FiscalDocumentTag.RECEIPT_SUM, protocolVersion = {"1.0", "1.1"})
    public void setReceiptSumFromTags(long val) {
        sum = BigDecimal.valueOf(val).divide(BigDecimal.valueOf(100));
    }


    public ReceiptItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
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

    public String getTaxSystemSign() {
        return taxSystemSign;
    }

    public void setTaxSystemSign(String taxSystemSign) {
        this.taxSystemSign = taxSystemSign;
    }

    public List<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Tax> taxes) {
        this.taxes = taxes;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
