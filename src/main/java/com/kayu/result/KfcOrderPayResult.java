package com.kayu.result;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 电影票订单支付结果
 * Created by Jin.Z.J  2021/12/20
 */
@Data
public class KfcOrderPayResult extends OpenApiBaseResult{

    private RspData data;

    @Data
    public static final class RspData{
        /**
         * 备用金支付状态 0:支付失败 1:支付成功
         */
        private Integer payStatus;
        /**
         * 账户余额
         */
        private BigDecimal balance;
    }

}
