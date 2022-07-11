package com.plateer.ec1.promotion.service.impl;

import com.plateer.ec1.promotion.enums.CouponKindCode;
import com.plateer.ec1.promotion.enums.PromotionKindCode;
import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.service.PromotionBaseService;
import com.plateer.ec1.promotion.vo.CouponInfo;
import com.plateer.ec1.promotion.vo.CouponSerializeVO;
import com.plateer.ec1.promotion.vo.PromotionRequestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PromotionBaseServiceImpl implements PromotionBaseService {

    private final PromotionMapper promotionMapper;

    @Override
    public List<CouponInfo> getPromotionData(String mbrNo) {
        return promotionMapper.getPromotionData(mbrNo);
    }

    @Override
    public List<CouponSerializeVO> getAllPromotionDataWithProduct(PromotionRequestVO req) {
        return promotionMapper.getAllPromotionDataWithProduct(req);
    }

    @Override
    public List<CouponSerializeVO> getAvailablePromotionData(List<CouponSerializeVO> list, PromotionKindCode prmKindCd, CouponKindCode cpnKindCd) {
        return CollectionUtils.isEmpty(list) ? Collections.emptyList()
                : list.stream()
                .filter(p -> p.isValidBase(prmKindCd, cpnKindCd))
                .collect(Collectors.toList());
    }
}
