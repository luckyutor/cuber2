package com.seamtop.cuber.core.api;

import com.seamtop.cuber.common.StringUtil;
import com.seamtop.cuber.common.entriy.Result;
import com.seamtop.cuber.common.params.ParamsCalibration;
import com.seamtop.cuber.core.api.car.CarIndexAddOperater;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 * 对客户端接口API
 */
public enum CuberClient {

    INSTANCE;

    //操作抽象
    private IOperater operater;

    //增加车源实现
    public void addCarIndex(final HashMap<String,Object> paramsMap) throws Exception{
        Result result = ParamsCalibration.caliAddCarParamsCali(paramsMap);
        if(result.isSuccess()){
            IOperater operater = new CarIndexAddOperater();
            result = operater.execute(paramsMap);
        }

    }

    //删除车源实现
    public void deleteCarIndex(final HashMap<String,Object> paramsMap)  throws Exception{

    }

    //更新车源实现
    public void updateCarIntex(final HashMap<String,Object> paramsMap)  throws Exception{

    }

    //查询车源实现
    public String queryCarByIndex(final HashMap<String,Object> paramsMap)  throws Exception{
        return null;
    }

    //增加点击次数实现
    public void addHitCountIndex(final HashMap<String,Object> paramsMap) throws Exception{

    }
}
