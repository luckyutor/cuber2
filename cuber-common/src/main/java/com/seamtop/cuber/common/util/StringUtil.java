package com.seamtop.cuber.common.util;

import java.util.regex.Pattern;

/**
 * Created by feng on 2015/8/8.
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if(str == null || "".equals(str)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断是否为数字,每一位必须为数字
     * @param str
     * @return
     */
    public static boolean isDigital(String str){
        if(StringUtil.isEmpty(str)){
            return false;
        }else{
            Pattern pattern = Pattern.compile("[0-9]*");
            return pattern.matcher(str).matches();
        }
    }

    /**
     * 判断是否为double
     * @param str
     * @return
     */
    public static boolean isDouble(String str){
        if(str==null || "".equals(str))
            return false;
        Pattern pattern = Pattern.compile("[1-9]+(\\.?)[0-9]*");
        return pattern.matcher(str).matches();
    }

}