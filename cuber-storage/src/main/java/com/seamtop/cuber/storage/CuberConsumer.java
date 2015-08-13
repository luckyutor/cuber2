package com.seamtop.cuber.storage;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import storm.kafka.*;

import java.util.Arrays;

/**
 * Created by feng on 2015/8/9.
 * Kafka消息队列消费者
 */
public class CuberConsumer {

    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, InterruptedException {
        String zks = "zookeeper.cuber.seamtop.com:2181";
        String topic = "test";
        String zkRoot = "/storm";
        String id = "word";
        BrokerHosts brokerHosts = new ZkHosts(zks);
        SpoutConfig spoutConf = new SpoutConfig(brokerHosts, topic, zkRoot, id);
        spoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());
        spoutConf.forceFromStart = false;
        spoutConf.zkServers = Arrays.asList(new String[] {"zookeeper.cuber.seamtop.com"});
        spoutConf.zkPort = 2181;

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafka-reader", new KafkaSpout(spoutConf), 2);
        builder.setBolt("hbase-splitter", new CuberSplitter(), 2).shuffleGrouping("kafka-reader");
        builder.setBolt("hbase-bolt", new HBaseBolt()).fieldsGrouping("hbase-splitter", new Fields("s","transDataList"));
        Config conf = new Config();
        String name = CuberConsumer.class.getSimpleName();
        if (args != null && args.length > 0) {
            conf.put(Config.NIMBUS_HOST, args[0]);
            conf.setNumWorkers(2);
            StormSubmitter.submitTopologyWithProgressBar(name, conf, builder.createTopology());
        } else {
            conf.setMaxTaskParallelism(3);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology(name, conf, builder.createTopology());
            Thread.sleep(3000);
            cluster.shutdown();
        }
    }
}
