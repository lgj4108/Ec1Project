package com.plateer.ec1.promotion.calculator;

import com.plateer.ec1.promotion.enums.PromotionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class CalculatorFactory {
    private final List<PromotionCalculator> promotionCalculators;
    private Map<PromotionType, PromotionCalculator> calculatorMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        this.promotionCalculators.forEach(c -> calculatorMap.put(c.getType(), c));
    }

    public PromotionCalculator getFactory(PromotionType type) {
        return calculatorMap.get(type);
    }
}
