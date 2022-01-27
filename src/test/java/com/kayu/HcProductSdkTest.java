package com.kayu;

import com.kayu.core.DefaultOpenApiClient;
import com.kayu.core.OpenApiClient;
import com.kayu.exception.OpenApiException;
import com.kayu.param.hc.HcProductDetailParam;
import com.kayu.param.hc.HcProductRechargeParam;
import com.kayu.result.hc.HcMerchantProductDetailResult;
import com.kayu.result.hc.HcProductRechargeResult;
import com.kayu.utils.IdUtils;
import org.junit.Test;

/**
 * 惠充-测试
 * Created by Jin.Z.J  2022/1/12
 */
public class HcProductSdkTest {


    private static final OpenApiClient openApiClient = new DefaultOpenApiClient.Builder()
            .host(Environment.HOST)
            .merchantNo(Environment.MERCHANT_NO)
            .md5key(Environment.MD5_KEY).build();


    /**
     * 获取产品详情
     */
    @Test
    public void getProductDetailTest(){
        HcProductDetailParam detailParam = new HcProductDetailParam();
        detailParam.setProCode("00001");
        HcMerchantProductDetailResult rsp;
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


    /**
     * 充值
     */
    @Test
    public void rechargeTest(){

        HcProductRechargeParam rechargeParam = new HcProductRechargeParam();

        rechargeParam.setProCode("00001");
        rechargeParam.setSpecId("80");
        rechargeParam.setAccount("1073528888");
        rechargeParam.setOutOrderNo(IdUtils.uuid32());
//        rechargeParam.setNotifyUrl();
        HcProductRechargeResult rsp;
        try {
            rsp = openApiClient.execute(rechargeParam);
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
