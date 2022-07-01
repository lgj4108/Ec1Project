package com.plateer.ec1.common.model.order;

import com.plateer.ec1.common.model.BaseModel;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("opOrdCostInfo")
public class OpOrdCostInfo extends BaseModel {
    private String ordNo;
    private String dvAmtTpCd;
    private Long orgDvAmt;
    private Long dvBnfAmt;
    private Long aplyDvAmt;
    private String imtnRsnCcd;
    private String dvPlcTpCd;
    private Long cnclDvAmt;
}
