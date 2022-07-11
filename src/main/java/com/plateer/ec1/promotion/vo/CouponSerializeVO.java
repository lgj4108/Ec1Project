package com.plateer.ec1.promotion.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;

@Data
@Alias("couponSerializeVO")
public class CouponSerializeVO extends CouponInfo {
    private String goodsNo;
    private Integer ItemNo;
    private String specialNo;
    private String categoryNo;
    private String companyNo;
    private Long price;

    private Long dcAmt;
    private String maxDcYn = "N";

    public void calculateDcAmt() {
        Double originalDcAmt = "10".equals(this.getDcCcd()) ? calcFixDcAmt(price) : calcRateDcAmt(price);
        Double maxDcAmt = originalDcAmt > this.getMaxDcAmt().doubleValue() ? this.getMaxDcAmt().doubleValue() : originalDcAmt;
        this.setDcAmt(BigDecimal.valueOf(maxDcAmt).setScale(-1, RoundingMode.DOWN).longValue());
    }

    private Double calcFixDcAmt(Long price) {
        return this.getDcVal().doubleValue() <= price.doubleValue() ? this.getDcVal().doubleValue() : price.doubleValue();
    }

    private Double calcRateDcAmt(Long price) {
        return (price.doubleValue() * this.getDcVal().doubleValue()) / 100;
    }
}
