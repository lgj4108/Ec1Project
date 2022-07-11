package com.plateer.ec1.payment.controller;

import com.plateer.ec1.payment.vo.request.PaymentRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @PostMapping("/approve")
    public void payApprove(@RequestBody PaymentRequestVO reqVO) {
        log.info("req : {} ", reqVO);
    }
}
