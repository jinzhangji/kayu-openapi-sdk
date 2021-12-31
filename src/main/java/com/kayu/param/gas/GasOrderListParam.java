package com.kayu.param.gas;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.param.IBaseParam;
import com.kayu.result.gas.GasOrderListResult;
import com.kayu.utils.IdUtils;
import com.kayu.utils.StringUtils;
import lombok.Data;

import java.util.Date;

/**
 * 加油订单列表参数
 * Created by Jin.Z.J  2021/12/16
 */
@Data
public class GasOrderListParam implements IBaseParam<GasOrderListResult> {

    /**
     * 支付开始时间 否 时间格式 2017-09-27 00:00:00
     */
    private Date startTime;
    /**
     * 支付结束时间 否 时间格式 2017-09-27 00:00:00
     */
    private Date endTime;
    /**
     * 订单状态 1:已支付、 3:已退款、4:退款中、5:退款失败
     */
    private Integer state;
    /**
     * 订单号 否
     */
    private String orderId;

    /**
     * 用户唯一id必须
     */
    private String uniqueId;

    /**
     * 应用类型 app | oa
     */
    private String appType;

    /**
     * 分页页码
     */
    private Integer pageNow;

    /**
     * 页码条数
     */
    private Integer pageSize;


    @Override
    public String requestId() {
        return IdUtils.uuid32();
    }

    @Override
    public String requestURI() {
        return "/api/v1/gas-order/queryList";
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<GasOrderListResult> resClass() {
        return GasOrderListResult.class;

    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }

    @Override
    public boolean verifyParam() {
        return !StringUtils.isAnyEmpty(this.uniqueId, this.appType);
    }
}
