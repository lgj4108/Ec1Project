package com.plateer.ec1.payment.factory;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.PaymentResultVO;

public interface PaymentProcessor {
    PaymentType getType();

    PaymentResultVO approve();
}
