package com.kayu;

import com.alibaba.fastjson.JSON;
import com.kayu.core.DefaultOpenApiClient;
import com.kayu.core.OpenApiClient;
import com.kayu.exception.OpenApiException;
import com.kayu.param.gas.*;
import com.kayu.result.gas.*;
import org.junit.Test;

/**
 * 加油-
 * Created by Jin.Z.J  2021/12/14
 */
public class GasSdkTest {


    private static final OpenApiClient openApiClient = new DefaultOpenApiClient.Builder()
            .host(Environment.HOST)
            .merchantNo(Environment.MERCHANT_NO)
            .md5key(Environment.MD5_KEY).build();


    /**
     * 登录
     */
    @Test
    public void gasLoginTest() {

        GasLoginParam loginParam = new GasLoginParam();
        loginParam.setAppType("oa");
        loginParam.setPhone("13717777777");
        loginParam.setUniqueId("3");
        GasLoginResult rsp;
        try {
            rsp = openApiClient.execute(loginParam);
        } catch (OpenApiException e) {
            log("请求异常", e.getMessage());
            return;
        }
        if (!rsp.isSuccess()) {
            log("api请求失败", rsp.getMessage());
            return;
        }



        log("响应结果", JSON.toJSONString(rsp));
    }


    /**
     * 获取油站列表
     *
     * @throws Exception
     */
    @Test
    public void gasQueryListTest() {
        GasQueryListParam queryListParam = new GasQueryListParam();
        queryListParam.setOilNo(92);
        queryListParam.setDistance(100);
        queryListParam.setUniqueId("3");
        queryListParam.setLatitude(39.916527);
        queryListParam.setLongitude(116.397128);
        queryListParam.setSort(0);
        queryListParam.setPageNow(1);
        queryListParam.setPageSize(1);
        queryListParam.setAppType("oa");
        GasQueryListResult rsp;
        try {
            rsp = openApiClient.execute(queryListParam);
        } catch (OpenApiException e) {
            log("请求异常", e.getMessage());
            return;
        }
        if (!rsp.isSuccess()) {
            log("api请求失败", rsp.getMessage());
            return;
        }
        log("响应结果", JSON.toJSONString(rsp));

    }


    /**
     * 获取油站详情
     *
     * @throws Exception
     */
    @Test
    public void gasQueryDetailTest() {
        GasGetDetailParam detailParam = new GasGetDetailParam();
        detailParam.setGasId("QP000001702");
        detailParam.setUniqueId("3");
        detailParam.setAppType("oa");
        GasGetDetailResult rsp;
        try {
            rsp = openApiClient.execute(detailParam);
        } catch (OpenApiException e) {
            log("请求异常", e.getMessage());
            return;
        }
        if (!rsp.isSuccess()) {
            log("api请求失败", rsp.getMessage());
            return;
        }
        log("响应结果", JSON.toJSONString(rsp));
    }


    /**
     * 获取支付url
     */
    @Test
    public void gasGetPayUrlTest() {
        GasGetPayUrlParam getPayUrlParam = new GasGetPayUrlParam();
        getPayUrlParam.setOilNo(92);
        getPayUrlParam.setGunNo("5");
        getPayUrlParam.setUniqueId("3");
        getPayUrlParam.setGasId("QP000001702");
        getPayUrlParam.setAppType("oa");
        GasGetPayUrlResult rsp;
        try {
            rsp = openApiClient.execute(getPayUrlParam);
        } catch (OpenApiException e) {
            log("请求异常", e.getMessage());
            return;
        }
        if (!rsp.isSuccess()) {
            log("api请求失败", rsp.getMessage());
            return;
        }
        log("响应结果", JSON.toJSONString(rsp));
    }





    /**
     * 查询订单
     */
    @Test
    public void gasQueryOrderListTest() {

        GasOrderListParam listParam = new GasOrderListParam();
        listParam.setUniqueId("6");
        listParam.setAppType("oa");
        listParam.setPageNow(1);
        listParam.setPageSize(100);
        GasOrderListResult rsp;
        try {
            rsp = openApiClient.execute(listParam);
        } catch (OpenApiException e) {
            log("请求异常", e.getMessage());
            return;
        }
        if (!rsp.isSuccess()) {
            log("api请求失败", rsp.getMessage());
            return;
        }
        log("响应结果", JSON.toJSONString(rsp));

    }


    private void log(String title, String message) {
        System.out.println(title + " : " + message);
    }


}
