package com.seamtop.cuber.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by feng on 2015/8/8.
 */
public class PropertiesUtil {

    public static Properties loadProperties(String path) throws Exception{
        File file = new File(path);
        FileInputStream fis = null;
        fis = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fis);
        return properties;
    }
}
