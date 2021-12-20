package com.kayu;

import com.kayu.core.DefaultOpenApiClient;
import com.kayu.core.OpenApiClient;
import com.kayu.exception.OpenApiException;
import com.kayu.param.KfcOrderPayInfoParam;
import com.kayu.param.KfcOrderPayParam;
import com.kayu.result.KfcOrderInfoPayResult;
import com.kayu.result.KfcOrderPayResult;
import org.junit.Test;

/**
 *
 * 肯德基
 *
 * Created by Jin.Z.J  2021/12/20
 */
public class KfcSdkTest {


    private static final OpenApiClient openApiClient = new DefaultOpenApiClient.Builder()
            .host(Environment.HOST)
            .merchantNo(Environment.MERCHANT_NO)
            .md5key(Environment.MD5_KEY).build();


    /**
     * 获取支付订单信息
     */
    @Test
    public void getOrderPayInfoTest(){
        KfcOrderPayInfoParam infoParam = new KfcOrderPayInfoParam();
        infoParam.setOrderNo("20211220153307109754202");
        KfcOrderInfoPayResult rsp;
        try {
            rsp = openApiClient.execute(infoParam);
        } catch (OpenApiException e) {
            SdkConsole.error(e.getMessage());
            return ;
        }
        if(!rsp.isSuccess()){
            SdkConsole.fail(rsp.getMessage());
            return;
        }
        System.out.println("是否允许支付 : " + (rsp.getData().getStatus() == 0));
        SdkConsole.success(rsp.getData());
    }


    /**
     * 账户资产-支付
     */
    @Test
    public void orderPaymentTest(){
        KfcOrderPayParam orderPayParam = new KfcOrderPayParam("20211220160731148938602");
        KfcOrderPayResult rsp;
        try {
            rsp = openApiClient.execute(orderPayParam);
        } catch (OpenApiException e) {
            SdkConsole.error(e.getMessage());
            return ;
        }
        if(!rsp.isSuccess()){
            SdkConsole.fail(rsp.getMessage());
            return;
        }
        SdkConsole.success(rsp.getData());
    }


}
