package com.kayu.result.gas;

import com.kayu.result.OpenApiBaseResult;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 油站详情响应
 * Created by Jin.Z.J  2021/12/15
 */
@Data
public class GasGetDetailResult extends OpenApiBaseResult {


    private RspData data;


    @Data
    public static class RspData {

        /**
         * 油站id
         */
        private String gasId;
        /**
         * 油站名称
         */
        private String gasName;
        /**
         * 油站logo
         */
        private String smallLogo;
        /**
         * 油站地址
         */
        private String address;
        /**
         * 油站纬度
         */
        private Double latitude;

        /**
         * 油站经度
         */
        private Double longitude;
        /**
         * 油站距离
         */
        private Double distance;


        /**
         * 是否能开发票 0 不能开 1 能开
         */
        private Integer isInvoice;

        /**
         * 油号类型集合
         */
        private List<GasOilTypeResult> oilTypes;


        @Data
        public static class GasOilTypeResult {
            /**
             * 类型 1:汽油 2:柴油 3:天然气
             */
            private Integer oilType;
            private List<GasOilResult> list;
        }


        @Data
        public static class GasOilResult {

            /**
             * 油号类型
             */
            private Integer oilNo;
            /**
             * 油号名称
             */
            private String oilName;
            /**
             * 国标价
             */
            private BigDecimal ofiPrice;
            /**
             * 优惠价
             */
            private BigDecimal pflPrice;
            /**
             * 枪价
             */
            private BigDecimal gunPrice;

            /**
             * 油号类别：1 汽油，2 柴油，3 天然气
             */
            private Integer oilType;

            /**
             * 枪号
             */
            private String gunNos;


        }

    }
}