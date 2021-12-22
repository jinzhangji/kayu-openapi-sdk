package com.kayu.param;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.result.EbrOrderResult;
import com.kayu.utils.IdUtils;
import com.kayu.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 电费充值订单查询
 * Created by Jin.Z.J  2021/12/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EbrQueryOrderParam implements IBaseParam<EbrOrderResult>{

    /**
     *  商户订单号
     */
    private String outOrderNo;

    @Override
    public String requestId() {
        return IdUtils.uuid32();
    }

    @Override
    public String requestURI() {
        return "/api/v1/ebr-api-order/getOrderStatus";
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<EbrOrderResult> resClass() {
        return EbrOrderResult.class;
    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }

    @Override
    public boolean verifyParam() {
        return !StringUtils.isEmpty(this.outOrderNo);
    }
}
