package com.seamtop.cuber.common.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
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

    public static boolean isChinese(String str) {
        if(StringUtil.isEmpty(str)){
            return false;
        }
        Pattern pattern = Pattern.compile("^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]){2,5}$");
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()){
            return true;
        }
        return false;
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
        Pattern pattern = Pattern.compile("[0-9]+(\\.?)[0-9]*|0");
        return pattern.matcher(str).matches();
    }

    public static boolean isNumbric(String str){
        if(str==null || "".equals(str))
            return false;
        Pattern pattern = Pattern.compile("[0-9]+[0-9]*|0");
        return pattern.matcher(str).matches();
    }

    public static String changeCharset(String str, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            //用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            //用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }

    public static String string2Unicode(String s) {
        try {
            StringBuffer out = new StringBuffer("");
            byte[] bytes = s.getBytes("unicode");
            for (int i = 2; i < bytes.length - 1; i += 2) {
                out.append("u");
                String str = Integer.toHexString(bytes[i + 1] & 0xff);
                for (int j = str.length(); j < 2; j++) {
                    out.append("0");
                }
                String str1 = Integer.toHexString(bytes[i] & 0xff);

                out.append(str);
                out.append(str1);
                out.append(" ");
            }
            return out.toString().toUpperCase();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String unicode2String(String unicodeStr){
        StringBuffer sb = new StringBuffer();
        String str[] = unicodeStr.toUpperCase().split("U");
        for(int i=0;i<str.length;i++){
            if(str[i].equals("")) continue;
            char c = (char)Integer.parseInt(str[i].trim(),16);
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String [] args){
        System.out.println(isDouble("0.00001"));
    }
}
