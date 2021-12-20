package com.kayu.param;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.result.FilmOrderInfoPayResult;
import com.kayu.utils.IdUtils;
import com.kayu.utils.StringUtils;
import lombok.Data;

/**
 * 获取支付信息
 * Created by Jin.Z.J  2021/12/20
 */
@Data
public class FilmOrderPayInfoParam implements IBaseParam<FilmOrderInfoPayResult>{


    /**
     * 订单号
     */
    private String orderNo;


    @Override
    public String requestId() {
        return IdUtils.uuid32();
    }

    @Override
    public String requestURI() {
        return "/api/v1/qz-film-order/getOrderPayInfo";
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<FilmOrderInfoPayResult> resClass() {
        return FilmOrderInfoPayResult.class;
    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }

    @Override
    public boolean verifyParam() {
        return !StringUtils.isEmpty(this.orderNo);
    }
}
