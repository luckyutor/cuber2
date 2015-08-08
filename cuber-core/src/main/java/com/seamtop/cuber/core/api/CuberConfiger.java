package com.seamtop.cuber.core.api;

import com.seamtop.cuber.common.util.PropertiesUtil;

import java.util.HashMap;
import java.util.Properties;

/**
 * Created by feng on 2015/8/8.
 */
public class CuberConfiger {

    public static Properties cuberConfigProperties = null;
    public static void loadConfiguration() throws Exception{
        String path = CuberConfiger.class.getClassLoader().getResource("").getPath();
        String filePath = path + "cuber_config.properties";
        cuberConfigProperties = PropertiesUtil.loadProperties(filePath);
    }
}
