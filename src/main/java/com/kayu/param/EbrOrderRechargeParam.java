package com.kayu.param;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.result.EbrOrderResult;
import com.kayu.utils.IdUtils;
import com.kayu.utils.StringUtils;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Jin.Z.J  2021/12/21
 */
@Data
public class EbrOrderRechargeParam implements IBaseParam<EbrOrderResult>{

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
    private BigDecimal faceVal;


    /**
     * 缴费地区-格式(河北-唐山)
     */
    private String area;

    /**
     * 缴费类型: 4.电费-住宅 5.电费-店铺 6.电费-企事业
     */
    private Integer ebType;


    /**
     * 产品类型 0:快充 1:慢充
     */
    private Integer proType;


    /**
     * 服务商 ：GW:国网 NW:南网
     */
    private String provider;

    @Override
    public String requestId() {
        return IdUtils.uuid32();
    }

    @Override
    public String requestURI() {
        return "/api/v1/ebr-api-order/recharge";
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<EbrOrderResult> resClass() {
        return EbrOrderResult.class;
    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }

    @Override
    public boolean verifyParam() {
        boolean r1 = StringUtils.isAnyEmpty(this.account,this.outOrderNo,this.area) || this.ebType == null;
        boolean r2 = StringUtils.isEmpty(proCode);
        if(r2){
            r2 = this.faceVal == null || StringUtils.isEmpty(this.provider) || getProType() == null;
        }
        return !(r1 || r2);
    }
}
