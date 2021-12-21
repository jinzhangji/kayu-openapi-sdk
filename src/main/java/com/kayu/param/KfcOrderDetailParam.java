package com.kayu.param;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.result.KfcOrderDetailResult;
import com.kayu.utils.IdUtils;
import com.kayu.utils.StringUtils;
import lombok.Data;

/**
 * 电影票订单列表
 * Created by Jin.Z.J  2021/12/21
 */
@Data
public class KfcOrderDetailParam implements IBaseParam<KfcOrderDetailResult> {

    /**
     * 订单号
     */
    private String orderNo;

    @Override
    public String requestId() {
        return IdUtils.uuid32();
    }

    @Override
    public String requestURI() {
        return "/api/v1/qz-kfc-order/getdetail";
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<KfcOrderDetailResult> resClass() {
        return KfcOrderDetailResult.class;
    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }

    @Override
    public boolean verifyParam() {
        return !StringUtils.isEmpty(orderNo);
    }
}
