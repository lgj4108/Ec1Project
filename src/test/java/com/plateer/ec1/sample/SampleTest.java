package com.plateer.ec1.sample;

import lombok.Data;

import java.util.List;

public class SampleTest {

    @Data
    public static class BaseVO {
        private String memberNo;
        private List<ProductVO> productVOS;
    }

    @Data
    public static class ProductVO {
        private String productNo;
        private Long price;
    }

    @Data
    public static class CouponResponse {
        private Long couponNo;
        public String type;
        private Long price;
        private Long benefitPrice;

        public void calculateBenefitPrice(Long productPrice) {
            this.benefitPrice = productPrice / this.price;
        }
    }

    @Data
    public static class ProductCouponResponse {
        private ProductVO productVO;
        private List<CouponResponse> coupons;

        public void setCouponInfo(List<CouponResponse> coupons) {
            this.coupons = coupons;
            coupons.forEach(i -> i.calculateBenefitPrice(this.productVO.price));
        }
    }

    @Data
    public static class ResposneVO {
        private String memberNo;
        private List<ProductCouponResponse> productCouponResponses;
    }
}
