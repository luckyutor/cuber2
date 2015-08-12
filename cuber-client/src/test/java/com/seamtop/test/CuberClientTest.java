package com.seamtop.test;

import com.seamtop.cuber.client.CuberClient;
import org.apache.commons.collections.map.UnmodifiableMap;
import org.apache.commons.configuration.Configuration;
import org.apache.hadoop.hbase.client.Put;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;



import java.util.List;

/**
 * Created by feng on 2015/8/8.
 */
public class CuberClientTest {

    @Test
    public void testAddCarIndex(){
        HashMap<String,String> paramsMap = new HashMap<String, String>();
        paramsMap.put("car_id","1");
        paramsMap.put("province_id","110000");
        paramsMap.put("city_id","111000");
        paramsMap.put("license_date",new Date().getTime()+"");
        paramsMap.put("brand_id","191");
        paramsMap.put("model_id","2344");
        paramsMap.put("trimm_id","3700");
        paramsMap.put("dealer_id","110000");
        paramsMap.put("sale_price","120000");
        paramsMap.put("create_date",new Date().getTime()+"");
        paramsMap.put("color","黑色");
        paramsMap.put("mileage", "1000");
        paramsMap.put("flag_source","0");
        paramsMap.put("flag","0");
        try{
            CuberClient.INSTANCE.addCarIndex(paramsMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void test2(){
        String s = "[{\"totalColumns\":1,\"families\":{\"base_family\":[{\"timestamp\":9223372036854775807,\"tag\":[],\"qualifier\":\"sale_price\",\"vlen\":6}]},\"row\":\"\\\\x00\\\\x00\\\\x00\\\\x01\"}, {\"totalColumns\":1,\"families\":{\"base_family\":[{\"timestamp\":9223372036854775807,\"tag\":[],\"qualifier\":\"mileage\",\"vlen\":4}]},\"row\":\"\\\\x00\\\\x00\\\\x00\\\\x01\"}, {\"totalColumns\":1,\"families\":{\"base_family\":[{\"timestamp\":9223372036854775807,\"tag\":[],\"qualifier\":\"province_id\",\"vlen\":6}]},\"row\":\"\\\\x00\\\\x00\\\\x00\\\\x01\"}, {\"totalColumns\":1,\"families\":{\"base_family\":[{\"timestamp\":9223372036854775807,\"tag\":[],\"qualifier\":\"brand_id\",\"vlen\":3}]},\"row\":\"\\\\x00\\\\x00\\\\x00\\\\x01\"}, {\"totalColumns\":1,\"families\":{\"base_family\":[{\"timestamp\":9223372036854775807,\"tag\":[],\"qualifier\":\"trimm_id\",\"vlen\":4}]},\"row\":\"\\\\x00\\\\x00\\\\x00\\\\x01\"}, {\"totalColumns\":1,\"families\":{\"base_family\":[{\"timestamp\":9223372036854775807,\"tag\":[],\"qualifier\":\"city_id\",\"vlen\":6}]},\"row\":\"\\\\x00\\\\x00\\\\x00\\\\x01\"}, {\"totalColumns\":1,\"families\":{\"base_family\":[{\"timestamp\":9223372036854775807,\"tag\":[],\"qualifier\":\"flag\",\"vlen\":1}]},\"row\":\"\\\\x00\\\\x00\\\\x00\\\\x01\"}, {\"totalColumns\":1,\"families\":{\"base_family\":[{\"timestamp\":9223372036854775807,\"tag\":[],\"qualifier\":\"create_date\",\"vlen\":13}]},\"row\":\"\\\\x00\\\\x00\\\\x00\\\\x01\"}, {\"totalColumns\":1,\"families\":{\"base_family\":[{\"timestamp\":9223372036854775807,\"tag\":[],\"qualifier\":\"color\",\"vlen\":6}]},\"row\":\"\\\\x00\\\\x00\\\\x00\\\\x01\"}, {\"totalColumns\":1,\"families\":{\"base_family\":[{\"timestamp\":9223372036854775807,\"tag\":[],\"qualifier\":\"model_id\",\"vlen\":4}]},\"row\":\"\\\\x00\\\\x00\\\\x00\\\\x01\"}, {\"totalColumns\":1,\"families\":{\"base_family\":[{\"timestamp\":9223372036854775807,\"tag\":[],\"qualifier\":\"dealer_id\",\"vlen\":6}]},\"row\":\"\\\\x00\\\\x00\\\\x00\\\\x01\"}, {\"totalColumns\":1,\"families\":{\"base_family\":[{\"timestamp\":9223372036854775807,\"tag\":[],\"qualifier\":\"flag_source\",\"vlen\":1}]},\"row\":\"\\\\x00\\\\x00\\\\x00\\\\x01\"}, {\"totalColumns\":1,\"families\":{\"base_family\":[{\"timestamp\":9223372036854775807,\"tag\":[],\"qualifier\":\"license_date\",\"vlen\":13}]},\"row\":\"\\\\x00\\\\x00\\\\x00\\\\x01\"}]";


    }
}
