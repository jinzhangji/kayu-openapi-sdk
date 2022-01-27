package com.kayu.param.hc;

import com.kayu.param.AbstractBaseParam;
import com.kayu.result.hc.HcMerchantProductDetailResult;
import com.kayu.utils.StringUtils;
import lombok.Data;

/**
 * Created by Jin.Z.J  2022/1/12
 */
@Data
public class HcProductDetailParam extends AbstractBaseParam<HcMerchantProductDetailResult> {

    /**
     * 产品编码
     */
    private String proCode;

    @Override
    public String requestURI() {
        return "/api/v1/hc-merchant-product/getdetail";
    }

    @Override
    public Class<HcMerchantProductDetailResult> resClass() {
        return HcMerchantProductDetailResult.class;
    }

    @Override
    public boolean verifyParam() {
        return StringUtils.isNotEmpty(this.proCode);
    }
}
