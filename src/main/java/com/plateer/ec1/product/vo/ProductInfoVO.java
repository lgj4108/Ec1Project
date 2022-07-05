package com.plateer.ec1.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Alias("productInfoVO")
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoVO {
    public ProductInfoVO (String goodsNo, String itemNo){
        this.goodsNo = goodsNo;
        this.itemNo = itemNo;
    }
    
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
