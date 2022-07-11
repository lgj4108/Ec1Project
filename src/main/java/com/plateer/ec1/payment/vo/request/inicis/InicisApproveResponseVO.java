package com.plateer.ec1.payment.vo.request.inicis;

import lombok.Data;

@Data
public class InicisApproveResponseVO {
    private String resultCode;
    private String resultMsg;
    private String tid;
    private String authDate;
    private String authTime;
    private String vacct;
    private String vacctName;
    private String vacctBankCode;
    private String validDate;
    private String validTime;
    private Long price;
}
