package com.kayu.param.gas;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.param.IBaseParam;
import com.kayu.result.gas.GasGetPayUrlResult;
import com.kayu.utils.IdUtils;
import com.kayu.utils.StringUtils;
import lombok.Data;

/**
 * 获取支付链接参数
 * Created by Jin.Z.J  2021/12/15
 */
@Data
public class GasGetPayUrlParam implements IBaseParam<GasGetPayUrlResult> {

    /**
     * 油站id
     */
    private String gasId;
    /**
     * 枪号
     */
    private String gunNo;
    /**
     * 用户唯一id
     */
    private String uniqueId;
    /**
     * 油号
     */
    private Integer oilNo;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 经度
     */
    private Double longitude;

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
        return "/api/v1/gas/getPayUrl";
    }

    @Override
    public String version() {
        return Version.SDK_V_1_0;
    }

    @Override
    public Class<GasGetPayUrlResult> resClass() {
        return GasGetPayUrlResult.class;
    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }

    @Override
    public boolean verifyParam() {
        return !StringUtils.isAnyEmpty(this.gasId,this.gunNo,this.uniqueId,this.appType);
    }
}
