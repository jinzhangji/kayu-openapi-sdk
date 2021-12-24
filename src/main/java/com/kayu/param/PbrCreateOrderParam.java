package com.kayu.param;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.result.PbrOrderResult;
import com.kayu.utils.IdUtils;
import com.kayu.utils.StringUtils;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Jin.Z.J  2021/12/1
 */
@Data
public class PbrCreateOrderParam implements IBaseParam<PbrOrderResult> {


    /**
     * 三方订单号
     */
    private String outOrderNo;


    /**
     * 充值账号
     */
    private String account;


    /**
     * 产品编码
     */
    private String proCode;


    /**
     * 充值面额
     */
    private BigDecimal amount;

    /**
     * 充值类型id
     */
    private Long categoryId;


    /**
     * 回调地址
     */
    private String notifyUrl;


    @Override
    public String requestId() {
        return IdUtils.uuid32();
    }

    @Override
    public String requestURI() {
        return "/api/v1/pbr/recharge";
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<PbrOrderResult> resClass() {
        return PbrOrderResult.class;
    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }

    @Override
    public boolean verifyParam() {
        boolean r1 = StringUtils.isAnyEmpty(this.account, this.outOrderNo);
        boolean r2 = StringUtils.isEmpty(this.proCode);
        if (r2) {
            r2 = this.amount == null || this.categoryId == null;
        }
        return !(r1 || r2);
    }
}
