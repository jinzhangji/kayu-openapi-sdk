package com.kayu.result.hc;

import com.kayu.result.OpenApiBaseResult;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 惠充-产品详情
 */
@Data
public class HcMerchantProductDetailResult extends OpenApiBaseResult {

    public RspData data;

    @Data
    public static final class RspData{
        /**
         * 产品名称
         */
        private String name;

        /**
         * 产品编码
         */
        private String code;


        /**
         * 账号类型
         */
        private String accountType;


        /**
         * 使用说明须知
         */
        private String explain;


        /**
         * 规格
         */
        private List<Spec> specs;
    }



    @Data
    public static class Spec{

        /**规格id*/
        private String specId;
        /**规格标题*/
        private String title;
        /**时长*/
        private String times;
        /**官方价*/
        private BigDecimal officialPrice;
        /**平台优惠价*/
        private BigDecimal amount;

    }

}
