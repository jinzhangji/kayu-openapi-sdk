package com.kayu.result;

/**
 * 返回结果
 * Created by Jin.Z.J  2021/3/11
 */
public class OpenApiBaseResult {

    private Integer status;
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return this.status == 1;
    }

}
