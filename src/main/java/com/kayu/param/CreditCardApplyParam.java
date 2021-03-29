package com.kayu.param;


import cn.hutool.core.util.IdUtil;
import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.result.CreditCardApplyResult;


/**
 * Created by Jin.Z.J  2021/3/11
 */
public class CreditCardApplyParam implements IBaseParam<CreditCardApplyResult>{

    private static final String PATH = "/api/merchant/apply";
    private String username; //用户名
    private String phone;//手机号
    private String idNo; //证件号码
    private String productId;//产品id
    private String notifyUrl;//通知地址

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @Override
    public String requestId() {
        return IdUtil.simpleUUID();
    }

    @Override
    public String requestURI() {
        return PATH;
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<CreditCardApplyResult> resClass() {
        return CreditCardApplyResult.class;
    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }
}
