package com.seamtop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zongjunfeng on 2015/8/10.
 */
public class HBaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(HBaseTest.class);

    static Configuration conf = HBaseConfiguration.create();



    /**
     * ������ṹ
     * @param tableName
     * @param columnFamilys
     * @throws Exception
     */
    public static void createTable(String tableName,String [] columnFamilys) throws Exception{
        HBaseAdmin admin = new HBaseAdmin(conf);
        System.out.println("admin:"+admin);
        if(admin.tableExists(tableName)){
            LOG.info("table exist now");
        }else{
            HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tableName));
            for(String columnFamily:columnFamilys){
                tableDesc.addFamily(new HColumnDescriptor(columnFamily));
            }
            admin.createTable(tableDesc);
            LOG.info("create ok !");
        }
    }

    public static void insertList(String tableName,String rows[])throws Exception{
        HTable table = new HTable(conf,tableName);
        List<Put> list = new ArrayList<Put>();
        for(String r :rows){
            Put p = new Put(Bytes.toBytes(r));
            p.add(Bytes.toBytes("family1"),Bytes.toBytes("column1"),Bytes.toBytes("fff2"));
            list.add(p);
        }
        table.put(list);
        table.close();
    }

    public static void main(String [] args) throws Exception{
        String[] str = new String[]{"base_family"};
        createTable("CUBER_CAR",str);
//        String [] rows = new String[]{"row-1"};
//        createTable("CUBER_CAR",rows);
    }
}
