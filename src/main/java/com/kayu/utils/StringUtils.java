package com.kayu.utils;

/**
 * Created by Jin.Z.J  2021/3/29
 */
public class StringUtils {

    /**
     * 是空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }


    /**
     * 是不空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


    /**
     * 对比开头
     * @param str1
     * @param str2
     * @return
     */
    public static boolean startWith(String str1, String str2) {
        if (isEmpty(str1)) {
            return false;
        }
        return str1.startsWith(str2);
    }


    /**
     * 有一个为空
     * @param strs
     * @return
     */
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


    /**
     * 全部为空
     * @param strs
     * @return
     */
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
