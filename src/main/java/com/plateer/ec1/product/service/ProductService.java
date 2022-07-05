package com.plateer.ec1.product.service;

import com.plateer.ec1.product.vo.ProductInfoVO;

import java.util.List;

public interface ProductService {
    List<ProductInfoVO> getProductList();

    List<ProductInfoVO> getProductList(List<ProductInfoVO> param);
}
