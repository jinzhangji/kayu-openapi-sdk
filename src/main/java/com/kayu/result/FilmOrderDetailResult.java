package com.kayu.result;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 电影票订单详情
 * Created by Jin.Z.J  2021/12/21
 */
@Data
public class FilmOrderDetailResult extends OpenApiBaseResult {


    private RspData data;

    @Data
    public static final class RspData {

        /**
         * 主键
         */
        private Long id;

        /**
         * 用户名称
         */
        private String username;

        /**
         * 订单号
         */
        private String orderNo;

        /**
         * 市场单价
         */
        private BigDecimal marketUnitPrice;

        /**
         * 订单状态	0:待付款	5:待出票	10:已出票	15:交易成功	-5:已取消
         */
        private Integer status;

        /**
         * 状态描述
         */
        private String statusDesc;

        /**
         * 单价
         */
        private BigDecimal unitPrice;

        /**
         * 总价
         */
        private BigDecimal totalPrice;

        /**
         * 支付时间
         */
        private Date paymentTime;

        /**
         * 支付金额
         */
        private BigDecimal amount;

        /**
         * 座位描述
         */
        private String seatsDesc;

        /**
         * 座位数量
         */
        private Integer seatsCount;

        /**
         * 电影的城市
         */
        private String cinemaCity;

        /**
         * 影院的详细地址
         */
        private String cinemaAddr;

        /**
         * 影院名称
         */
        private String cinemaName;


        /**
         * 影厅名称
         */
        private String hallName;

        /**
         * 电影名称
         */
        private String filmName;

        /**
         * 影片语言
         */
        private String language;

        /**
         * 影片url海报地址
         */
        private String pic;

        /**
         * 影片类型
         */
        private String versionTypes;

        /**
         * 用户备注
         */
        private String userRemark;

        /**
         * 播放时长
         */
        private String duration;

        /**
         * 场次开始时间
         */
        private Date showTime;

        /**
         * 场次结束时间
         */
        private Date showEndTime;

        /**
         * 完成时间
         */
        private Date completeTime;

        /**
         * 取消时间
         */
        private Date cancelTime;

        /**
         * 订单取消类型	5:用户取消	6:支付超时	15:客服取消	10,11,12:出票超时
         */
        private Integer cancelType;

        /**
         * 是否接受调座
         */
        private String acceptAdjust;

        /**
         * 距离开场时间
         */
        private String distanceToShow;

        /**
         * 用户能否取消订单
         */
        private String canUserCancel;


        /**
         * 平台用户唯一标识
         */
        private String platformUniqueId;

        /**
         * 0:竞价出票(折扣出票) 5:快速出票(非折扣出票)
         */
        private Integer drawMode;


        /**
         * 商户佣金
         */
        private BigDecimal merchantCommissionPrice;

        /**
         * 出票时间
         */
        private Date drawTime;

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
