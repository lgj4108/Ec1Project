package com.plateer.ec1.promotion.service.impl;

import com.plateer.ec1.promotion.calculator.CalculatorFactory;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.service.PromotionService;
import com.plateer.ec1.promotion.vo.ProductCouponResponseVO;
import com.plateer.ec1.promotion.vo.PromotionRequestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PromotionServiceImpl implements PromotionService {

    private final CalculatorFactory factory;

    @Override
    public ProductCouponResponseVO getProductCouponApplyData(PromotionRequestVO request) {
        return (ProductCouponResponseVO) factory.getFactory(PromotionType.PRODUCT).getAvailablePromotion(request);
    }
}
