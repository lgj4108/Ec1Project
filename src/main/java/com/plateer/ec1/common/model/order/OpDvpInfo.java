package com.plateer.ec1.common.model.order;

import com.plateer.ec1.common.model.BaseModel;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("opDvpInfo")
public class OpDvpInfo extends BaseModel {
    private Integer dvGrpNo;
    private String ordNo;
    private Integer dvpSeq;
    private String dvMthdCd;
}
