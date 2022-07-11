package com.plateer.ec1.promotion.calculator;

import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.vo.CouponSerializeVO;
import com.plateer.ec1.promotion.vo.PromotionRequestVO;
import com.plateer.ec1.promotion.vo.base.PromotionResponseBase;

import java.util.Comparator;

public interface PromotionCalculator {

    PromotionType getType();

    PromotionResponseBase getCalculationData(PromotionRequestVO requestVO);
}
