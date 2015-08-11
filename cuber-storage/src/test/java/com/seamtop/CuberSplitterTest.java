package com.seamtop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seamtop.cuber.storage.kafka.CuberSplitter;

import org.apache.hadoop.util.hash.Hash;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/9.
 */
public class CuberSplitterTest {
    @Test
    public void testCuberSplitter(){
        String res = "{\"task\":{\"messageList\":[{\"msgData\":{\"sale_price\":\"120000\",\"mileage\":\"1000\",\"province_id\":\"110000\",\"brand_id\":\"191\",\"first_license_date\":\"1439272017637\",\"trimm_id\":\"3700\",\"id\":\"1\",\"city_id\":\"111000\",\"flag\":\"0\",\"create_date\":\"1439272017637\",\"color\":\"黑色\",\"flag_source\":\"0\",\"model_id\":\"2344\",\"dealer_id\":\"110000\"},\"msgType\":\"ADD_CAR_INDEX\"}],\"taskId\":143927201774758,\"taskSource\":1,\"taskTime\":1439272017747}}";
        JSONObject taskObject = JSON.parseObject(res);
        JSONObject jsonObject = (JSONObject)taskObject.get("task");
        JSONArray msgArray = (JSONArray)jsonObject.get("messageList");
        emitMsg(msgArray);
    }

    public void emitMsg(JSONArray array){
        for(int i=0;i<array.size();i++){
            JSONObject jsonObject = (JSONObject)array.get(i);
            System.out.println(jsonObject);
            String msgType = jsonObject.getString("msgType");
            System.out.println(msgType);


        }
    }
}
