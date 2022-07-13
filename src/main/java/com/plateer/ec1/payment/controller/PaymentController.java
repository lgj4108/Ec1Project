package com.plateer.ec1.payment.controller;

import com.plateer.ec1.payment.vo.PaymentRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @PostMapping("/approve")
    public void payApprove(@RequestBody PaymentRequestVO reqVO) {
        log.info("req : {} ", reqVO);
    }

    @PostMapping("/confirm")
    public void confirm(HttpServletRequest request, @RequestBody PaymentRequestVO reqVO) {
        if("123.123.215".equals(request.getRemoteAddr())) {

        }
        log.info("req: {}", reqVO);
    }
}
