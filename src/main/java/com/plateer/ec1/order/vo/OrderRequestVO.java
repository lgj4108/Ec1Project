package com.plateer.ec1.order.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestVO {
    private String ordNo;
    private List<OrderProductVO> orderProducts;
    private List<OrderDeliveryAreaVO> orderDeliveryAreas;
    private List<OrderBenefitVO> orderBenefits;

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