package com.kayu.utils;

import cn.hutool.core.map.MapUtil;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.Objects;

/**
 * Created by Jin.Z.J  2021/3/29
 */
public class SignUtils {


    /**
     * 生成签名
     * @param source 数据源
     * @param md5Key 签名秘钥
     * @return
     */
    public static String sign(Map<String,Object> source,String md5Key){
        Objects.requireNonNull(source,"source can not be null");
        source.remove("sign");
        String stringA = MapUtil.sortJoin(source,"&","=",true);
        String stringB = new StringBuilder(stringA).append("&token=").append(md5Key).toString();
        return DigestUtils.md5Hex(stringB).toUpperCase();
    }


    /**
     * 验签
     * @param sign 待对比签名
     * @param source 数据源
     * @param md5Key 签名秘钥
     * @return
     */
    public static boolean verify(String sign,Map<String,Object> source,String md5Key){
        String localSign = sign(source,md5Key);
        return localSign.equals(sign);
    }


}
