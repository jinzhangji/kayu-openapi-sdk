package com.kayu.result.gas;

import com.kayu.result.OpenApiBaseResult;
import com.kayu.result.PageData;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 加油系统订单列表
 * Created by Jin.Z.J  2022/1/4
 */
@Data
public class GasOrderListResult extends OpenApiBaseResult {


    private PageData<RspData> data;


    @Data
    public static class RspData{


        /**
         * 订单号
         */
        private String orderId;

        /**
         * 支付订单号
         */
        private String paySn;

        /**
         * 油站名称
         */
        private String gasName;

        /**
         * 支付类型
         */
        private String payType;

        /**
         * 油号
         */
        private String oilName;

        /**
         * 枪号
         */
        private String gunNo;

        /**
         * 总金额
         */
        private BigDecimal totalAmt;

        /**
         * 优惠金额
         */
        private BigDecimal disAmt;

        /**
         * 支付金额
         */
        private BigDecimal payAmt;

        /**
         * 优惠券金额
         */
        private BigDecimal couponAmt;

        /**
         * 优惠金额
         */
        private BigDecimal otherAmt;

        /**
         * 升数
         */
        private Double liter;

        /**
         * 订单状态 0:未支付、1:已支付、2:已取消 3:已退款、4:退款中、5:退款失败、
         */
        private Integer state;

        /**
         * 核销码
         */
        private String qrCode;

        /**
         * 用户手机号
         */
        private String phone;


        /**
         * 用户唯一id
         */
        private String uniqueId;

        /**
         * 渠道 0:团油 1:滴滴
         */
        private Integer channel;

        /**
         * 商户佣金
         */
        private BigDecimal merCommissionPrice;


        /**
         * 支付时间
         */
        private Date payTime;

        /**
         * 退款时间
         */
        private Date refundTime;

        /**
         * 创建时间
         */
        private Date createTime;

        /**
         * 修改时间
         */
        private Date modifyTime;
    }

}