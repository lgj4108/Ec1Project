package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.promotion.enums.CouponKindCode;
import com.plateer.ec1.promotion.enums.PromotionKindCode;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
    private LocalDateTime sysRegDtime;

    private List<PromotionApplyTarget> applyTargetList;

    public boolean isValidBase(PromotionKindCode prmKindCd, CouponKindCode cpnKindCd) {
        return isPeriod() && isPromotionUseYn() && (PromotionKindCode.COUPON == prmKindCd ? this.cpnKindCd.equals(cpnKindCd.getCode()) : true);
    }

    public boolean isValidCouponBase(PromotionKindCode prmKindCd, CouponKindCode cpnKindCd) {
        return isValidBase(prmKindCd, cpnKindCd) && isCouponUseYn();
    }

    public boolean isPeriod() {
        return "10".equals(this.prmPriodCcd) ? this.prmStrtDt.isBefore(LocalDateTime.now()) && this.prmEndDt.isAfter(LocalDateTime.now())
                : this.sysRegDtime.plusDays(this.prmStdDd.longValue()).isAfter(LocalDateTime.now());
    }

    public boolean isPromotionUseYn() {
        return "Y".equals(this.useYn);
    }

    public boolean isCouponUseYn() {
        return Objects.isNull(this.cpnUseDt);
    }
}
