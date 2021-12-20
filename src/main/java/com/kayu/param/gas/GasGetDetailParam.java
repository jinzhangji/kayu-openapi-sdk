package com.kayu.param.gas;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.param.IBaseParam;
import com.kayu.result.gas.GasGetDetailResult;
import com.kayu.utils.IdUtils;
import com.kayu.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取油站详情参数
 * Created by Jin.Z.J  2021/12/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GasGetDetailParam implements IBaseParam<GasGetDetailResult> {
    /**
     * 油站id
     */
    private String gasId;
    /**
     * 用户唯一id
     */
    private String uniqueId;

    /**
     * 应用类型 h5：接口和H5支付 | oa：接口和公众号支付
     */
    private String appType;


    @Override
    public String requestId() {
        return IdUtils.uuid32();
    }

    @Override
    public String requestURI() {
        return "/api/v1/gas/getdetail";
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<GasGetDetailResult> resClass() {
        return GasGetDetailResult.class;
    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }

    @Override
    public boolean verifyParam() {
        return !StringUtils.isAnyEmpty(this.gasId, this.uniqueId, this.appType);
    }
}
