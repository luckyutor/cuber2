package com.seamtop.cuber.common.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by feng on 2015/8/10.
 */
public class JSONUtil {

    public static HashMap<String,String> jsonToMap(JSONObject jsonObject){
        if(jsonObject == null){
            return null;
        }
        HashMap<String, String> data = new HashMap<String, String>();
        // 将json字符串转换成jsonObject
        Iterator it = jsonObject.keySet().iterator();
        // 遍历jsonObject数据，添加到Map对象
        while (it.hasNext())
        {
            String key = String.valueOf(it.next());
            String value = (String) jsonObject.get(key);
            data.put(key, value);
        }
        return data;
    }
}
