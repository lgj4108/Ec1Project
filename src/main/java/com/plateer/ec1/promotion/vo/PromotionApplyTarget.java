package com.plateer.ec1.promotion.vo;

import lombok.Data;

@Data
public class PromotionApplyTarget {
    private String aplyTgtCcd;
    private String aplyTgtNo;
    private String useYn;

    public boolean isTarget(ProductRequest product) {
        return false;
    }
}
