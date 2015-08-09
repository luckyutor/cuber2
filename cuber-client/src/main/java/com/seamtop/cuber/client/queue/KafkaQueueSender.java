package com.seamtop.cuber.client.queue;

import com.seamtop.cuber.client.conf.CuberConfiger;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Properties;

/**
 * Created by feng on 2015/8/9.
 */
public class KafkaQueueSender implements QueueSenderAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaQueueSender.class);

    private volatile static KafkaQueueSender ss = null;

    private static kafka.javaapi.producer.Producer<Integer, String> producer;

    private static final String topic = "test";

    public static KafkaQueueSender getInstance(){
        if(ss ==null){
            synchronized (KafkaQueueSender.class){
                if(ss ==null){
                    ss = new KafkaQueueSender();
                }
            }
        }
        return ss;
    }

    private KafkaQueueSender(){
        String metaBrokerList = null;
        if(producer == null){
            Properties kafkaProps = new Properties();
            kafkaProps.put("serializer.class", "kafka.serializer.StringEncoder");
            metaBrokerList = CuberConfiger.cuberConfigProperties.getProperty("cuber.kafka.server") + ":" + "9092";
            kafkaProps.put("metadata.broker.list", metaBrokerList);
            producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(kafkaProps));
        }
        String topic = CuberConfiger.cuberConfigProperties.getProperty("cuber.kafka.topic.name");
        LOG.debug("kafka broker list:" + metaBrokerList + " topic:"+topic);
    }

    /**
     * 发送kafka客户端消息
     * @param msg
     * @return
     */
    public static void sendKafkaMessage(String msg){
        producer.send(new KeyedMessage<Integer, String>(topic, msg));
    }

    public void send(final String msg) throws Exception {
        sendKafkaMessage(msg);
    }
}
