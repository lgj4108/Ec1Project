package com.plateer.ec1.payment.factory;

import com.plateer.ec1.payment.enums.PaymentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentFactory {

    private final List<PaymentProcessor> processors;
    private Map<PaymentType, PaymentProcessor> processorMap;
    @PostConstruct
    public void init() {
        processorMap = new LinkedHashMap<>();

        processors.forEach(p -> processorMap.put(p.getType(), p));
    }

    public PaymentProcessor get(PaymentType type) {
        return processorMap.get(type);
    }
}
