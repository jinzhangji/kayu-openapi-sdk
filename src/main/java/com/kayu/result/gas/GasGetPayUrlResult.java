package com.kayu.result.gas;

import com.kayu.result.OpenApiBaseResult;
import lombok.Data;

/**
 * 获取支付url响应结果
 * Created by Jin.Z.J  2021/12/15
 */
@Data
public class GasGetPayUrlResult extends OpenApiBaseResult {

    private RspData data;

    @Data
    public static class RspData{
        /**
         * 支付url
         */
        private String url;
        /**
         * 挂载数据-待定
         */
        private String data;

    }




}
