package com.plateer.ec1.promotion.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CouponInfo {
    private Long cpnIssNo;
    private Long prmNo;
    private String mbrNo;
    private String cpnUseDt;
    private String orgCpnIssNo;
    private String cpnCertNo;
    private String prmNm;
    private String prmKindCd;
    private String prmPriodCcd;
    private LocalDateTime prmStrtDt;
    private LocalDateTime prmEndDt;
    private Integer prmStdDd;
    private String empYn;
    private String dcCcd;
    private Integer dcVal;
    private Integer minPurAmt;
    private Integer maxDcAmt;
    private String useYn;
    private String rmk;
    private String cpnKindCd;
    private String degrCcd;
    private String dcSvCcd;
    private String mdaGb;
    private String entChnGb;
    private String dwlPriodCcd;
    private String dwlAvlStrtDd;
    private String dwlAvlEndDd;
    private Integer dwlStdDd;
    private Integer dwlPsbCnt;
    private Integer psnDwlPsbCnt;
    private String dwlAvlPlc;
    private String issWayCcd;
    private String certCd;
    private Integer ourChrgRt;
    private Integer entrChrgRt;

    private List<PromotionApplyTarget> applyTargetList;

    @Data
    public static class PromotionApplyTarget {
        private String aplyTgtCcd;
        private String aplyTgtNo;
        private String useYn;

        public boolean isTarget(PromotionRequestVO.ProductRequest product) {
            return false;
        }
    }

    public boolean isValidBase(String cpnKindCd) {
        return isPeriod() && isUseYn() && this.cpnKindCd.equals(cpnKindCd);
    }

    public boolean isPeriod() {
        return this.prmStrtDt.isBefore(LocalDateTime.now()) && this.prmEndDt.isAfter(LocalDateTime.now());
    }

    public boolean isUseYn() {
        return "Y".equals(this.useYn);
    }

    public boolean isOverAmt(Long price) {
        return this.minPurAmt <= price;
    }
}
