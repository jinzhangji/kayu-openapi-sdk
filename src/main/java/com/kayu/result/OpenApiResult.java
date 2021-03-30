package com.kayu.result;

import com.alibaba.fastjson.JSON;
import com.kayu.utils.StringUtils;

/**
 * Created by Jin.Z.J  2021/3/11
 */
public class OpenApiResult extends OpenApiBaseResult {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public <T extends OpenApiBaseResult> T getData(Class<T> clazz) {
        if (StringUtils.isEmpty(this.data)) {
            try {
                T result = clazz.newInstance();
                result.setStatus(this.getStatus());
                result.setMessage(this.getMessage());
                return result;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        T result = JSON.parseObject(this.data, clazz);
        result.setStatus(this.getStatus());
        result.setMessage(this.getMessage());
        return result;
    }

}
