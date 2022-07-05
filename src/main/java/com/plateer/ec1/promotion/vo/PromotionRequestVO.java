package com.plateer.ec1.promotion.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class PromotionRequestVO {
    private String mbrNo;
    private List<ProductRequest> productList;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductRequest {
        private String goodsNo;
        private Integer ItemNo;
        private Long price;
        private String categoryNo;
        private String companyNo;
        private String specialNo;
    }
}
