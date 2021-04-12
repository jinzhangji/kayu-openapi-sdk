package com.kayu.utils;

import com.kayu.param.IObject;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Created by Jin.Z.J  2021/3/29
 */
public class SignUtils {


    /**
     * 生成签名
     * @param data 数据源
     * @param md5Key 签名秘钥
     * @return
     */
    public static <T extends IObject> String sign(T data,String md5Key){
        if(data == null){
            throw new NullPointerException("data can not be null");
        }
        Map<String,Object> map = BeanUtils.beanToMap(data);
        return sign(map,md5Key);
    }


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
        return md5(stringB).toUpperCase();
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


    /**
     * md5加密
     * @param str
     * @return
     */
    public static String md5(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(str.getBytes(Charset.forName("utf-8")));
            return bytes2HexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }



    /*
     * 字节数组转16进制字符串
     */
    public static String bytes2HexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                builder.append('0').append(hex);
            }else{
                builder.append(hex);
            }
        }
        return builder.toString();
    }


}
