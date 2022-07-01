package com.plateer.ec1.config;

import com.plateer.ec1.aspect.SystemIdAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanConfig {

    @Bean
    public SystemIdAspect systemIdAspect() {
        return new SystemIdAspect();
    }
}
