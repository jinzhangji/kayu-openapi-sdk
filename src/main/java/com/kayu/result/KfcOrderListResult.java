package com.kayu.result;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 肯德基订单列表
 * Created by Jin.Z.J  2021/12/21
 */
@Data
public class KfcOrderListResult extends OpenApiBaseResult {


    private RspData data;


    @Data
    public static final class RspData {
        /**
         * 总条数
         */
        private Long total;
        /**
         * 总页数
         */
        private Long pages;
        /**
         * 列表
         */
        private List<Item> list;


    }

    @Data
    public static final class Item {
        /**
         * 主键
         */
        private Long id;

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
         * 取票码
         */
        private String ticket;

        /**
         * 用户备注
         */
        private String userRemark;

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
         * 退款金额/元
         */
        private BigDecimal refundPrice;

        /**
         * 平台用户唯一id
         */
        private String platformUniqueId;

        /**
         * 下单手机后4位（非用户手机号）
         */
        private String kfcOrderMobileSuffix;

        /**
         * 下单手机备注
         */
        private String kfcOrderMobileRemark;

        /**
         * 是否外卖
         */
        private String takeout;

        /**
         * 商户佣金
         */
        private BigDecimal merchantCommissionPrice;

        /**
         * 创建时间
         */
        private Date createTime;

        /**
         * 订单更新时间
         */
        private Date updateTime;


    }


}
