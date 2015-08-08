package com.seamtop.cuber.core;

import com.seamtop.cuber.core.api.CuberClient;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 */
public class TestMain {
    public static void main(String [] args){
        HashMap<String,String> paramsMap = new HashMap<String, String>();
        paramsMap.put("car_id","1");
        paramsMap.put("province","110000");
        paramsMap.put("city","111000");
        paramsMap.put("dealer_id","110000");
        paramsMap.put("mileage","1000");
        try{
            CuberClient.INSTANCE.addCarIndex(paramsMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
