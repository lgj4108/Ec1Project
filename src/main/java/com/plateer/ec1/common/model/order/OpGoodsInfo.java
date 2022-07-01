package com.plateer.ec1.common.model.order;

import com.plateer.ec1.common.model.BaseModel;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("opGoodsInfo")
public class OpGoodsInfo extends BaseModel {
    private String ordNo;
    private String ordGoodsNo;
    private String ordItemNo;
    private String goodsSellTpCd;
    private String goodsDlvTpCd;
    private String goodsNm;
    private String itemNm;
    private Long sellAmt;
    private Long sellDcAmt;
}
