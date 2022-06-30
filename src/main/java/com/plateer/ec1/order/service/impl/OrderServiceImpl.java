package com.plateer.ec1.order.service.impl;

import com.plateer.ec1.order.context.OrderContext;
import com.plateer.ec1.order.mapper.OrderMapper;
import com.plateer.ec1.order.service.OrderService;
import com.plateer.ec1.order.vo.OrderRequestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderContext orderContext;

    /**
     * 주문번호 채번
     * @return
     */
    @Override
    public String getOrdNoNextval() {
        return orderMapper.getOrdNoNextval();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void order(OrderRequestVO request) {
        orderContext.excute(request);
    }
}
