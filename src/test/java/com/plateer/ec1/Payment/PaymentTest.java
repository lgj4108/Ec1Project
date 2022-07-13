package com.plateer.ec1.Payment;


import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.BaseRequestVO;
import com.plateer.ec1.payment.vo.PaymentRequestVO;
import com.plateer.ec1.payment.vo.inicis.vitualaccount.InicisApproveRequestVO;
import com.plateer.ec1.payment.vo.inicis.vitualaccount.InicisVirtualApproveResponseVO;
import com.plateer.ec1.utils.CipherUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.DatatypeConverter;
import java.net.InetSocketAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
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

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String payUrl = env.getProperty("inicis.vacct.url");

        InicisApproveRequestVO vo = new InicisApproveRequestVO();
        vo.setType("Pay");
        vo.setPaymethod("Vacct");
        vo.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")));
        vo.setClientIp(request.getRemoteAddr());
        vo.setMid("INIpayTest");
        vo.setUrl("http://localhost");
        vo.setMoid("0111213354");
        vo.setGoodName("상품1");
        vo.setBuyerName("test");
        vo.setBuyerEmail("lgj4108@gmail.com");
        vo.setPrice(1L);
        vo.setBankCode("03");
        vo.setDtInput(LocalDateTime.now().plusDays(1L).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        vo.setTmInput(LocalDateTime.now().plusDays(1L).format(DateTimeFormatter.ofPattern("hhmm")));
        vo.setNmInput("이광진");
        vo.setHashData(getHashData(vo));

        ResponseEntity<InicisVirtualApproveResponseVO> response = restTemplate.exchange(payUrl, HttpMethod.POST, getHttpEntity(vo), InicisVirtualApproveResponseVO.class);

        InicisVirtualApproveResponseVO responseVO = response.getBody();
        log.info("code: {}, msg: {}", responseVO.getResultCode(), responseVO.getResultMsg());
        log.info("result: {}", responseVO);
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

    String getHashData(InicisApproveRequestVO vo) {
        String hashBase = "ItEQKi3rY7uvDS8l" + vo.getType() + vo.getPaymethod() + vo.getTimestamp() + vo.getClientIp() + vo.getMid() + vo.getMoid() + vo.getPrice();
        log.info("hashBase: {}", hashBase);
        return CipherUtils.encrypt(hashBase);
    }

    @Test
    @DisplayName("암호화 테스트")
    void encryptTest() throws NoSuchAlgorithmException {
        String text = "test";
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(text.getBytes());
        byte[] digest = md.digest();
        log.info("digest: {}", digest);

        String hexEncrypt = DatatypeConverter.printHexBinary(digest);
        log.info("encrypt text: {}", hexEncrypt);

        assertThat(hexEncrypt.toLowerCase()).isEqualTo("ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff");
    }
}
