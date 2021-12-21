package com.kayu.param;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.result.FilmOrderDetailResult;
import com.kayu.utils.IdUtils;
import com.kayu.utils.StringUtils;
import lombok.Data;

/**
 * 电影票订单详情
 * Created by Jin.Z.J  2021/12/21
 */
@Data
public class FilmOrderDetailParam implements IBaseParam<FilmOrderDetailResult> {

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
        return "/api/v1/qz-film-order/getdetail";
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<FilmOrderDetailResult> resClass() {
        return FilmOrderDetailResult.class;
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
