package com.plateer.ec1.payment.vo.request.inicis;

import com.plateer.ec1.payment.vo.request.PaymentRequestVO;
import lombok.Data;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;

@Data
public class InicisApproveRequestVO extends PaymentRequestVO {
    private String type;
    private String paymethod;
    private String timestamp;
    private String clientIp;
    private String mid;
    private String url;
    private String moid;
    private String goodName;
    private String buyerName;
    private String buyerEmail;
    private String buyerTel;
    private Long price;
    private String currency;
    private String bankCode;
    private String dtInput;
    private String tmInput;
    private String nmInput;
    private String flgCash;
    private String cashRegNo;
    private String vacctType;
    private String vacct;
    private String hashData;

    public void setAuthInfo(String type, String paymethod, String mid) {
        this.type = type;
        this.paymethod = paymethod;
        this.mid = mid;
    }

    public MultiValueMap getMap() {

        LinkedMultiValueMap params = new LinkedMultiValueMap();
        for(Field f : this.getClass().getDeclaredFields()) {
            try {
                params.add(f.getName(), f.get(this));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return params;
    }
}
