package com.plateer.ec1.delivery.controller;

import com.plateer.ec1.delivery.service.DeliverySerivce;
import com.plateer.ec1.delivery.vo.DeliveryCostVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliverySerivce deliverySerivce;

    @GetMapping("getDeliveryCost")
    public List<DeliveryCostVO> getDeliveryCost() {
        return deliverySerivce.getDeliveryCost();
    }
}
