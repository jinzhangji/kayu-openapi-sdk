package com.kayu;

import com.kayu.core.URIBuilder;
import com.kayu.utils.SignUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * H5 联合登陆
 * Created by Jin.Z.J  2021/03/22
 */
public class H5UnionLoginSdkTest {


    /**
     * 创建H5登陆URI
     */
    @Test
    public void createLoginUrlTest() {

        Map<String, Object> map = new HashMap<>();
        map.put("uniqueId", "123456");
        map.put("phone", "13744444444");
        map.put("merchantNo", Environment.MERCHANT_NO);
        String sign = SignUtils.sign(map, Environment.MD5_KEY);
        map.put("sign", sign);
        URIBuilder uriBuilder = new URIBuilder(Environment.UNION_LOGIN_URL);
        uriBuilder.addParam(map);
        System.out.println("登陆URL -> " + uriBuilder.build().toString());
    }


}
