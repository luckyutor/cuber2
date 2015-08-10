package com.seamtop.cuber.storage.kafka;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by feng on 2015/8/9.
 */
public class CuberSplitter extends BaseRichBolt {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CuberSplitter.class);

    private static final long serialVersionUID = 886149197481637894L;

    private OutputCollector collector;

    public void prepare(Map stormConf, TopologyContext context,
                        OutputCollector collector) {
        this.collector = collector;
    }

    public void execute(Tuple input) {
        String line = input.getString(0);
        LOG.info("Cuber Storage - dispose msg:"+ line);
//        JSONObject jsonObject = JSON.parseObject(line);

//        String[] words = line.split("\\s+");
//        for(String word : words) {
//            LOG.info("EMIT[splitter -> counter] " + word);
//            collector.emit(input, new Values(word, 1));
//        }
//        collector.ack(input);
        collector.emit(input,new Values(line));
        collector.ack(input);
    }


    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("line"));
    }

}
