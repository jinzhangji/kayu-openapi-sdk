package com.kayu.result;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 话费充值结果
 * Created by Jin.Z.J  2021/12/1
 */
@Data
public class PbrOrderResult extends OpenApiBaseResult {


    private RspData data;

    @Data
    public static final class RspData {

        /**
         * 商户订单号
         */
        private String outOrderNo;
        /**
         * 平台订单号
         */
        private String orderNo;
        /**
         * 面额
         */
        private BigDecimal faceVal;
        /**
         * 充值账号
         */
        private String account;
        /**
         * 充值状态 0:充值中 1:充值成功 2:充值失败
         */
        private Integer state;

    }


}
