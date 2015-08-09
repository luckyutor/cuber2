package com.seamtop.cuber.client.conf;

import com.seamtop.cuber.common.util.PropertiesUtil;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * Created by feng on 2015/8/8.
 */
public class CuberConfiger {

    private static final Logger LOG = Logger.getLogger(CuberConfiger.class);

    public static Properties cuberConfigProperties = null;
    public static void loadConfiguration() throws Exception{
        String path = CuberConfiger.class.getClassLoader().getResource("").getPath();
        String filePath = path + "cuber_config.properties";
        System.out.println("filePath:"+filePath);
        LOG.debug("load cuber config file:" + filePath);
        cuberConfigProperties = PropertiesUtil.loadProperties(filePath);
        System.out.println("cuberConfigProperties:"+cuberConfigProperties);
    }
}
