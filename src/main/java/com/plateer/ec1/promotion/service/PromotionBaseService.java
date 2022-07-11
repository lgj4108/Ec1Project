package com.plateer.ec1.promotion.service;

import com.plateer.ec1.promotion.enums.CouponKindCode;
import com.plateer.ec1.promotion.enums.PromotionKindCode;
import com.plateer.ec1.promotion.vo.CouponInfo;
import com.plateer.ec1.promotion.vo.CouponSerializeVO;
import com.plateer.ec1.promotion.vo.PromotionRequestVO;

import java.util.List;

public interface PromotionBaseService {
    List<CouponInfo> getPromotionData(String mbrNo);

    List<CouponSerializeVO> getAllPromotionDataWithProduct(PromotionRequestVO req);

    List<CouponSerializeVO> getAvailablePromotionData(List<CouponSerializeVO> list, PromotionKindCode prmKindCd, CouponKindCode cpnKindCd);
}
