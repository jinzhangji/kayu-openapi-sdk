package com.kayu.result.gas;

import com.kayu.result.OpenApiBaseResult;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 油站列表
 * Created by Jin.Z.J  2021/12/14
 */
@Data
public class GasQueryListResult extends OpenApiBaseResult {

    private List<RspData> data;

    @Data
    public static class RspData{
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
    }


}
