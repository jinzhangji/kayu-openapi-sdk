package com.kayu;

import com.kayu.core.DefaultOpenApiClient;
import com.kayu.core.OpenApiClient;
import com.kayu.exception.OpenApiException;
import com.kayu.param.KfcOrderDetailParam;
import com.kayu.param.KfcOrderListParam;
import com.kayu.param.KfcOrderPayInfoParam;
import com.kayu.param.KfcOrderPayParam;
import com.kayu.result.KfcOrderDetailResult;
import com.kayu.result.KfcOrderInfoPayResult;
import com.kayu.result.KfcOrderListResult;
import com.kayu.result.KfcOrderPayResult;
import org.junit.Test;

/**
 * 肯德基
 * <p>
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
    public void getOrderPayInfoTest() {
        KfcOrderPayInfoParam infoParam = new KfcOrderPayInfoParam();
        infoParam.setOrderNo("20211220153307109754202");
        KfcOrderInfoPayResult rsp;
        try {
            rsp = openApiClient.execute(infoParam);
        } catch (OpenApiException e) {
            SdkConsole.error(e.getMessage());
            return;
        }
        if (!rsp.isSuccess()) {
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
    public void orderPaymentTest() {
        KfcOrderPayParam orderPayParam = new KfcOrderPayParam("20211220160731148938602");
        KfcOrderPayResult rsp;
        try {
            rsp = openApiClient.execute(orderPayParam);
        } catch (OpenApiException e) {
            SdkConsole.error(e.getMessage());
            return;
        }
        if (!rsp.isSuccess()) {
            SdkConsole.fail(rsp.getMessage());
            return;
        }
        SdkConsole.success(rsp.getData());
    }


    /**
     * 查询订单列表
     */
    @Test
    public void queryOrderListTest() {
        KfcOrderListParam listParam = new KfcOrderListParam();
        listParam.setOrderNo("20211208172310227967502");
        listParam.setPageNow(1);
        listParam.setPageSize(10);
        listParam.setStatus(15);
        listParam.setUniqueId("1");
        listParam.setStartTime("2021-12-08 00:00:00");
        listParam.setEndTime("2021-12-21 00:00:00");
        KfcOrderListResult rsp;
        try {
            rsp = openApiClient.execute(listParam);
        } catch (OpenApiException e) {
            SdkConsole.error(e.getMessage());
            return;
        }
        if (!rsp.isSuccess()) {
            SdkConsole.fail(rsp.getMessage());
            return;
        }
        SdkConsole.success(rsp.getData());
    }


    /**
     * 查询订单详情
     */
    @Test
    public void getOrderDetailTest() {
        KfcOrderDetailParam detailParam = new KfcOrderDetailParam();
        detailParam.setOrderNo("20211208172310227967502");
        KfcOrderDetailResult rsp;
        try {
            rsp = openApiClient.execute(detailParam);
        } catch (OpenApiException e) {
            SdkConsole.error(e.getMessage());
            return;
        }
        if (!rsp.isSuccess()) {
            SdkConsole.fail(rsp.getMessage());
            return;
        }
        SdkConsole.success(rsp.getData());
    }


}
