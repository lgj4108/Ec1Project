package com.plateer.ec1.product.service.impl;

import com.plateer.ec1.product.mapper.ProductMapper;
import com.plateer.ec1.product.service.ProductService;
import com.plateer.ec1.product.vo.ProductInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    @Override
    public List<ProductInfoVO> getProductList() {
        return productMapper.getProductList();
    }
}
