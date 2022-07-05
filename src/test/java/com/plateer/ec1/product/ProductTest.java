package com.plateer.ec1.product;

import com.plateer.ec1.product.service.ProductService;
import com.plateer.ec1.product.vo.ProductInfoVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductTest {

    @Autowired
    ProductService productService;

    @Test
    public void selectProduct() {
        List<ProductInfoVO> reqProduct = new ArrayList<>();
        reqProduct.add(new ProductInfoVO("P001", "1"));
        productService.getProductList(reqProduct);
    }
}
