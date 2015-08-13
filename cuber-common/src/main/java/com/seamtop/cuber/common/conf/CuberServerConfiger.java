package com.seamtop.cuber.common.conf;

import com.seamtop.cuber.common.util.PropertiesUtil;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * Created by feng on 2015/8/8.
 */
public class CuberServerConfiger {

    private static final Logger LOG = Logger.getLogger(CuberServerConfiger.class);

    public static final Properties serverConfigProperties = loadConfiguration();

    public static Properties loadConfiguration(){
        String path = CuberServerConfiger.class.getClassLoader().getResource("").getPath();
        String filePath = path + "cuber_server_conf.properties";
        LOG.debug("Load cuber server config file:" + filePath);
        Properties cuberConfigProperties = null;
        try {
            cuberConfigProperties = PropertiesUtil.loadProperties(filePath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cuberConfigProperties;
    }
    
}
