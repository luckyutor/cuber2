package com.seamtop;

import com.alibaba.fastjson.JSON;
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
        String res = "{\"task\":{\"taskData\":{\"sale_price\":\"120000\",\"mileage\":\"1000\",\"province_id\":\"110000\",\"brand_id\":\"191\",\"first_license_date\":\"1439177426993\",\"trimm_id\":\"3700\",\"id\":\"1\",\"city_id\":\"111000\",\"flag\":\"0\",\"create_date\":\"1439177426993\",\"color\":\"ab\",\"flag_source\":\"0\",\"model_id\":\"2344\",\"dealer_id\":\"110000\"},\"taskId\":143917742711415,\"taskTime\":1439177427114,\"taskType\":1}}";
        JSONObject taskObject = JSON.parseObject(res);
        System.out.println("taskObject"+taskObject);
        JSONObject jsonObject = (JSONObject)taskObject.get("task");
        int taskSource = (Integer)jsonObject.get("taskSource");
        System.out.println(taskSource);
        JSONObject taskDataObject = (JSONObject)jsonObject.get("taskData");
        System.out.println("taskSource:"+taskSource + " map:"+taskDataObject.get("sale_price"));
        System.out.println(taskSource);
        switch (taskSource){
            case 1://为客户端API接口

                break;
            case 2:

        }
    }
}
