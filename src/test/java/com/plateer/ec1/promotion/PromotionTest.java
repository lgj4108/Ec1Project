package com.plateer.ec1.promotion;

import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.service.PromotionService;
import com.plateer.ec1.promotion.vo.CouponInfo;
import com.plateer.ec1.promotion.vo.ProductCouponResponseVO;
import com.plateer.ec1.promotion.vo.PromotionRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public void productCouponTest() {
        PromotionRequestVO param = new PromotionRequestVO();
        param.setMbrNo("test01");

        List<PromotionRequestVO.ProductRequest> productList = new ArrayList<>();
        productList.add(new PromotionRequestVO.ProductRequest("P001", 1, 29900L, "", "", ""));
        productList.add(new PromotionRequestVO.ProductRequest("P002", 1, 10900L, "", "", ""));
        param.setProductList(productList);


        ProductCouponResponseVO productCouponApplyData = promotionService.getProductCouponApplyData(param);

        assertThat(productCouponApplyData.getMbrNo()).isNotBlank();
        assertThat(productCouponApplyData.getTargetProducts().size()).isEqualTo(1);
        assertThat(productCouponApplyData.getTargetProducts().stream()
                .map(ProductCouponResponseVO.CouponTargetProduct::getApplyCoupons).count()).isEqualTo(1L);

        log.info("testData: {}", productCouponApplyData);
    }


    @Test
    public void productCouponTest2() {
        PromotionRequestVO param = new PromotionRequestVO();
        param.setMbrNo("test02");

        List<PromotionRequestVO.ProductRequest> productList = new ArrayList<>();
        productList.add(new PromotionRequestVO.ProductRequest("P001", 1, 29000L, "", "", ""));
        productList.add(new PromotionRequestVO.ProductRequest("P001", 2, 58000L, "", "", ""));
        productList.add(new PromotionRequestVO.ProductRequest("P002", 1, 20500L, "", "", ""));
        productList.add(new PromotionRequestVO.ProductRequest("P002", 2, 10250L, "", "", ""));
        productList.add(new PromotionRequestVO.ProductRequest("P005", 1, 9000L, "", "", ""));
        productList.add(new PromotionRequestVO.ProductRequest("P005", 2, 9000L, "", "", ""));
        productList.add(new PromotionRequestVO.ProductRequest("P005", 3, 9000L, "", "", ""));
        productList.add(new PromotionRequestVO.ProductRequest("P006", 0, 140000L, "", "", ""));
        productList.add(new PromotionRequestVO.ProductRequest("P007", 1, 24000L, "", "", ""));
        productList.add(new PromotionRequestVO.ProductRequest("P007", 2, 48000L, "", "", ""));
        productList.add(new PromotionRequestVO.ProductRequest("P007", 3, 24000L, "", "", ""));
        param.setProductList(productList);

        ProductCouponResponseVO productCouponApplyData = promotionService.getProductCouponApplyData(param);

        assertThat(productCouponApplyData.getMbrNo()).isNotBlank();

        log.info("testData: {}", productCouponApplyData);
    }

    private List<ProductCouponResponseVO.CouponTargetProduct> setPromotionByProduct(PromotionRequestVO requestVO) {
        List<CouponInfo> promotions = new ArrayList<>();
        return requestVO.getProductList().stream()
                .map(rp -> {
                    ProductCouponResponseVO.CouponTargetProduct target = new ProductCouponResponseVO.CouponTargetProduct(rp);
                    target.setApplyCoupons(promotions);
                    return target;
                })
                .filter(p -> !CollectionUtils.isEmpty(p.getApplyCoupons()))
                .collect(Collectors.toList());
    }
}
