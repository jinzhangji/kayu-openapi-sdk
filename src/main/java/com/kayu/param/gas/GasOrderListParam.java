package com.kayu.param.gas;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.param.IBaseParam;
import com.kayu.param.PageParam;
import com.kayu.result.gas.GasOrderListResult;
import com.kayu.utils.IdUtils;
import lombok.Data;

/**
 * Created by Jin.Z.J  2022/1/4
 */
@Data
public class GasOrderListParam extends PageParam implements IBaseParam<GasOrderListResult> {

    /**
     * 开始时间  时间格式 2017-09-27 00:00:00
     */
    private String startTime;
    /**
     * 结束时间 时间格式 2017-09-27 00:00:00
     */
    private String endTime;
    /**
     * 订单状态 0:未支付、1:已支付、2:已取消 3:已退款、4:退款中、5:退款失败
     */
    private Integer state;
    /**
     * 订单号
     */
    private String orderId;

    /**
     * 用户唯一id
     */
    private String uniqueId;


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
        return !(super.getPageNow() == null);
    }
}
