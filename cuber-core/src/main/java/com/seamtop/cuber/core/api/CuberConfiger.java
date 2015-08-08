package com.seamtop.cuber.core.api;

import com.seamtop.cuber.common.util.PropertiesUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.util.HashMap;
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
        LOG.debug("load cuber config file:" + filePath);
        cuberConfigProperties = PropertiesUtil.loadProperties(filePath);
    }
}
