package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.promotion.vo.base.CouponResponseBase;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class CouponInfoResponseVO extends CouponResponseBase {
    private Long dcAmt;
    private String maxDcYn;
}
