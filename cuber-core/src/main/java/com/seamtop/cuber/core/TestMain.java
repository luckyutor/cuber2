package com.seamtop.cuber.core;

import com.seamtop.cuber.core.api.CuberClient;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 */
public class TestMain {
    public static void main(String [] args){
        HashMap<String,Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("car_id","1");
        try{
            CuberClient.INSTANCE.addCarIndex(paramsMap);
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
