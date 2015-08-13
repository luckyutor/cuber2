package com.seamtop.cuber.common.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;

import java.io.IOException;

/**
 * Created by feng on 2015/8/12.
 */
public class HBaseUtil {
    private static final String QUORUM = "zookeeper.cuber.seamtop.com";
    private static final String CLIENTPORT = "2181";
    private static Configuration conf = null;
    private static HConnection conn = null;

    /**
     * 获取全局唯一的Configuration实例
     * @return
     */
    private static synchronized Configuration getConfiguration()
    {
        if(conf == null)
        {
            conf =  HBaseConfiguration.create();
            conf.set("hbase.zookeeper.quorum", QUORUM);
            conf.set("hbase.zookeeper.property.clientPort", CLIENTPORT);
        }
        return conf;
    }

    /**
     * 获取全局唯一的HConnection实例
     * @return
     * @throws ZooKeeperConnectionException
     */
    public static synchronized HConnection getHConnection() throws ZooKeeperConnectionException,IOException
    {
        if(conn == null)
        {
            conn = HConnectionManager.createConnection(getConfiguration());
        }
        return conn;
    }
}
