package com.seamtop.cuber.common.conf;

import com.seamtop.cuber.common.util.PropertiesUtil;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * Created by feng on 2015/8/8.
 */
public class CuberConfiger {

    private static final Logger LOG = Logger.getLogger(CuberConfiger.class);

    public static final Properties clientConfigProperties = loadConfiguration();

    public static Properties loadConfiguration(){
        String path = CuberConfiger.class.getClassLoader().getResource("").getPath();
        String filePath = path + "cuber_conf.properties";
        LOG.debug("Load cuber client config file:" + filePath);
        Properties cuberConfigProperties = null;
        try {
            cuberConfigProperties = PropertiesUtil.loadProperties(filePath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cuberConfigProperties;
    }

}
