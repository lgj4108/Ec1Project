package com.plateer.ec1.promotion.enums;

public enum CouponKindCode {
    PRODUCT("10"),
    CART("20"),
    ;

    CouponKindCode(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }
}
