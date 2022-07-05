package com.plateer.ec1.promotion.service.impl;

import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.service.PromotionBaseService;
import com.plateer.ec1.promotion.vo.CouponInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PromotionBaseServiceImpl implements PromotionBaseService {

    private final PromotionMapper promotionMapper;

    @Override
    public List<CouponInfo> getPromotionData(String mbrNo) {
        return promotionMapper.getPromotionData(mbrNo);
    }
}
