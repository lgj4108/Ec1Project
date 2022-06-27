package com.plateer.ec1.common.model.product;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Alias("prGoodsBase")
public class PrGoodsBase {
    private String goodsNm;
    private String goodsTpCd;
    private Long salePrc;
    private Long prmPrc;
    private String prgsStatCd;
    private LocalDateTime sysRegDtime;
    private LocalDateTime sysModDtime;
    private String sysRegrId;
    private String sysModrId;
    private String goodsDlvTpCd;
    private String goodsNo;
}

