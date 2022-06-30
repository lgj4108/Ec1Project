package com.plateer.ec1.common.model.order;

import com.google.gson.JsonObject;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("opOrdClmMntLog")
public class OpOrdClmMntLog {
    private Long logSeq;
    private String ordNo;
    private String clmNo;
    private String reqPram;
    private String insData;
    private String uptData;
    private String sysRegrId = "admin";
    private String sysModrId = "admin";
    private String procCcd;


}
