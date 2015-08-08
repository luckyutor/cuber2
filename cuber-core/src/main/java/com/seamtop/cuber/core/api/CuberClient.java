package com.seamtop.cuber.core.api;

import com.seamtop.cuber.common.params.CalibrationConstants;
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
    public void addCarIndex(final HashMap<String,String> paramsMap) throws Exception{
        ParamsCalibration.calibration(CalibrationConstants.API_ADD_CAR_INDEX,paramsMap);
        IOperater operater = new CarIndexAddOperater();
        operater.execute(paramsMap);
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
