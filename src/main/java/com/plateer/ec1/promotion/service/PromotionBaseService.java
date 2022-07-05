package com.plateer.ec1.promotion.service;

import com.plateer.ec1.promotion.vo.CouponInfo;

import java.util.List;

public interface PromotionBaseService {
    List<CouponInfo> getPromotionData(String mbrNo);
}
