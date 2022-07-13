package com.plateer.ec1.payment.vo.inicis.vitualaccount;

import com.plateer.ec1.common.model.order.OpPayInfo;
import lombok.Data;

@Data
public class InicisVirtualApproveResponseVO {
    private String resultCode;
    private String resultMsg;
    private String tid;
    private String authDate;
    private String authTime;
    private String vacct;
    private String vacctName;
    private String vacctBankCode;
    private String validDate;
    private String validTime;
    private Long price;

    public OpPayInfo createInsertModel(String payMnCd) {
        OpPayInfo model = new OpPayInfo();
        model.setTrsnId(this.getTid());
        model.setPayMnCd(payMnCd);
        model.setVrValDt(this.getValidDate());
        model.setVrValTt(this.getValidTime());
        model.setVrAcct(this.getVacct());
        model.setVrAcctNm(this.getVacctName());
        model.setVrBnkCd(this.getVacctBankCode());
        model.setPayAmt(this.getPrice());

        return model;
    }
}
