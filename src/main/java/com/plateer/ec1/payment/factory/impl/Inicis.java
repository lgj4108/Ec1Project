package com.plateer.ec1.payment.factory.impl;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.PaymentProcessor;
import com.plateer.ec1.payment.vo.PaymentResultVO;
import org.springframework.stereotype.Component;

@Component
public class Inicis implements PaymentProcessor {

    @Override
    public PaymentType getType() {
        return PaymentType.INICIS;
    }

    @Override
    public PaymentResultVO approve() {
        return null;
    }
}
