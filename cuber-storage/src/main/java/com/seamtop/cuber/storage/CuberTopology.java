package com.seamtop.cuber.storage;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import com.seamtop.cuber.common.conf.CuberServerConfiger;
import storm.kafka.*;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by feng on 2015/8/9.
 * Kafka消息队列消费者
 */
public class CuberTopology {

    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, InterruptedException {
        Properties properties =  CuberServerConfiger.serverConfigProperties;
        String zks = properties.getProperty("zookeeper.cluster");
        String topic = "cuber";
        String zkRoot = "/storm";
        String id = "word";
        BrokerHosts brokerHosts = new ZkHosts(zks);
        SpoutConfig spoutConf = new SpoutConfig(brokerHosts, topic, zkRoot, id);
        spoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());
        spoutConf.forceFromStart = false;
        String [] zookeeperStr = zks.split(",");
        String[] zksHosts = new String[zookeeperStr.length];
        for(int i=0;i<zookeeperStr.length;i++){
            zksHosts[i] = zookeeperStr[i].split(":")[0];
        }
        spoutConf.zkServers = Arrays.asList(zksHosts);
        spoutConf.zkPort = 2181;
        TopologyBuilder builder = new TopologyBuilder();
        int queueSpoutNum = Integer.parseInt(properties.getProperty("queue.spout.num"));
        int queueSplitterNum = Integer.parseInt(properties.getProperty("queue.splitter.num"));
        int queueBoltNum = Integer.parseInt(properties.getProperty("queue.bolt.num"));
        builder.setSpout("queue-spout", new KafkaSpout(spoutConf), queueSpoutNum);
        builder.setBolt("queue-splitter", new CuberSplitter(), queueSplitterNum).shuffleGrouping("queue-spout");
        builder.setBolt("queue-bolt", new HBaseBolt(),queueBoltNum).shuffleGrouping("queue-splitter");
        Config conf = new Config();
        String name = CuberTopology.class.getSimpleName();
        conf.put(Config.NIMBUS_HOST, "nimbus.cuber.seamtop.com");
        conf.setNumWorkers(2);
        StormSubmitter.submitTopologyWithProgressBar(name, conf, builder.createTopology());
    }
}
