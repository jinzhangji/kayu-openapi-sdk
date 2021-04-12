package com.kayu;

import com.alibaba.fastjson.JSON;
import com.kayu.core.DefaultOpenApiClient;
import com.kayu.core.OpenApiClient;
import com.kayu.exception.OpenApiException;
import com.kayu.param.CreditCardApplyParam;
import com.kayu.param.SbuxCouponOrderCreateParam;
import com.kayu.result.CreditCardApplyResult;
import com.kayu.result.SbuxCouponOrderCreateResult;
import com.kayu.utils.MapUtils;
import com.kayu.utils.SignUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jin.Z.J  2021/3/26
 */
public class SDKTest {

    private static final OpenApiClient openApiClient = new DefaultOpenApiClient.Builder()
                                                    .host("http://localhost:9097")
                                                    .merchantNo("")
                                                    .md5key("").build();

    /**
     * 信用卡申请测试
     */
    @Test
    public void creditCardApplyTest(){


        CreditCardApplyParam applyParam = new CreditCardApplyParam();
        applyParam.setUsername("测试");
        applyParam.setPhone("13777777777");
        applyParam.setIdNo("110101199003079892");
        applyParam.setNotifyUrl("https://www.example.com");
        applyParam.setProductId("0001");
        try{
            CreditCardApplyResult result = openApiClient.execute(applyParam);
            System.out.println("RES-json-->" + JSON.toJSONString(result));
            if(result.isSuccess()){
                System.out.println(JSON.toJSONString(result));
            }else{
                System.out.println(String.format("status:%s - message:%s",result.getStatus(),result.getMessage()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 登陆
     */
    @Test
    public void couponLogin(){
        Map<String,Object> mapParam = new HashMap<>();
        mapParam.put("uniqueId","123456");
        mapParam.put("phone","13866666666");
        mapParam.put("merchantNo","123456");
        String sign = SignUtils.sign(mapParam,"12456");
        mapParam.put("sign",sign);
        String params = MapUtils.join(mapParam,"&","=");
        String url = "https://www.baidu.com?" + params;
        System.out.println(url);

    }


    /**
     * 星巴克创建订单
     */
    @Test
    public void sbuxCreateOrder(){
        SbuxCouponOrderCreateParam sbuxCouponOrderCreateParam = new SbuxCouponOrderCreateParam();
        sbuxCouponOrderCreateParam.setOutOrderNo("");
        sbuxCouponOrderCreateParam.setProductId(1L);
        sbuxCouponOrderCreateParam.setQuantity(2);
        try {
            SbuxCouponOrderCreateResult result = openApiClient.execute(sbuxCouponOrderCreateParam);
            System.out.println(JSON.toJSONString(result));
        } catch (OpenApiException e) {
            e.printStackTrace();
        }
    }


}
