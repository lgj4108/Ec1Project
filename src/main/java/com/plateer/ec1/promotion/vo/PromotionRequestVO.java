package com.plateer.ec1.promotion.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@Alias("promotionRequestVO")
public class PromotionRequestVO {
    private String mbrNo;
    private List<ProductRequest> productList;


}
