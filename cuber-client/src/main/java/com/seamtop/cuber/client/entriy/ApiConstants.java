package com.seamtop.cuber.client.entriy;

/**
 * Created by feng on 2015/8/8.
 */
public class ApiConstants {

    /**
     * 客户端API函数 -- 添加车源信息
     */
    public static final int ADD_CAR_INDEX = 1;

    public static final String ADD_CAR_INDEX_DESC = "ADD_CAR_INDEX";

    public static String getDesc(int apiType){
        String result = "";
        switch (apiType){
            case 1:
                result = ApiConstants.ADD_CAR_INDEX_DESC;
                break;
            default:
                break;
        }
        return result;
    }
}
