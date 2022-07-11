package com.plateer.ec1.promotion.calculator.impl;

import com.plateer.ec1.promotion.calculator.PromotionCalculator;
import com.plateer.ec1.promotion.enums.CouponKindCode;
import com.plateer.ec1.promotion.enums.PromotionKindCode;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.service.PromotionBaseService;
import com.plateer.ec1.promotion.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ProductCouponResponseVO getCalculationData(PromotionRequestVO requestVO) {
//        List<CouponSerializeVO> promDcInfos = sortPromotionByPolicy();
        setMaxBenefitYn(promotionBaseService.getAvailablePromotionData(promotionBaseService.getAllPromotionDataWithProduct(requestVO), PromotionKindCode.COUPON, CouponKindCode.PRODUCT));
        ProductCouponResponseVO productCouponResponseVO = new ProductCouponResponseVO(requestVO.getMbrNo(), setPromotionByProduct(requestVO));

        return productCouponResponseVO;
    }

    private List<CouponSerializeVO> sortPromotionByPolicy(List<CouponSerializeVO> list) {
        return list.stream()
                .map(p -> {
                    p.calculateDcAmt();
                    return p;
                })
                .sorted(Comparator.comparing(CouponSerializeVO::getPrmNo)
                        .thenComparing(CouponSerializeVO::getCpnIssNo)
                        .thenComparing(CouponSerializeVO::getDcAmt, Comparator.reverseOrder())
                        .thenComparing(CouponSerializeVO::getGoodsNo, Comparator.reverseOrder())
                        .thenComparing(CouponSerializeVO::getItemNo))
                .collect(Collectors.toList());
    }
    private List<CouponSerializeVO> setMaxBenefitYn(List<CouponSerializeVO> list) {
        Map<Long, CouponSerializeVO> applyMaxBenefitYn = new HashMap<>();

        List<CouponSerializeVO> couponSerializeVOS = sortPromotionByPolicy(list);
        return couponSerializeVOS;
    }

    private List<CouponTargetProduct> setPromotionByProduct(PromotionRequestVO requestVO) {
        List<CouponInfo> promotions = getAvailablePromotions(requestVO.getMbrNo());
        return requestVO.getProductList().stream()
                .map(rp -> {
                    CouponTargetProduct target = new CouponTargetProduct(rp);
//                    target.setApplyCoupons(promotions);
                    return target;
                })
                .filter(p -> !CollectionUtils.isEmpty(p.getApplyCoupons()))
                .collect(Collectors.toList());
    }

    private List<CouponInfo> getAvailablePromotions(String mbrNo) {
        return promotionBaseService.getPromotionData(mbrNo)
                .stream()
                .filter(p -> p.isValidBase(PromotionKindCode.COUPON, CouponKindCode.PRODUCT))
                .collect(Collectors.toList());
    }
}
