package com.plateer.ec1.order.context;

import com.plateer.ec1.aspect.annotation.OrderHistory;
import com.plateer.ec1.order.vo.OrderInsertVO;
import com.plateer.ec1.order.vo.OrderRequestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderContext {

    @OrderHistory
    public OrderInsertVO excute(OrderRequestVO requestVO) {
        log.info("주문생성 시작");

        OrderInsertVO orderInsertVO = new OrderInsertVO();

        try {
            // 주문 상품 조회

            // 유효성 검사

            // 데이터 생성

            // 데이터 입력

            // 주문금액 검증

            // 결제

            // 후처리

        } catch (Exception ex) {
            throw ex;
        }

        return orderInsertVO;
    }
}
