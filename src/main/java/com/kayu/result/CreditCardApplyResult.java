package com.kayu.result;


import lombok.Data;

/**
 * 信用卡申请
 * Created by Jin.Z.J  2021/3/11
 */
@Data
public class CreditCardApplyResult extends OpenApiBaseResult {


    private RspData data;

    @Data
    public static class RspData{
        private String orderId;
        private String url;
    }

}
