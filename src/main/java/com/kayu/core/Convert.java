package com.kayu.core;

import com.kayu.result.OpenApiBaseResult;

/**
 * Created by Jin.Z.J  2021/7/5
 */
public interface Convert {


    <T extends OpenApiBaseResult> T convert(Class<T> clazz);

}
