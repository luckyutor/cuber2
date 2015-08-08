package com.seamtop.cuber.common;

/**
 * Created by feng on 2015/8/8.
 */
public class StringUtil {
    public static boolean isEmpty(String str){
        if(str == null || "".equals(str)){
            return true;
        }else{
            return false;
        }
    }
}
