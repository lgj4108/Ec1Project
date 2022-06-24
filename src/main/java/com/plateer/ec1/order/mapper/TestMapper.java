package com.plateer.ec1.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface TestMapper {
    @Transactional
    int test();
}
