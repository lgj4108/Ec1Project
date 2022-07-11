package com.plateer.ec1.Payment;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.request.BaseRequestVO;
import com.plateer.ec1.payment.vo.request.PaymentRequestVO;
import com.plateer.ec1.payment.vo.request.inicis.InicisApproveRequestVO;
import com.plateer.ec1.payment.vo.request.inicis.InicisApproveResponseVO;
import jdk.internal.net.http.common.HttpHeadersBuilder;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PaymentTest {

    @Autowired
    Environment env;
    RestTemplate restTemplate;

    Validator validator;

    @BeforeEach
    void init() {
        restTemplate = new RestTemplate();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    @DisplayName("요청 파라메터 유효성검사")
    void requestParamValidationTest() {
        PaymentRequestVO requestVO = new PaymentRequestVO();
        Set<ConstraintViolation<PaymentRequestVO>> validate = validator.validate(requestVO);

        assertThat(validate.size()).isEqualTo(2);

        requestVO.setMbrNo("test01");
        validate = validator.validate(requestVO);

        assertThat(validate.size()).isEqualTo(1);

        requestVO.setOrdNo("102312345");
        validate = validator.validate(requestVO);

        assertThat(validate.size()).isEqualTo(0);

        List<BaseRequestVO> testList = new ArrayList<>();
        BaseRequestVO baseRequestVO = new BaseRequestVO();
        testList.add(baseRequestVO);
        requestVO.setPayInfos(testList);

        validate = validator.validate(requestVO);
        assertThat(validate.size()).isEqualTo(2);

        baseRequestVO.setPaymentType(PaymentType.INICIS);
        testList.add(baseRequestVO);
        requestVO.setPayInfos(testList);

        validate = validator.validate(requestVO);

        assertThat(validate.size()).isEqualTo(1);

        baseRequestVO.setPayAmt(10000L);
        testList.add(baseRequestVO);
        requestVO.setPayInfos(testList);

        validate = validator.validate(requestVO);

        assertThat(validate.size()).isEqualTo(0);
    }
    
    @Test
    void paymentTest() {

        String payUrl = env.getProperty("inicis.vacct.url");

        InicisApproveRequestVO vo = new InicisApproveRequestVO();
        vo.setType("Pay");
        vo.setPaymethod("Vacct");
        vo.setMid("1234567890");
        vo.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")));

        ResponseEntity<InicisApproveResponseVO> response = restTemplate.exchange(payUrl, HttpMethod.POST, getHttpEntity(vo), InicisApproveResponseVO.class);

        InicisApproveResponseVO responseVO = response.getBody();
        assertThat(responseVO)
                .extracting("resultCode")
                .isNotNull()
                .isEqualTo("00");
    }

    HttpEntity getHttpEntity(InicisApproveRequestVO vo) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setHost(new InetSocketAddress("inicis.vacct.url", 443));
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setAccept(Arrays.asList(MediaType.ALL));

        return new HttpEntity(vo.getMap(), httpHeaders);
    }
}
