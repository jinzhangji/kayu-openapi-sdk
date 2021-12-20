package com.kayu.param;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.result.FilmOrderPayResult;
import com.kayu.utils.IdUtils;
import com.kayu.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jin.Z.J  2021/12/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmOrderPayParam implements IBaseParam<FilmOrderPayResult> {

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
        return "/api/v1/qz-film-order/payment";
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<FilmOrderPayResult> resClass() {
        return FilmOrderPayResult.class;
    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }

    @Override
    public boolean verifyParam() {
        return !StringUtils.isEmpty(orderNo);
    }
}
