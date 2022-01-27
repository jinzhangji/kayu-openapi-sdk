package com.kayu.param.hc;

import com.kayu.param.AbstractBaseParam;
import com.kayu.result.hc.HcProductRechargeResult;
import com.kayu.utils.StringUtils;
import lombok.Data;

/**
 * 惠充产品充值
 */
@Data
public class HcProductRechargeParam extends AbstractBaseParam<HcProductRechargeResult> {

    /**
     * 产品编码
     */
    private String proCode;


    /**
     * 规格id
     */
    private String specId;


    /**
     * 充值账号
     */
    private String account;


    /**
     * 商户订单号
     */
    private String outOrderNo;


    /**
     * 通知地址
     */
    private String notifyUrl;


    @Override
    public String requestURI() {
        return "/api/v1/hc-product-order/recharge";
    }

    @Override
    public Class<HcProductRechargeResult> resClass() {
        return HcProductRechargeResult.class;
    }

    @Override
    public boolean verifyParam() {
        return !StringUtils.isAnyEmpty(this.proCode,this.account,this.specId,this.outOrderNo);
    }
}