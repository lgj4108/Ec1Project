package com.plateer.ec1.promotion.calculator.impl;

import com.plateer.ec1.promotion.calculator.PromotionCalculator;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.vo.PromotionRequestVO;
import com.plateer.ec1.promotion.vo.base.PromotionResponseBase;
import org.springframework.stereotype.Component;

@Component
public class CartCouponCalculator implements PromotionCalculator {
    @Override
    public PromotionType getType() {
        return PromotionType.CART;
    }

    @Override
    public PromotionResponseBase getCalculationData(PromotionRequestVO requestVO) {
        return null;
    }
}
