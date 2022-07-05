package com.plateer.ec1.order.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderRequestVO extends OrderClaimBaseVO{

    private OrderBaseVO orderBase;
    private List<OrderProductVO> orderProducts;
    private List<OrderDeliveryAreaVO> orderDeliveryAreas;
    private List<OrderBenefitVO> orderBenefits;

    @Data
    public static class OrderBaseVO {
        private String mbrNo;
        private String ordTpCd;
        private String ordSysCcd;
        private String ordNm;
        private String ordSellNo;
        private String ordAddr;
        private String ordAddrDtl;
        private String rfndBnkCk;
        private String rfndAcctNo;
        private String rfndAcctOwnNm;
    }

    @Data
    public static class OrderProductBaseVO {
        private String goodsNo;
        private String itemNo;
    }
    @Data
    public static class OrderProductVO extends OrderProductBaseVO{
        private Integer ordCnt;
        private List<BenefitBaseVO> productBenefits;
    }

    @Data
    public static class OrderDeliveryAreaVO {
        private Integer dvpSeq;
        private String rmtiNm;
        private String rmtiHpNo;
        private String rmtiAddr;
        private String rmtiAddrDtl;
        private List<OrderDeliveryGroupInfoVO> orderDeliveryGroupInfos;
    }

    @Data
    public static class OrderDeliveryGroupInfoVO {
        private Integer dvGrpNo;
        private List<OrderCostInfoVO> orderCostInfos;
        private List<OrderProductBaseVO> deliveryGroupProducts;
    }

    @Data
    public static class OrderCostInfoVO {
        private String dvAmtTpCd;
        private Long orgDvAmt;
    }

    @Data
    public static class BenefitBaseVO {
        private Long prmNo;
        private Long cpnIssNo;
        private String cpnKndCd;
        private Integer degrCcd;
        private Integer ordBnfAmt;
    }

    @Data
    public static class OrderBenefitVO extends BenefitBaseVO {
        private List<OrderProductBaseVO> benefitTargetProducts;
    }
}
