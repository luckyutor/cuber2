package com.seamtop.cuber.common.params;

import com.seamtop.cuber.common.StringUtil;
import com.seamtop.cuber.common.entriy.Result;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 * 参数校验实体类
 */
public class ParamsCalibration {

    public static Result calibration(int caliType,HashMap<String,Object> paramMap){
        Result result = null;
        switch (caliType){
            case CalibrationConstants.API_ADD_CAR_INDEX:
                result = caliAddCarParamsCali(paramMap);
                break;
            case 2:
                break;
        }
        return null;
    }

    /**
     * 添加车源方法参数验证
     * @param paramsMap
     * @return
     */
    public static Result caliAddCarParamsCali(HashMap<String,Object> paramsMap){
        if(paramsMap == null || paramsMap.size() == 0){
            return new Result(Result.PARAMS_IS_NULL,null);
        }

        //carId不可为空且必须为数字
        Object oCarId = paramsMap.get("car_id");
        if(StringUtil.isEmpty((String)oCarId)){
            return new Result(Result.PARAMS_IS_NULL,"car_id");
        }
        if(!StringUtil.isDigital((String)oCarId)){
            return new Result(Result.PARAMS_FORMAT_ERROR,"car_id");
        }
        return new Result(Result.SUCCESS);
    }
}
