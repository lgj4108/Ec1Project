package com.plateer.ec1.promotion;

import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.service.PromotionService;
import com.plateer.ec1.promotion.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


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
    void getPromotionData() {
        PromotionRequestVO param = new PromotionRequestVO();
        param.setMbrNo("test01");

        List<ProductRequest> productList = new ArrayList<>();
        productList.add(new ProductRequest("P001", 1, 29900L, "", "", ""));
        productList.add(new ProductRequest("P002", 1, 10900L, "", "", ""));
        param.setProductList(productList);

        List<CouponSerializeVO> datas = promotionMapper.getAllPromotionDataWithProduct(param);

        assertThat(datas.stream().filter(d -> d.getCpnKindCd().equals("10")).count()).isEqualTo(1L);
    }


    @Test
    public void productCouponTest() {
        PromotionRequestVO param = new PromotionRequestVO();
        param.setMbrNo("test01");

        List<ProductRequest> productList = new ArrayList<>();
        productList.add(new ProductRequest("P001", 1, 29900L, "", "", ""));
        productList.add(new ProductRequest("P002", 1, 10900L, "", "", ""));
        param.setProductList(productList);


        ProductCouponResponseVO productCouponApplyData = promotionService.getProductCouponApplyData(param);

        assertThat(productCouponApplyData.getMbrNo()).isNotBlank();
        assertThat(productCouponApplyData.getTargetProducts().size()).isEqualTo(1);
        assertThat(productCouponApplyData.getTargetProducts().stream()
                .map(CouponTargetProduct::getApplyCoupons).count()).isEqualTo(1L);

        log.info("testData: {}", productCouponApplyData);
    }


    @Test
    public void productCouponTest2() {
        PromotionRequestVO param = new PromotionRequestVO();
        param.setMbrNo("test02");

        List<ProductRequest> productList = new ArrayList<>();
        productList.add(new ProductRequest("P001", 1, 29000L, "", "", ""));
        productList.add(new ProductRequest("P001", 2, 29000L, "", "", ""));
        productList.add(new ProductRequest("P002", 1, 20500L, "", "", ""));
        productList.add(new ProductRequest("P002", 2, 10250L, "", "", ""));
        productList.add(new ProductRequest("P005", 1, 9000L, "", "", ""));
        productList.add(new ProductRequest("P005", 2, 9000L, "", "", ""));
        productList.add(new ProductRequest("P005", 3, 9000L, "", "", ""));
        productList.add(new ProductRequest("P006", 0, 140000L, "", "", ""));
        productList.add(new ProductRequest("P007", 1, 24000L, "", "", ""));
        productList.add(new ProductRequest("P007", 2, 48000L, "", "", ""));
        productList.add(new ProductRequest("P007", 3, 24000L, "", "", ""));
        param.setProductList(productList);

        ProductCouponResponseVO productCouponApplyData = promotionService.getProductCouponApplyData(param);

        assertThat(productCouponApplyData.getMbrNo()).isNotBlank();

        log.info("testData: {}", productCouponApplyData);
    }

    @Test
    void cutTest() {

        int value = 10111;
        long longValue = BigDecimal.valueOf(value).setScale(-1, RoundingMode.DOWN).longValue();

        log.info("cut value : {}", longValue);
        assertThat(longValue).isLessThan(value);
        assertThat(longValue).isEqualTo(value - 1);
    }
}
