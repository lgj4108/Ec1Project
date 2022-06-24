package com.plateer.ec1.order.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("orderRequest")
public class OrderRequest {
    private String ordNo;
}
