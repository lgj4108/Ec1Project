package com.plateer.ec1.common.model.order;

import com.plateer.ec1.common.model.BaseModel;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("opDvpAreaInfo")
public class OpDvpAreaInfo extends BaseModel {
    private String ordNo;
    private Integer dvpSeq;
    private String rmtiNm;
    private String rmtiHpNo;
    private String rmtiAddr;
    private String rmtiAddrDtl;
}
