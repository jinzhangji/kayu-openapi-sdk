package com.kayu.result.gas;

import com.kayu.result.OpenApiBaseResult;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 加油订单列表
 * Created by Jin.Z.J  2021/12/16
 */
@Data
public class GasOrderListResult extends OpenApiBaseResult {

    private List<RspData> data;

    @Data
    public static final class RspData {


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
         * 订单状态 0:未支付、1:已支付、2:已取消 3:已退款、4:待退款、5:退款失败、
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
         * 创建时间
         */
        private Date createTime;

        /**
         * 支付时间
         */
        private Date payTime;

        /**
         * 退款时间
         */
        private Date refundTime;


    }

}
