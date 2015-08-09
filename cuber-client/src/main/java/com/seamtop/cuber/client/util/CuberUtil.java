package com.seamtop.cuber.client.util;

import com.alibaba.fastjson.JSONObject;
import com.seamtop.cuber.client.entriy.CuberTask;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/9.
 */
public class CuberUtil {

    public static String transTaskJson(int apiType,HashMap<String,String> paramsMap){
        CuberTask task = new CuberTask();
        task.setTaskType(apiType);
        task.setTaskData(paramsMap);
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("task", task);
        String s = JSONObject.toJSONString(map);
        System.out.println("s:"+s);
        return s;
    }
}
