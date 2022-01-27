package com.kayu.result.takeout;

import com.kayu.result.OpenApiBaseResult;
import com.kayu.result.PageData;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * Created by Jin.Z.J  2022/1/7
 */
@Data
public class FkMtTakeoutOrderListResult extends OpenApiBaseResult {

    /**
     * 响应数据
     */
    private PageData<RspData> data;


    /**
     * 响应数据
     */
    @Data
    public static final class RspData{
        /**
         * 订单号
         */
        private String orderId;

        /**
         * 支付时间
         */
        private Date payTime;

        /**
         * 支付金额
         */
        private BigDecimal payPrice;

        /**
         * 团购项目短标题
         */
        private String smsTitle;


        /**
         * 订单总价
         */
        private BigDecimal total;

        /**
         * 数量
         */
        private Integer quantity;

        /**
         * 团购项目ID
         */
        private String dealId;

        /**
         * 更新时间
         */
        private Date modTime;

        /**
         * 订单付款金额
         */
        private BigDecimal direct;

        /**
         * 券状态 1, 3, 4 (表示第1/3/4张券被消费了)
         */
        private String couponSequence;

        /**
         * 使用时间
         */
        private Date couponUseTime;

        /**
         * 商户佣金
         */
        private BigDecimal merchantCommission;

        /**
         * 订单状态，0，无效，1有效
         */
        private Integer state;

        /**
         * 用户唯一标识
         */
        private String uniqueId;

        /**
         * 用户手机号
         */
        private String phone;


    }


}
