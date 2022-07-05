package com.plateer.ec1.promotion;

import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.service.PromotionService;
import com.plateer.ec1.promotion.vo.CouponInfo;
import com.plateer.ec1.promotion.vo.ProductCouponResponseVO;
import com.plateer.ec1.promotion.vo.PromotionRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class PromotionTest {

    @Autowired
    PromotionMapper promotionMapper;

    @Autowired
    PromotionService promotionService;

    @Test
    public void getDataTest() {
        List<CouponInfo> couponInfos = promotionMapper.getPromotionData("test01");
        log.info("log: {}", couponInfos);
    }

    @Test
    public void productCouponTest() {
        PromotionRequestVO param = new PromotionRequestVO();
        param.setMbrNo("test01");

        List<PromotionRequestVO.ProductRequest> productList = new ArrayList<>();
        productList.add(new PromotionRequestVO.ProductRequest("P001", 1, 29900L, "", "", ""));
        productList.add(new PromotionRequestVO.ProductRequest("P002", 1, 10900L, "", "", ""));
        param.setProductList(productList);
        ProductCouponResponseVO productCouponApplyData = promotionService.getProductCouponApplyData(param);

        log.info("testData: {}", productCouponApplyData);

    }
}
