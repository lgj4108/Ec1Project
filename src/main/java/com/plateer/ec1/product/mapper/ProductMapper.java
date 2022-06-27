package com.plateer.ec1.product.mapper;

import com.plateer.ec1.product.vo.ProductInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Mapper
@Alias("productMapper")
public interface ProductMapper {
    List<ProductInfoVO> getProductList();
}
