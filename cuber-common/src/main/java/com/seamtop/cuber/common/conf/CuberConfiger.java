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
        //1 he 4
        String path1 = CuberConfiger.class.getResource("").getPath();
        System.out.println("path1:"+path1);
        String path2 = CuberConfiger.class.getResource("/").getPath();
        System.out.println("path2:"+path2);
        System.out.println("path3"+CuberConfiger.class.getResource("/").getFile());
        System.out.println("path4"+CuberConfiger.class.getResource("").getFile());

        String path = CuberConfiger.class.getClassLoader().getResource("").getPath();
        //String path = ClassLoader.getSystemResource("").getPath();
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
