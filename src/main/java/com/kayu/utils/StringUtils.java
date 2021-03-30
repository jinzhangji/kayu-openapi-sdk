package com.kayu.utils;

/**
 * Created by Jin.Z.J  2021/3/29
 */
public class StringUtils {


    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }


    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean startWith(String str1, String str2) {
        if (isEmpty(str1)) {
            return false;
        }
        return str1.startsWith(str2);
    }


    public static boolean isAnyEmpty(String... strs) {
        if (strs == null || strs.length == 0) {
            return true;
        }
        for (String str : strs) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isAllEmpty(String... strs) {
        if (strs == null || strs.length == 0) {
            return true;
        }
        for (String str : strs) {
            if (isNotEmpty(str)) {
                return false;
            }
        }
        return true;
    }


}
