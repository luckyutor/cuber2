package com.seamtop.cuber.core.queue;

/**
 * Created by feng on 2015/8/9.
 */
public interface QueueSenderAdapter {

    public void send(String msg) throws Exception;
}
