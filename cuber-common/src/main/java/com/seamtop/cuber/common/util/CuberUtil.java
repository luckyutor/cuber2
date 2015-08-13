package com.seamtop.cuber.common.util;

import com.alibaba.fastjson.JSONObject;
import com.seamtop.cuber.common.entriy.CuberTask;
import com.seamtop.cuber.common.entriy.Message;

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
        return JSONObject.toJSONString(map);
    }
}
