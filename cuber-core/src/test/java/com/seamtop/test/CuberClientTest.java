package com.seamtop.test;

import com.seamtop.cuber.core.api.CuberClient;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 */
public class CuberClientTest {

    @Test
    public void testAddCarIndex(){
        HashMap<String,String> paramsMap = new HashMap<String, String>();
        paramsMap.put("id","1");
        paramsMap.put("province_id","110000");
        paramsMap.put("city_id","111000");
        paramsMap.put("first_license_date",new Date().getTime()+"");
        paramsMap.put("brand_id","191");
        paramsMap.put("model_id","2344");
        paramsMap.put("trimm_id","3700");
        paramsMap.put("dealer_id","110000");
        paramsMap.put("sale_price","120000");
        paramsMap.put("create_date",new Date().getTime()+"");
        paramsMap.put("color","黑色");
        paramsMap.put("mileage", "1000");
        try{
            CuberClient.INSTANCE.addCarIndex(paramsMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
