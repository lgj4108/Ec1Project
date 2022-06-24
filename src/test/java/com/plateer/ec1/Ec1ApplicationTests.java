package com.plateer.ec1;

import com.plateer.ec1.order.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class Ec1ApplicationTests {

    @Autowired
    TestMapper test;

    @Test
    public void getTest() {
        test.test();
    }

}
