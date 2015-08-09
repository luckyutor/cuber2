package com.seamtop.cuber.core;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;
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
        return JSONObject.toJSONString(map);
    }
}
