package com.kayu.result.gas;

import com.kayu.result.OpenApiBaseResult;
import lombok.Data;

/**
 * 登陆结果
 * Created by Jin.Z.J  2021/12/16
 */
@Data
public class GasLoginResult extends OpenApiBaseResult {

    private RspData data;


    @Data
    public static class RspData{

        /**
         * 是否为渠道新用户 0:否 1:是
         */
        private Integer isNew;
        /**
         * 72小时-(建议维护24小时)
         */
        private String token;

    }


}
