package com.seamtop.cuber.client.entriy;

import java.util.HashMap;

/**
 * Created by zongjunfeng on 2015/8/10.
 */
public class Message {

    private String msgType;

    private HashMap<String,String> msgData;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public HashMap<String, String> getMsgData() {
        return msgData;
    }

    public void setMsgData(HashMap<String, String> msgData) {
        this.msgData = msgData;
    }
}
