package com.plateer.ec1.common.model.order;

import com.google.gson.JsonObject;
import com.plateer.ec1.common.model.BaseModel;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("opOrdClmMntLog")
public class OpOrdClmMntLog extends BaseModel {
    private Long logSeq;
    private String ordNo;
    private String clmNo;
    private String reqPram;
    private String insData;
    private String uptData;
    private String procCcd;
}
