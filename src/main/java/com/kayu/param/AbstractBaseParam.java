package com.kayu.param;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.result.OpenApiBaseResult;
import com.kayu.utils.IdUtils;

/**
 * Created by Jin.Z.J  2022/1/12
 */
public abstract class AbstractBaseParam<T extends OpenApiBaseResult> implements IBaseParam<T>{


    @Override
    public String requestId() {
        return IdUtils.uuid32();
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }
}

