package com.kayu.core;

import com.kayu.exception.OpenApiException;
import com.kayu.param.IBaseParam;
import com.kayu.result.OpenApiBaseResult;

import java.util.concurrent.Future;

/**
 * Created by Jin.Z.J  2021/3/24
 */
public interface OpenApiClient {

    <T extends OpenApiBaseResult> T execute(IBaseParam<T> param) throws OpenApiException;


    <T extends OpenApiBaseResult> Future<T> asyncExecute(IBaseParam<T> param);


}
