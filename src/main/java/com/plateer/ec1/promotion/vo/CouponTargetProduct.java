package com.plateer.ec1.promotion.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponTargetProduct extends ProductRequest {

    public CouponTargetProduct(ProductRequest reqProduct) {
        this.setGoodsNo(reqProduct.getGoodsNo());
        this.setItemNo(reqProduct.getItemNo());
        this.setPrice(reqProduct.getPrice());
    }

    private List<CouponInfoResponseVO> applyCoupons;

    private Predicate<List<PromotionApplyTarget>> goodsPredicate = (tl) -> {
        if (tl.stream().filter(t -> "10".equals(t.getAplyTgtCcd())).count() > 0) {
            return tl.stream().anyMatch(t -> "10".equals(t.getAplyTgtCcd()) && this.getGoodsNo().equals(t.getAplyTgtNo()));
        }

        return true;
    };

    private Predicate<List<PromotionApplyTarget>> specialPredicate = (tl) -> {
        if (tl.stream().filter(t -> "20".equals(t.getAplyTgtCcd())).count() > 0) {
            return tl.stream().anyMatch(t -> "20".equals(t.getAplyTgtCcd()) && this.getSpecialNo().equals(t.getAplyTgtNo()));
        }

        return true;
    };

    private Predicate<List<PromotionApplyTarget>> categoryPredicate = (tl) -> {
        if (tl.stream().filter(t -> "30".equals(t.getAplyTgtCcd())).count() > 0) {
            return tl.stream().anyMatch(t -> "30".equals(t.getAplyTgtCcd()) && this.getCategoryNo().equals(t.getAplyTgtNo()));
        }

        return true;
    };

    private Predicate<List<PromotionApplyTarget>> companyPredicate = (tl) -> {
        if (tl.stream().filter(t -> "40".equals(t.getAplyTgtCcd())).count() > 0) {
            return tl.stream().anyMatch(t -> "40".equals(t.getAplyTgtCcd()) && this.getCompanyNo().equals(t.getAplyTgtNo()));
        }

        return true;
    };

    private Predicate<CouponInfo> applyPrediCate = (p) ->
            p.getMinPurAmt() <= this.getPrice()
                    && goodsPredicate.test(p.getApplyTargetList())
                    && specialPredicate.test(p.getApplyTargetList())
                    && categoryPredicate.test(p.getApplyTargetList())
                    && companyPredicate.test(p.getApplyTargetList());
}
