package com.kayu.param.takeout;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.param.IBaseParam;
import com.kayu.param.PageParam;
import com.kayu.result.takeout.FkMtTakeoutOrderListResult;
import com.kayu.utils.IdUtils;
import com.kayu.utils.StringUtils;
import lombok.Data;

/**
 * Created by Jin.Z.J  2022/1/7
 */
@Data
public class FkMtTakeoutOrderListParam extends PageParam implements IBaseParam<FkMtTakeoutOrderListResult> {

    /**
     * 订单状态 0:无效 1:有效
     */
    private Integer state;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 解释时间
     */
    private String endTime;
    /**
     * 用户唯一id
     */
    private String uniqueId;

    /**
     * 订单号
     */
    private String orderId;


    @Override
    public String requestId() {
        return IdUtils.uuid32();
    }

    @Override
    public String requestURI() {
        return "/api/v1/fk-mt-takeout-order/queryList";
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<FkMtTakeoutOrderListResult> resClass() {
        return FkMtTakeoutOrderListResult.class;
    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }

    @Override
    public boolean verifyParam() {
        return !StringUtils.isAnyEmpty(this.startTime,this.endTime);
    }
}
