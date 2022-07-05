package com.plateer.ec1.promotion.vo.base;

import lombok.Data;

@Data
public class CouponResponseBase {
    private Long cpnIssNo;
    private Long prmNo;
    private String cpnKindCd;
    private String degrCcd;
    private String dcCcd;
    private Integer dcVal;
    private Integer minPurAmt;
    private Integer maxDcAmt;
}
