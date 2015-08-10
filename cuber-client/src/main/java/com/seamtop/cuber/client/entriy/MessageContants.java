package com.seamtop.cuber.client.entriy;

/**
 * Created by zongjunfeng on 2015/8/10.
 *
 * 消息常量类
 */
public class MessageContants {

    /**
     * 客户端API函数 -- 添加车源信息
     */
    public static final int MSG_ADD_CAR_INDEX = 1;

    public static final String MSG_ADD_CAR_INDEX_DESC = "ADD_CAR_INDEX";

    public static String getDesc(int msgType){
        String result = "";
        switch (msgType){
            case 1:
                result = MessageContants.MSG_ADD_CAR_INDEX_DESC;
                break;
            default:
                break;
        }
        return result;
    }


}
