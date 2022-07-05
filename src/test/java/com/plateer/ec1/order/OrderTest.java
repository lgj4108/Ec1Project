package com.plateer.ec1.order;

import com.google.gson.Gson;
import com.plateer.ec1.common.model.order.OpOrdClmMntLog;
import com.plateer.ec1.order.mapper.OrderTrxMapper;
import com.plateer.ec1.order.service.OrderService;
import com.plateer.ec1.order.vo.OrderRequestVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class OrderTest {

    @Autowired
    OrderTrxMapper orderTrxMapper;

    @Autowired
    OrderService orderService;

    @Test
    public void historyTest() {
        OpOrdClmMntLog param = new OpOrdClmMntLog();
        Map<String, String> test = new HashMap<>();
        test.put("test", "test");
        Gson gson = new Gson();
        param.setReqPram(gson.toJson(test));
        orderTrxMapper.insertOrderHistory(param);
    }

    @Test
    public void serviceTest() {
        OrderRequestVO orderReq = new OrderRequestVO();
        orderReq.setOrdNo(orderService.getOrdNoNextval());

        OrderRequestVO.OrderBaseVO orderBase = new OrderRequestVO.OrderBaseVO();
        orderBase.setMbrNo("");
        orderBase.setOrdNm("");
        orderBase.setOrdTpCd("10");
        orderBase.setOrdSysCcd("10");
//        orderBase.set
        orderReq.setOrderBase(orderBase);

        orderService.order(orderReq);
    }
}
