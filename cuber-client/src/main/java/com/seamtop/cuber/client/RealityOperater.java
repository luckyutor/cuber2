package com.seamtop.cuber.client;

import com.seamtop.cuber.common.conf.CuberConfiger;
import com.seamtop.cuber.common.entriy.Message;
import com.seamtop.cuber.common.util.CuberUtil;
import com.seamtop.cuber.client.queue.KafkaQueueSender;
import com.seamtop.cuber.client.queue.QueueSenderAdapter;
import com.seamtop.cuber.common.params.ParamsCalibration;
import org.slf4j.LoggerFactory;

/**
 * Created by feng on 2015/8/8.
 */
public class RealityOperater implements IOperater {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RealityOperater.class);

    public void execute(Message message) throws Exception{
        LOG.debug("CuberClient - invoke method:"+ message.getMsgType() + " parameter:"+message.getMsgData());
        //参数验证
        ParamsCalibration.calibration(message);
        //判断是及时发送还是定时发送
        String mode = CuberConfiger.clientConfigProperties.getProperty("send.message.mode");
        if("intime".equals(mode)){//及时发送消息
            String json = CuberUtil.transTaskJson(message);
            QueueSenderAdapter sender = KafkaQueueSender.getInstance();
            System.out.println("json:"+json);
            sender.send(json);
        }
    }
}
