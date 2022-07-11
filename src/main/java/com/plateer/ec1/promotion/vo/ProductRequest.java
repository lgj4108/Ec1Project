package com.plateer.ec1.promotion.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String goodsNo;
    private Integer ItemNo;
    private Long price;
    private String categoryNo;
    private String companyNo;
    private String specialNo;
}
