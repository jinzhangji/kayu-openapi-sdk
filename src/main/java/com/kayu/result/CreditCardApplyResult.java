package com.kayu.result;


/**
 * Created by Jin.Z.J  2021/3/11
 */
public class CreditCardApplyResult extends OpenApiBaseResult {

    private String orderId;
    private String url;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
