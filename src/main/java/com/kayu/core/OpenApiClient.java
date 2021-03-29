package com.kayu.core;

import com.kayu.exception.OpenApiException;
import com.kayu.param.IBaseParam;
import com.kayu.result.OpenApiBaseResult;

/**
 * Created by Jin.Z.J  2021/3/24
 */
public interface OpenApiClient {

    <T extends OpenApiBaseResult> T execute(IBaseParam<T> param) throws OpenApiException;

}
