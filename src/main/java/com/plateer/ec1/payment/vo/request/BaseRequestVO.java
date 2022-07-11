package com.plateer.ec1.payment.vo.request;

import com.plateer.ec1.payment.enums.PaymentType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class BaseRequestVO {
    @NotNull
    private PaymentType paymentType;
    @NotNull
    private Long payAmt;

    private Map<String, Object> authInfo;
}
