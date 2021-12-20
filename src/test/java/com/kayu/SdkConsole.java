package com.kayu;

import com.alibaba.fastjson.JSON;

/**
 * Created by Jin.Z.J  2021/12/20
 */
public class SdkConsole {


    public static void error(String message){
        log("请求异常", message);
    }

    public static void fail(String message){
        log("api请求失败", message);
    }

    public static void success(Object data){
        log("api响应", JSON.toJSONString(data));
    }

    public static void log(String title,String message){
        System.out.println(title + " : " + message);
    }

}
