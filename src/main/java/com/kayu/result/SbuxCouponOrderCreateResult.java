package com.kayu.result;

import java.util.List;

/**
 * Created by Jin.Z.J  2021/4/1
 */
public class SbuxCouponOrderCreateResult extends OpenApiBaseResult{

    private String orderNo;   //上游订单号
    private String outOrderNo;//三方订单号
    private List<String> codes;//优惠券码

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }
}
