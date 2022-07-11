package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.promotion.vo.base.PromotionResponseBase;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductCouponResponseVO extends PromotionResponseBase {
    private List<CouponTargetProduct> targetProducts;

    public ProductCouponResponseVO(String mbrNo, List<CouponTargetProduct> setPromotionByProduct) {
        super(mbrNo);
        this.targetProducts = setPromotionByProduct;
    }

}
