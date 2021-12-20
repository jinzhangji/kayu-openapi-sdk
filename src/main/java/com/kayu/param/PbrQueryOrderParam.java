package com.kayu.param;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.result.PbrOrderResult;
import com.kayu.utils.IdUtils;
import com.kayu.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jin.Z.J  2021/12/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PbrQueryOrderParam implements IBaseParam<PbrOrderResult> {

    /**
     * 三方订单号
     */
    private String outOrderNo;

    @Override
    public String requestId() {
        return IdUtils.uuid32();
    }

    @Override
    public String requestURI() {
        return "/api/v1/pbr/getOrderStatus";
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<PbrOrderResult> resClass() {
        return PbrOrderResult.class;
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
