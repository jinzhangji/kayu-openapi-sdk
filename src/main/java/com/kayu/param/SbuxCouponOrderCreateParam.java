package com.kayu.param;

import com.kayu.constant.RequestMethod;
import com.kayu.constant.Version;
import com.kayu.result.SbuxCouponOrderCreateResult;
import com.kayu.utils.IdUtils;

/**
 * Created by Jin.Z.J  2021/4/1
 */
public class SbuxCouponOrderCreateParam implements IBaseParam<SbuxCouponOrderCreateResult>{

    private static final String PATH = "/api/v1/sbux-coupon-order/create";

    private Long productId;    //订单id
    private String outOrderNo; //外部订单号
    private Integer quantity;  //数量

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String requestId() {
        return IdUtils.uuid32();
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
    public Class<SbuxCouponOrderCreateResult> resClass() {
        return SbuxCouponOrderCreateResult.class;
    }

    @Override
    public String reqMethod() {
        return RequestMethod.POST;
    }
}
