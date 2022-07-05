package com.plateer.ec1.promotion.mapper;

import com.plateer.ec1.promotion.vo.CouponInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PromotionMapper {

    List<CouponInfo> getPromotionData(@Param("mbrNo") String mbrNo);
}
