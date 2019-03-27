package com.company.data.document.subitem;




import com.company.data.tag.FiscalDocumentTag;
import com.company.data.tag.annotation.PropertyTag;

import java.math.BigDecimal;

/**
 * Скидки/наценки
 */
public class SaleOrMarkup {
    /**
     * наименование скидки
     * tag: 1113
     */
    @PropertyTag(value = FiscalDocumentTag.SALE_NAME, protocolVersion = {"1.0", "1.1"})
    private String saleName;

    /**
     * наименование наценки
     * tag: 1114
     */
    @PropertyTag(value = FiscalDocumentTag.MARKUP_NAME, protocolVersion = {"1.0", "1.1"})
    private String markupName;

    /**
     * скидка (ставка)
     * tag: 1063
     */
    private BigDecimal saleRate;
    @PropertyTag(value = FiscalDocumentTag.SALE_RATE, protocolVersion = {"1.0", "1.1"})
    public void setSaleRateFromTags(long val) {
        saleRate = BigDecimal.valueOf(val).divide(BigDecimal.valueOf(100));
    }

    /**
     * наценка (ставка)
     * tag: 1034
     */
    private BigDecimal markupRate;
    @PropertyTag(value = FiscalDocumentTag.MARKUP_RATE, protocolVersion = {"1.0", "1.1"})
    public void setMarkupRateFromTags(long val) {
        markupRate = BigDecimal.valueOf(val).divide(BigDecimal.valueOf(100));
    }

    /**
     * скидка (сумма)
     * tag: 1064
     */
    @PropertyTag(value = FiscalDocumentTag.SALE_SUM, protocolVersion = {"1.0", "1.1"})
    private long saleSum;

    /**
     * наценка (сумма)
     * tag: 1035
     */
    @PropertyTag(value = FiscalDocumentTag.MARKUP_SUM, protocolVersion = {"1.0", "1.1"})
    private long markupSum;


    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getMarkupName() {
        return markupName;
    }

    public void setMarkupName(String markupName) {
        this.markupName = markupName;
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
}
