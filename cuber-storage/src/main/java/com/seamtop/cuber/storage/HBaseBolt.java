package com.seamtop.cuber.storage;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import com.seamtop.cuber.common.entriy.TransData;
import com.seamtop.cuber.storage.util.HBaseUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by feng on 2015/8/9.
 */
public  class HBaseBolt extends BaseRichBolt {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(HBaseBolt.class);
    private static final long serialVersionUID = 886149197481637894L;
    private OutputCollector collector;
    private Map<String, AtomicInteger> counterMap;


    public void prepare(Map stormConf, TopologyContext context,
                        OutputCollector collector) {
        this.collector = collector;
        this.counterMap = new HashMap<String, AtomicInteger>();
    }

    public void execute(Tuple input){
        String operateTable = input.getString(0);
        int operateType = input.getInteger(1);
        List<TransData> transDataList = (List<TransData>)input.getValue(2);
        Configuration conf = HBaseConfiguration.create();
        HTableInterface table = null;
        try{
            table = HBaseUtil.getHConnection().getTable(operateTable);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(table == null){
            return;
        }
        switch (operateType){
            case 0:
                addData(transDataList,table);
                break;
            case 1:
                break;
            case 2:
                break;
        }
        collector.ack(input);
        LOG.info("--------------------message process ok--------------------");
    }

    private void addData(List<TransData> transDataList,HTableInterface table){
        List<Put> putList = new ArrayList<Put>();
        for(int i=0;i<transDataList.size();i++){
            TransData transData = transDataList.get(i);
            Put p = new Put(Bytes.toBytes(transData.getRowKey()));
            p.add(Bytes.toBytes(transData.getFamilyName()),Bytes.toBytes(transData.getColumnName()),Bytes.toBytes(transData.getValue()));
            putList.add(p);
        }
        try{
            table.put(putList);
            table.close();
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    @Override
    public void cleanup() {
        LOG.info("The final result:");
        Iterator<Map.Entry<String, AtomicInteger>> iter = this.counterMap.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<String, AtomicInteger> entry = iter.next();
            LOG.info(entry.getKey() + "\t:\t" + entry.getValue().get());
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }
}
