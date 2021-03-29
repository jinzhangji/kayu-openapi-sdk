package com.kayu.param;

import com.kayu.result.OpenApiBaseResult;
import org.codehaus.groovy.tools.shell.IO;

/**
 * 参数
 * Created by Jin.Z.J  2020/9/18
 */
public interface IBaseParam<T extends OpenApiBaseResult> extends IObject {

    String requestId();

    String requestURI();

    String version();

    Class<T> resClass();

    String reqMethod();

}
