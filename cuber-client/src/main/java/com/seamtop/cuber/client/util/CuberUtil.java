package com.seamtop.cuber.client.util;

import com.alibaba.fastjson.JSONObject;
import com.seamtop.cuber.client.entriy.CuberTask;
import com.seamtop.cuber.client.entriy.Message;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/9.
 */
public class CuberUtil {

    public static String transTaskJson(Message message){
        CuberTask task = new CuberTask();
        task.setTaskSource(1);
        task.setMessage(message);
        task.setTaskSource(1);
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("task", task);
        String s = JSONObject.toJSONString(map);
        System.out.println("s:"+s);
        return s;
    }
}
