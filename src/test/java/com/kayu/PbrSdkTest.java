package com.kayu;

import com.alibaba.fastjson.JSON;
import com.kayu.core.DefaultOpenApiClient;
import com.kayu.core.OpenApiClient;
import com.kayu.exception.OpenApiException;
import com.kayu.param.PbrCreateOrderParam;
import com.kayu.param.PbrQueryOrderParam;
import com.kayu.result.PbrOrderResult;
import com.kayu.utils.IdUtils;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * 话费充值sdk 测试
 * Created by Jin.Z.J  2021/12/1
 */
public class PbrSdkTest {


    private static final OpenApiClient openApiClient = new DefaultOpenApiClient.Builder()
            .host(Environment.HOST)
            .merchantNo(Environment.MERCHANT_NO)
            .md5key(Environment.MD5_KEY).build();

    /**
     * 充值
     */
    @Test
    public void rechargeTest(){

        PbrCreateOrderParam orderParam = new PbrCreateOrderParam();
        //Example 1
//        orderParam.setProCode("00005");
        //Example 2
        orderParam.setOutOrderNo(IdUtils.uuid32());
        orderParam.setAccount("13717699999");
        orderParam.setAmount(BigDecimal.valueOf(100));
        orderParam.setCategoryId(1L);
        orderParam.setNotifyUrl("http://www.example.com/api/pbr/callback");
        try {
            PbrOrderResult result = openApiClient.execute(orderParam);
            if(result.isSuccess()){

            }
            System.out.println(JSON.toJSONString(result));
        } catch (OpenApiException e) {
            SdkConsole.error(e.getMessage());
        }

    }

    /**
     * 交易状态查询
     */
    @Test
    public void queryOrderTest() {
        PbrQueryOrderParam orderParam = new PbrQueryOrderParam();
        orderParam.setOutOrderNo("f50c9767f92e42c8bc2192fbfea0ab96");
        try {
            PbrOrderResult result = openApiClient.execute(orderParam);
            System.out.println(JSON.toJSONString(result));
        } catch (OpenApiException e) {

        }
    }


}
