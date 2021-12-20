package com.kayu.param.gas;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.param.IBaseParam;
import com.kayu.result.gas.GasLoginResult;
import com.kayu.utils.IdUtils;
import com.kayu.utils.StringUtils;
import lombok.Data;

/**
 * 加油用户登陆
 * Created by Jin.Z.J  2021/12/16
 */
@Data
public class GasLoginParam implements IBaseParam<GasLoginResult> {

    /**
     * 手机号
     */
    private String phone;
    /**
     * 应用类型 h5:h5 | oa：微信公众号
     */
    private String appType;

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
        return "/api/v1/gas/login";
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<GasLoginResult> resClass() {
        return GasLoginResult.class;
    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }

    @Override
    public boolean verifyParam() {
        return !StringUtils.isAnyEmpty(this.phone, this.appType, this.uniqueId);
    }
}
