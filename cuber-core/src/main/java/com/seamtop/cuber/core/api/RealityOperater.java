package com.seamtop.cuber.core.api;

import com.seamtop.cuber.common.entriy.ErrorCode;
import com.seamtop.cuber.core.api.IOperater;
import org.apache.log4j.Logger;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 */
public class RealityOperater implements IOperater {

    private static final Logger LOG = Logger.getLogger(RealityOperater.class);

    public void execute(int apiType,HashMap<String,String> paramsMap) throws Exception{
        LOG.debug("CuberClient - invoke method:"+ApiConstants.getDesc(apiType) + " parameter:"+paramsMap);
        //参数验证
        ParamsCalibration.calibration(ApiConstants.ADD_CAR_INDEX, paramsMap);

    }
}
