package com.plateer.ec1.delivery.service.impl;

import com.plateer.ec1.delivery.service.DeliverySerivce;
import com.plateer.ec1.delivery.vo.DeliveryCostVO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliverySerivce {
    @Override
    public List<DeliveryCostVO> getDeliveryCost() {
        return Arrays.asList(new DeliveryCostVO(1, "10", 0L, 0L));
    }
}
