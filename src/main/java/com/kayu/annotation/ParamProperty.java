package com.kayu.annotation;

import java.lang.annotation.*;

/**
 * Created by Jin.Z.J  2021/3/29
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamProperty {

    String value();

}
