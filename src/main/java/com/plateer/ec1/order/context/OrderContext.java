package com.plateer.ec1.order.context;

import com.plateer.ec1.aspect.annotation.OrderHistory;
import com.plateer.ec1.order.vo.OrderRequestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderContext {

    @OrderHistory
    public OrderRequestVO excute(OrderRequestVO requestVO) {
        log.info("주문생성 시작");
        
        return requestVO;
    }
}
