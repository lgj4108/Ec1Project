package com.plateer.ec1.order.service;

import com.plateer.ec1.order.vo.OrderRequestVO;

public interface OrderService {
    String getOrdNoNextval();

    void order(OrderRequestVO request);
}
