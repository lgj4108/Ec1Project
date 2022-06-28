package com.plateer.ec1.delivery.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DeliveryCostVO {
    private Integer dvGrpNo;
    private String dvAmtTpCd;
    private Long orgDvAmt;
    private Long dvBnfAmt;
}
