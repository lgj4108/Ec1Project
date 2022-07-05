package com.plateer.ec1.promotion.calculator.impl;

import com.plateer.ec1.promotion.calculator.PromotionCalculator;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.service.PromotionBaseService;
import com.plateer.ec1.promotion.vo.CouponInfo;
import com.plateer.ec1.promotion.vo.ProductCouponResponseVO;
import com.plateer.ec1.promotion.vo.PromotionRequestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductCouponCalculator implements PromotionCalculator {

    private final PromotionBaseService promotionBaseService;

    @Override
    public PromotionType getType() {
        return PromotionType.PRODUCT;
    }

    @Override
    public ProductCouponResponseVO getAvailablePromotion(PromotionRequestVO requestVO) {
        return setAvailablePromotion(requestVO);
    }

    private ProductCouponResponseVO setAvailablePromotion(PromotionRequestVO requestVO) {
        return new ProductCouponResponseVO(requestVO.getMbrNo(), setPromotionByProduct(requestVO));
    }

    private List<ProductCouponResponseVO.CouponTargetProduct> setPromotionByProduct(PromotionRequestVO requestVO) {
        final List<CouponInfo> promotions = getAvailablePromotions(requestVO.getMbrNo());
        return requestVO.getProductList().stream()
                .map(rp -> {
                    ProductCouponResponseVO.CouponTargetProduct target = new ProductCouponResponseVO.CouponTargetProduct(rp);
                    target.setApplyCoupons(promotions);
                    return target;
                })
                .collect(Collectors.toList());
    }

    private List<CouponInfo> getAvailablePromotions(String mbrNo) {
        return promotionBaseService.getPromotionData(mbrNo)
                .stream()
                .filter(p -> p.isValidBase("10"))
                .collect(Collectors.toList());
    }


}
