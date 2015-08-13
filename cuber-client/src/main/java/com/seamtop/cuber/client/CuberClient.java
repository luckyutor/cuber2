package com.seamtop.cuber.client;

import com.seamtop.cuber.common.entriy.Message;
import com.seamtop.cuber.common.entriy.MessageContants;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 * 对客户端接口API
 */
public enum  CuberClient {

    INSTANCE;

    //操作抽象
    private IOperater operater;

    private CuberClient (){
        CuberOperaterProxy proxy = new CuberOperaterProxy();
        operater = (IOperater)proxy.bind(new RealityOperater());
    }

    //增加车源实现
    public void addCarIndex(final HashMap<String,String> paramsMap) throws Exception{
        Message message = new Message();
        message.setMsgType(MessageContants.MSGTYPE_ADD_CAR_INDEX);
        message.setMsgData(paramsMap);
        operater.execute(message);
    }

    //删除车源实现
    public void deleteCarIndex(final HashMap<String,String> paramsMap)  throws Exception{

    }

    //更新车源实现
    public void updateCarIntex(final HashMap<String,String> paramsMap)  throws Exception{

    }

    //查询车源实现
    public String queryCarByIndex(final HashMap<String,Object> paramsMap)  throws Exception{
        return null;
    }

    //增加点击次数实现
    public void addHitCountIndex(final HashMap<String,Object> paramsMap) throws Exception{

    }
}
