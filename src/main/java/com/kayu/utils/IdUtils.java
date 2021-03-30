package com.kayu.utils;

import java.util.UUID;

/**
 * Created by Jin.Z.J  2021/3/30
 */
public final class IdUtils {

    /**
     * uuid
     *
     * @return
     */
    public static String uuid32() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
