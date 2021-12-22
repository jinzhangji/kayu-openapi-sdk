package com.kayu;

import com.kayu.core.DefaultOpenApiClient;
import com.kayu.core.OpenApiClient;
import com.kayu.exception.OpenApiException;
import com.kayu.param.EbrOrderRechargeParam;
import com.kayu.param.EbrQueryOrderParam;
import com.kayu.result.EbrOrderResult;
import com.kayu.utils.IdUtils;
import org.junit.Test;

/**
 * 电费测试
 * Created by Jin.Z.J  2021/12/21
 */
public class EbrSdkTest {


    private static final OpenApiClient openApiClient = new DefaultOpenApiClient.Builder()
            .host(Environment.HOST)
            .merchantNo(Environment.MERCHANT_NO)
            .md5key(Environment.MD5_KEY).build();


    /**
     * 电费充值测试
     */
    @Test
    public void rechargeTest() {

        EbrOrderRechargeParam rechargeParam = new EbrOrderRechargeParam();

        String outOrderNo = IdUtils.uuid32();
        //example 1 自选产品
        rechargeParam.setProCode("y16000006");
        rechargeParam.setAccount("1008611");
        rechargeParam.setArea("北京-北京");
        rechargeParam.setOutOrderNo(outOrderNo);
        rechargeParam.setEbType(4);

//        //example 2 自动匹配产品
//        rechargeParam.setAccount("1008611");
//        rechargeParam.setArea("北京-北京");
//        rechargeParam.setFaceVal(BigDecimal.valueOf(100));
//        rechargeParam.setProvider("GW");
//        rechargeParam.setProType(1);
//        rechargeParam.setEbType(4);
//        rechargeParam.setOutOrderNo(outOrderNo);


        EbrOrderResult rsp;
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


    /**
     * 查询订单状态
     */
    @Test
    public void getOrderStatusTest() {

        EbrQueryOrderParam queryOrderParam = new EbrQueryOrderParam();
        queryOrderParam.setOutOrderNo("f028f8eb1a89445cab3a666fcc9e75fe");
        EbrOrderResult rsp;
        try {
            rsp = openApiClient.execute(queryOrderParam);
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
