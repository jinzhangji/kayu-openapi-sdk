package com.kayu.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;

/**
 * Created by Jin.Z.J  2021/3/29
 */
public class SignUtils {


    /**
     * 生成签名
     *
     * @param source 数据源
     * @param md5Key 签名秘钥
     * @return
     */
    public static String sign(Map<String, Object> source, String md5Key) {
        if (MapUtils.isEmpty(source)) {
            throw new IllegalArgumentException("source can not be empty");
        }
        String stringA = MapUtils.sortJoin(source, "&", "=", true);
        String stringB = new StringBuilder(stringA).append("&token=").append(md5Key).toString();
        return DigestUtils.md5Hex(stringB).toUpperCase();
    }

    /**
     * 验签
     *
     * @param sign   待对比签名
     * @param source 数据源
     * @param md5Key 签名秘钥
     * @return
     */
    public static boolean verify(String sign, Map<String, Object> source, String md5Key) {
        String localSign = sign(source, md5Key);
        return localSign.equals(sign);
    }


}
