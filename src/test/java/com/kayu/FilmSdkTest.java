package com.kayu;

import com.kayu.core.DefaultOpenApiClient;
import com.kayu.core.OpenApiClient;
import com.kayu.exception.OpenApiException;
import com.kayu.param.FilmOrderPayInfoParam;
import com.kayu.param.FilmOrderPayParam;
import com.kayu.result.FilmOrderInfoPayResult;
import com.kayu.result.FilmOrderPayResult;
import org.junit.Test;

/**
 * 电影票
 * Created by Jin.Z.J  2021/12/20
 */
public class FilmSdkTest {


    private static final OpenApiClient openApiClient = new DefaultOpenApiClient.Builder()
            .host(Environment.HOST)
            .merchantNo(Environment.MERCHANT_NO)
            .md5key(Environment.MD5_KEY).build();


    /**
     * 获取支付订单信息
     */
    @Test
    public void getOrderPayInfoTest(){
        FilmOrderPayInfoParam infoParam = new FilmOrderPayInfoParam();
        infoParam.setOrderNo("20211220143643559878001");
        FilmOrderInfoPayResult rsp;
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
        System.out.println("是否允许支付:" + (rsp.getData().getStatus() == 0));
        SdkConsole.success(rsp.getData());
    }


    /**
     * 账户资产-支付
     */
    @Test
    public void orderPaymentTest(){
        FilmOrderPayParam orderPayParam = new FilmOrderPayParam();
        orderPayParam.setOrderNo("20211220155923121455201");
        FilmOrderPayResult rsp;
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
