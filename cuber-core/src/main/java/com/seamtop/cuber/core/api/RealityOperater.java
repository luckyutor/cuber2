package com.seamtop.cuber.core.api;

import com.alibaba.fastjson.JSONObject;
import com.seamtop.cuber.common.util.StringUtil;
import com.seamtop.cuber.core.queue.KafkaQueueSender;
import com.seamtop.cuber.core.queue.QueueSenderAdapter;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 */
public class RealityOperater implements IOperater {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RealityOperater.class);

    public void execute(int apiType,HashMap<String,String> paramsMap) throws Exception{
        LOG.debug("CuberClient - invoke method:"+ApiConstants.getDesc(apiType) + " parameter:"+paramsMap);
        //参数验证
        ParamsCalibration.calibration(apiType, paramsMap);
        //判断是及时发送还是定时发送
        String mode = CuberConfiger.cuberConfigProperties.getProperty("send.message.mode");
        if("intime".equals(mode)){//及时发送消息
            String json = JSONObject.toJSONString(paramsMap);
            QueueSenderAdapter sender = KafkaQueueSender.getInstance();
            json = java.net.URLEncoder.encode(json,"utf-8");
            sender.send(json);
        }else{//定时发送消息
            TaskListBean.taskList.add(paramsMap);
        }
    }
}
