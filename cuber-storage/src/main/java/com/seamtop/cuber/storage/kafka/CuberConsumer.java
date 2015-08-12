package com.seamtop.cuber.storage.kafka;
import java.util.Arrays;

import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

/**
 * Created by feng on 2015/8/9.
 * Kafka消息队列消费者
 */
public class CuberConsumer {

    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, InterruptedException {
        //String zks = "192.168.126.130:2181";
        String zks = "192.168.45.52:2181";
        String topic = "test";
        String zkRoot = "/storm"; // default zookeeper root configuration for storm
        String id = "word";

        BrokerHosts brokerHosts = new ZkHosts(zks);
        SpoutConfig spoutConf = new SpoutConfig(brokerHosts, topic, zkRoot, id);
        spoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());
        spoutConf.forceFromStart = false;
        spoutConf.zkServers = Arrays.asList(new String[] {"192.168.45.52"});
        spoutConf.zkPort = 2181;

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafka-reader", new KafkaSpout(spoutConf), 2); // Kafka我们创建了一个5分区的Topic，这里并行度设置为5
        builder.setBolt("word-splitter", new CuberSplitter(), 2).globalGrouping("kafka-reader");
        //builder.setBolt("word-splitter", new CuberSplitter(), 1).shuffleGrouping("kafka-reader");
        builder.setBolt("word-counter", new WordCounter()).fieldsGrouping("word-splitter", new Fields("s","putList"));

        Config conf = new Config();

        String name = CuberConsumer.class.getSimpleName();
        if (args != null && args.length > 0) {
            // Nimbus host name passed from command line
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
