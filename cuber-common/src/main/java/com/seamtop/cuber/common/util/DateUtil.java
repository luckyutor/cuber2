package com.seamtop.cuber.common.util;

import java.util.Date;

/**
 * Created by feng on 2015/8/8.
 */
public class DateUtil {

    public static boolean isTimeStamp(String str){
        if(StringUtil.isEmpty(str) || StringUtil.isDouble(str)){
            return false;
        }
        long t = Long.valueOf(str);
        Date date=new Date(t);
        return false;
    }

    public static boolean isTimeStampBeforeNow(String str){
        if(StringUtil.isEmpty(str) || !StringUtil.isDouble(str)){
            return false;
        }
        long t = Long.valueOf(str);
        if(t < new Date().getTime()){
            return true;
        }else{
            return false;
        }
    }


    public static void main(String [] args){
        Date d = new Date();
        isTimeStamp(d.getTime() + "");
        isTimeStamp("0");
    }
}
