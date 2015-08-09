package com.seamtop.cuber.client.queue;

/**
 * Created by feng on 2015/8/9.
 */
public interface QueueSenderAdapter {

    public void send(final String msg) throws Exception;
}
