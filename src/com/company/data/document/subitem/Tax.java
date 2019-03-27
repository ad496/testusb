package com.company.data.document.subitem;



import com.company.data.tag.FiscalDocumentTag;
import com.company.data.tag.annotation.PropertyTag;

import java.math.BigDecimal;

/**
 * Налоги
 */
public class Tax {
    /**
     * Наименование налога
     * tag: 1065
     */
    @PropertyTag(value = FiscalDocumentTag.TAX_NAME, protocolVersion = {"1.0", "1.1"})
    private String name;

    /**
     * Ставка налога
     * tag: 1070
     */
    @PropertyTag(value = FiscalDocumentTag.TAX_RATE, protocolVersion = {"1.0", "1.1"})
    private BigDecimal rate = BigDecimal.ZERO;

    /**
     * Сумма налога
     * tag: 1072
     */
    @PropertyTag(value = FiscalDocumentTag.TAX_SUM, protocolVersion = {"1.0", "1.1"})
    private BigDecimal sum = BigDecimal.ZERO;

    public Tax(String name, long rate, long sum) {
        this.name = name;
        this.rate = BigDecimal.valueOf(rate);
        this.sum = BigDecimal.valueOf(sum);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
