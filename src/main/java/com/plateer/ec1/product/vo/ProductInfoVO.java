package com.plateer.ec1.product.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Alias("productInfoVO")
public class ProductInfoVO {
    private String goodsNm;
    private String goodsTpCd;
    private Long salePrc;
    private Long prmPrc;
    private String prgsStatCd;
    private String goodsDlvTpCd;
    private String goodsNo;
    private String itemNo;
    private String itemNm;
}
