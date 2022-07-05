package com.plateer.ec1.promotion.service;

import com.plateer.ec1.promotion.vo.ProductCouponResponseVO;
import com.plateer.ec1.promotion.vo.PromotionRequestVO;

public interface PromotionService {

    ProductCouponResponseVO getProductCouponApplyData(PromotionRequestVO request);
}
