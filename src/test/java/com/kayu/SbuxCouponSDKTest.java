package com.kayu;

import com.alibaba.fastjson.JSON;
import com.kayu.core.DefaultOpenApiClient;
import com.kayu.core.OpenApiClient;
import com.kayu.core.URIBuilder;
import com.kayu.exception.OpenApiException;
import com.kayu.param.SbuxCouponOrderCreateParam;
import com.kayu.result.SbuxCouponOrderCreateResult;
import com.kayu.utils.IdUtils;
import com.kayu.utils.SignUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jin.Z.J  2021/03/22
 */
public class SbuxCouponSDKTest {


    private static final String MD5_KEY = "your md5key";

    private static final String MERCHANT_NO = "your merchant no";

    private static final OpenApiClient openApiClient = new DefaultOpenApiClient.Builder()
                                                            .host("your host")
                                                            .merchantNo(MERCHANT_NO)
                                                            .md5key(MD5_KEY).build();



    /**
     * 创建H5登陆URI
     */
    @Test
    public void createLoginUrlTest(){

        String h5Host= "your url";
        Map<String,Object> map = new HashMap<>();
        map.put("uniqueId","your uniqueId");
        map.put("phone","your phone");
        map.put("merchantNo",MERCHANT_NO);
        String sign = SignUtils.sign(map,MD5_KEY);
        map.put("sign",sign);
        URIBuilder uriBuilder = new URIBuilder(h5Host);
        uriBuilder.addParam(map);

        System.out.println("登陆URL -> " + uriBuilder.build().toString());

    }


    /**
     * 星巴克创建订单
     */
    @Test
    public void sbuxCreateOrder(){
        SbuxCouponOrderCreateParam sbuxCouponOrderCreateParam = new SbuxCouponOrderCreateParam();
        sbuxCouponOrderCreateParam.setOutOrderNo(IdUtils.uuid32());
        sbuxCouponOrderCreateParam.setProductId(1L);
        sbuxCouponOrderCreateParam.setQuantity(1);
        try {
            SbuxCouponOrderCreateResult result = openApiClient.execute(sbuxCouponOrderCreateParam);
            System.out.println(JSON.toJSONString(result));
        } catch (OpenApiException e) {
            e.printStackTrace();
        }
    }



}
