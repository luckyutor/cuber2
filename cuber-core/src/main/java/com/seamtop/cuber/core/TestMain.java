package com.seamtop.cuber.core;

import com.seamtop.cuber.core.api.CuberApplication;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 */
public class TestMain {
    public static void main(String [] args){
        HashMap<String,Object> pramsMap = new HashMap<String, Object>();
        pramsMap.put("car_id", "10001");
        pramsMap.put("province", "110000");
        try{
            CuberApplication.INSTANCE.addCarIndex(pramsMap);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
