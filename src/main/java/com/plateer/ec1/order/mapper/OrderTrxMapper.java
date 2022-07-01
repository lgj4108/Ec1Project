package com.plateer.ec1.order.mapper;

import com.plateer.ec1.common.model.order.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderTrxMapper {
    int insertOrderHistory(OpOrdClmMntLog opOrdClmMntLog);

    void updateOrderHistory(OpOrdClmMntLog opOrdClmMntLog);

    int insertOpOrdBase(OpOrdBase opOrdBase);

    int insertOpGoodsInfo(OpGoodsInfo opGoodsInfo);

    int insertOpClmInfo(OpClmInfo opClmInfo);

    int insertOpDvpAreaInfo(OpDvpAreaInfo opDvpAreaInfo);

    int insertOpOrdCostInfo(OpOrdCostInfo opOrdCostInfo);

    int insertOpDvpInfo(OpDvpInfo opDvpInfo);

    int insertOpOrdBnfInfo(OpOrdBnfInfo opOrdBnfInfo);

    int insertOpOrdBnfRelInfo(OpOrdBnfRelInfo opOrdBnfRelInfo);
}
