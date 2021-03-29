package com.kayu.exception;

/**
 * Created by Jin.Z.J  2020/9/18
 */
public class OpenApiException extends Exception {


    public OpenApiException() {
    }

    public OpenApiException(String message) {
        super(message);
    }

    public OpenApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public OpenApiException(Throwable cause) {
        super(cause);
    }

    protected OpenApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
