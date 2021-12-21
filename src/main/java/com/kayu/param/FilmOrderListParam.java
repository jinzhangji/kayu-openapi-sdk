package com.kayu.param;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.result.FilmOrderListResult;
import com.kayu.utils.IdUtils;
import lombok.Data;

/**
 * 电影票订单列表
 * Created by Jin.Z.J  2021/12/21
 */
@Data
public class FilmOrderListParam implements IBaseParam<FilmOrderListResult> {

    /**
     * 页码
     */
    private Integer pageNow;
    /**
     * 条数
     */
    private Integer pageSize;
    /**
     * 用户唯一id
     */
    private String uniqueId;
    /**
     * 订单状态 订单状态	0:待付款	5:待出票	10:已出票	15:交易成功	-5:已取消
     */
    private Integer status;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;


    @Override
    public String requestId() {
        return IdUtils.uuid32();
    }

    @Override
    public String requestURI() {
        return "/api/v1/qz-film-order/queryList";
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<FilmOrderListResult> resClass() {
        return FilmOrderListResult.class;
    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }

    @Override
    public boolean verifyParam() {
        return !(this.pageNow == null);
    }
}
