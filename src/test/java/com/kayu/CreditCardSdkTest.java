package com.kayu;

import com.alibaba.fastjson.JSON;
import com.kayu.core.DefaultOpenApiClient;
import com.kayu.core.OpenApiClient;
import com.kayu.param.CreditCardApplyParam;
import com.kayu.result.CreditCardApplyResult;
import org.junit.Test;

/**
 * 信用卡
 * Created by Jin.Z.J  2021/3/26
 */
public class CreditCardSdkTest {


    private static final OpenApiClient openApiClient = new DefaultOpenApiClient.Builder()
            .host(Environment.HOST)
            .merchantNo(Environment.MERCHANT_NO)
            .md5key(Environment.MD5_KEY).build();


    /**
     * 信用卡进件
     */
    @Test
    public void creditCardApplyTest() {
        CreditCardApplyParam applyParam = new CreditCardApplyParam();
        applyParam.setUsername("华夏测试");
        applyParam.setPhone("15588886666");
        applyParam.setIdNo("110101199003079892");
        applyParam.setNotifyUrl("https://www.example.com");
        applyParam.setProductId("62");
        try {
            CreditCardApplyResult result = openApiClient.execute(applyParam);
            if (result.isSuccess()) {
                System.out.println("RES-json-->" + JSON.toJSONString(result));
            } else {
                System.out.println(String.format("status:%s - message:%s", result.getStatus(), result.getMessage()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
