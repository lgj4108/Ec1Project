package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.promotion.vo.base.CouponResponseBase;
import com.plateer.ec1.promotion.vo.base.PromotionResponseBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCouponResponseVO extends PromotionResponseBase {
    private List<CouponTargetProduct> targetProducts;

    public ProductCouponResponseVO(String mbrNo, List<CouponTargetProduct> setPromotionByProduct) {
        super(mbrNo);
        this.targetProducts = setPromotionByProduct;
    }

    public void setMaxBenefitYn() {

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CouponTargetProduct extends PromotionRequestVO.ProductRequest {

        public CouponTargetProduct(PromotionRequestVO.ProductRequest reqProduct) {
            this.setGoodsNo(reqProduct.getGoodsNo());
            this.setItemNo(reqProduct.getItemNo());
            this.setPrice(reqProduct.getPrice());
        }

        private List<CouponInfoResponse> applyCoupons;

        private Predicate<List<CouponInfo.PromotionApplyTarget>> goodsPredicate = (tl) -> {
            if(tl.stream().filter(t -> "10".equals(t.getAplyTgtCcd())).count() > 0) {
                return tl.stream().anyMatch(t -> "10".equals(t.getAplyTgtCcd()) && this.getGoodsNo().equals(t.getAplyTgtNo()));
            }

            return true;
        };

        private Predicate<List<CouponInfo.PromotionApplyTarget>> specialPredicate = (tl) -> {
            if(tl.stream().filter(t -> "20".equals(t.getAplyTgtCcd())).count() > 0) {
                return tl.stream().anyMatch(t -> "20".equals(t.getAplyTgtCcd()) && this.getSpecialNo().equals(t.getAplyTgtNo()));
            }

            return true;
        };

        private Predicate<List<CouponInfo.PromotionApplyTarget>> categoryPredicate = (tl) -> {
            if(tl.stream().filter(t -> "30".equals(t.getAplyTgtCcd())).count() > 0) {
                return tl.stream().anyMatch(t -> "30".equals(t.getAplyTgtCcd()) && this.getCategoryNo().equals(t.getAplyTgtNo()));
            }

            return true;
        };

        private Predicate<List<CouponInfo.PromotionApplyTarget>> companyPredicate = (tl) -> {
            if(tl.stream().filter(t -> "40".equals(t.getAplyTgtCcd())).count() > 0) {
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

        public void setApplyCoupons(List<CouponInfo> promotions) {
            this.applyCoupons = promotions.stream()
                    .filter(applyPrediCate)
                    .map(p -> {
                        CouponInfoResponse promo = new CouponInfoResponse();
                        BeanUtils.copyProperties(p, promo);
                        promo.calculateDcAmt(this.getPrice());
                        return promo;
                    })
                    .collect(Collectors.toList());
        }
    }

    @Data
    public static class CouponInfoResponse extends CouponResponseBase {
        private Long dcAmt;
        private String maxDcYn = "N";

        public void calculateDcAmt(Long price) {
            Long dcAmt = "10".equals(this.getDcCcd()) ? calcFixDcAmt(price) : calcRateDcAmt(price);

            this.dcAmt = dcAmt > this.getMaxDcAmt() ? this.getMaxDcAmt() : dcAmt;
        }

        private Long calcFixDcAmt(Long price) {
            return this.getDcVal() <= price ? this.getDcVal() : price;
        }

        private Long calcRateDcAmt(Long price) {
            return (price * this.getDcVal()) / 100;
        }
    }
}
