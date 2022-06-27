package com.plateer.ec1.common.model.product;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Alias("prItemInfo")
public class PrItemInfo {
    private String goodsNo;
    private String itemNo;
    private String itemNm;
    private LocalDateTime sysRegDtime;
    private LocalDateTime sysModDtime;
    private String sysRegrId;
    private String sysModrId;

}
