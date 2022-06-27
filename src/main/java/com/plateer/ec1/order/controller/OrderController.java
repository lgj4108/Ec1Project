package com.plateer.ec1.order.controller;

import com.plateer.ec1.order.service.OrderService;
import com.plateer.ec1.order.vo.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public void registOrder(OrderRequest orderRequest) {
        orderService.order(orderRequest);
    }
}
