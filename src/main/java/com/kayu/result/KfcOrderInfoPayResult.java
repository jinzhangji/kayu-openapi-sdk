package com.kayu.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 电影票订单支付信息
 * Created by Jin.Z.J  2021/12/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KfcOrderInfoPayResult extends OpenApiBaseResult {


    private RspData data;

    @Data
    public static final class RspData{

        /**
         * 订单号
         */
        private String orderNo;

        /**
         * 订单内容
         */
        private String content;

        /**
         * 订单状态 0:待付款 5:排队中 15:TRAN_SUCCESS:交易成功 -5:已取消
         */
        private Integer status;

        /**
         * 状态描述
         */
        private String statusDesc;

        /**
         * 单价/元
         */
        private BigDecimal unitPrice;

        /**
         * 总价/元
         */
        private BigDecimal totalPrice;

        /**
         * 市场价/元
         */
        private BigDecimal marketUnitPrice;

        /**
         * 支付时间
         */
        private Date paymentTime;

        /**
         * 支付金额/元
         */
        private BigDecimal amount;

        /**
         * 数量
         */
        private Integer quantity;

        /**
         * 出票时间
         */
        private Date drawTime;

        /**
         * 用户手机号
         */
        private String userMobile;

        /**
         * 取消时间
         */
        private Date cancelTime;

        /**
         * 用户昵称
         */
        private String userName;

        /**
         * 平台用户唯一id
         */
        private String platformUniqueId;

        /**
         * 创建时间
         */
        private Date createTime;

    }



}
