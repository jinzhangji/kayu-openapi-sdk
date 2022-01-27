package com.kayu.result.hc;

import com.kayu.result.OpenApiBaseResult;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HcProductRechargeResult extends OpenApiBaseResult {

    private RspData data;

    @Data
    public static final class RspData{
        /**
         *状态 0:待支付 1:充值中 2:充值成功 3:充值失败
         */
        private Integer state;

        /**
         * 商户订单号
         */
        private String outOrderNo;

        /**
         * 平台订单号
         */
        private String orderNo;

        /**
         * 充值账号
         */
        private String account;


        /**
         * 优惠价
         */
        private BigDecimal amount;

        /**
         * 官方价
         */
        private BigDecimal officialPrice;


        /**
         * 订单内容
         */
        private String content;
    }


}