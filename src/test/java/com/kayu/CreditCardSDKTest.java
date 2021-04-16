package com.kayu;

import com.alibaba.fastjson.JSON;
import com.kayu.core.DefaultOpenApiClient;
import com.kayu.core.OpenApiClient;
import com.kayu.param.CreditCardApplyParam;
import com.kayu.result.CreditCardApplyResult;
import org.junit.Test;

/**
 * Created by Jin.Z.J  2021/3/26
 */
public class CreditCardSDKTest {


    private static final OpenApiClient openApiClient = new DefaultOpenApiClient.Builder()
                                                    .host("https://www.example.com")
                                                    .merchantNo("example")
                                                    .md5key("example").build();

    /**
     * 信用卡申请测试
     */
    @Test
    public void creditCardApplyTest(){
        CreditCardApplyParam applyParam = new CreditCardApplyParam();
        applyParam.setUsername("测试张三");
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


}
