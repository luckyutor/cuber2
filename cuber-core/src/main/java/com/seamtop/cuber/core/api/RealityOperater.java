package com.seamtop.cuber.core.api;

import com.seamtop.cuber.common.entriy.ErrorCode;
import com.seamtop.cuber.core.api.IOperater;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 */
public class RealityOperater implements IOperater {

    public void execute(int apiType,HashMap<String,String> paramsMap) throws Exception{
        System.out.println("reality execute！");
        //参数验证
        ParamsCalibration.calibration(ApiConstants.ADD_CAR_INDEX, paramsMap);

    }
}
