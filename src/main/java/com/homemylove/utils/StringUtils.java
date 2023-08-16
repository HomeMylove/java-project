package com.homemylove.utils;

public class StringUtils {

    /**
     * 为字符串去除空格，如果为空，返回空字符串
     * @param str 输入字符串
     * @return 输出字符串
     */
    public static String trimIfNotNull(String str){
        if(str == null){
            str = "";
        }else {
            str = str.trim();
        }
        return str;
    }

}
