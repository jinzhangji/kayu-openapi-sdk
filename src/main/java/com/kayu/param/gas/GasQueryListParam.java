package com.kayu.param.gas;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.param.IBaseParam;
import com.kayu.result.gas.GasQueryListResult;
import com.kayu.utils.IdUtils;
import lombok.Data;

/**
 * 查询油站列表参数
 * Created by Jin.Z.J  2021/12/14
 */
@Data
public class GasQueryListParam implements IBaseParam<GasQueryListResult> {

    /**
     * 油号
     */
    private Integer oilNo;
    /**
     * 距离内筛选
     */
    private Integer distance;

    /**
     * 0 价格有限 1:距离优先
     */
    private Integer sort = 1;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 用户唯一id
     */
    private String uniqueId;

    /**
     * 页码
     */
    private Integer pageNow;

    /**
     * 条数
     */
    private Integer pageSize;

    /**
     * 应用类型 h5：接口和H5支付 | oa：接口和公众号支付
     */
    private String appType;


    @Override
    public String requestId() {
        return IdUtils.uuid32();
    }

    @Override
    public String requestURI() {
        return "/api/v1/gas/queryList";
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<GasQueryListResult> resClass() {
        return GasQueryListResult.class;
    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }

    @Override
    public boolean verifyParam() {
        return true;
    }
}
