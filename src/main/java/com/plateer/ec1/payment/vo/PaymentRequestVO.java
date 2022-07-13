package com.plateer.ec1.payment.vo;

import com.plateer.ec1.payment.vo.BaseRequestVO;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PaymentRequestVO {
    @NotNull
    private String mbrNo;
    @NotNull
    private String ordNo;

    private List<@NotNull @Valid BaseRequestVO> payInfos;

}
