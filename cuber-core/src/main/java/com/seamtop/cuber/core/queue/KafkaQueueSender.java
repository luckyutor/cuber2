package com.seamtop.cuber.core.queue;

import org.apache.log4j.Logger;

/**
 * Created by feng on 2015/8/9.
 */
public class KafkaQueueSender implements QueueSender{

    private static final Logger LOG = Logger.getLogger(KafkaQueueSender.class);

    public void send(String msg) throws Exception {
        LOG.debug("cuber send msg:" + msg);
    }
}
