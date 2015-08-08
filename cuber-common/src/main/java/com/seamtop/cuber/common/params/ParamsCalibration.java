package com.seamtop.cuber.common.params;

import com.seamtop.cuber.common.StringUtil;
import com.seamtop.cuber.common.entriy.ErrorCode;
import com.seamtop.cuber.common.exception.CuberParamsProcessException;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 * 参数校验实体类
 */
public class ParamsCalibration {

    public static void calibration(int caliType,HashMap<String,Object> paramMap) throws Exception{
        Boolean result = null;
        switch (caliType){
            case CalibrationConstants.API_ADD_CAR_INDEX:
                caliAddCarParamsCali(paramMap);
                break;
            case 2:
                break;
        }
    }

    /**
     * 添加车源方法参数验证
     * @param paramsMap
     * @return
     */
    public static  void caliAddCarParamsCali(HashMap<String,Object> paramsMap) throws Exception{
        if(paramsMap == null || paramsMap.size() == 0){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_IS_NULL,null).toString());
        }

        //carId不可为空且必须为数字
        Object oCarId = paramsMap.get("car_id");
        if(StringUtil.isEmpty((String)oCarId)){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_IS_NULL,"car_id").toString());
        }
        if(!StringUtil.isDigital((String)oCarId)){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_FORMAT_ERROR,"car_id").toString());
        }
    }
}
