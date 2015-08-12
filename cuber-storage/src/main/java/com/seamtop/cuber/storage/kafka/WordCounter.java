package com.seamtop.cuber.storage.kafka;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import com.seamtop.cuber.storage.HBaseUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by feng on 2015/8/9.
 */
public  class WordCounter extends BaseRichBolt {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(WordCounter.class);
    private static final long serialVersionUID = 886149197481637894L;
    private OutputCollector collector;
    private Map<String, AtomicInteger> counterMap;


    public void prepare(Map stormConf, TopologyContext context,
                        OutputCollector collector) {
        this.collector = collector;
        this.counterMap = new HashMap<String, AtomicInteger>();
    }

    public void execute(Tuple input){
        System.out.println("WordCounter--------kakaka:"+input);
        String operate = input.getString(0);
        List<TransData> transDataList = (List<TransData>)input.getValue(1);
        System.out.println("transDataList:"+transDataList);
        Configuration conf = HBaseConfiguration.create();
        System.out.println("conf:"+conf);
        List<Put> putList = new ArrayList<Put>();
        HTableInterface table = null;
        try{
             //table = new HTable(conf,"CUBER_CAR");
            table = HBaseUtil.getHConnection().getTable("CUBER_CAR");
        }catch (Exception e){
            e.printStackTrace();
        }

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
        System.out.println("input---add ok-------================================================");
//        String word = input.getString(0);
//        System.out.println("word=============================================================================:"+word);
////        int count = input.getInteger(1);
////        LOG.info("RECV[splitter -> counter] " + word + " : " + count);
////        System.out.println("RECV[splitter -> counter] " + word + " : " + count);
////        AtomicInteger ai = this.counterMap.get(word);
////        if(ai == null) {
////            ai = new AtomicInteger();
////            this.counterMap.put(word, ai);
////        }
////        ai.addAndGet(count);
        collector.ack(input);
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
        declarer.declare(new Fields("word", "count"));
    }
}
