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

    //参数验证
    public static void calibration(int caliType,HashMap<String,String> paramMap) throws Exception{
        Boolean result = null;
        switch (caliType){
            case CalibrationConstants.API_ADD_CAR_INDEX:
                caliAddCarParams(paramMap);
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
    public static  void caliAddCarParams(HashMap<String,String> paramsMap) throws Exception{
        if(paramsMap == null || paramsMap.size() == 0){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_IS_NULL,null).toString());
        }

        //carId不可为空且必须为数字
        String carIdStr = paramsMap.get(ParamsContants.CAR_ID);
        if(StringUtil.isEmpty(carIdStr)){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_IS_NULL,ParamsContants.CAR_ID).toString());
        }
        if(!StringUtil.isDigital(carIdStr)){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_FORMAT_ERROR,ParamsContants.CAR_ID).toString());
        }

        //province不可为空且必须为数字
        String provinceIdStr = paramsMap.get(ParamsContants.PROVINCE_ID);
        if(StringUtil.isEmpty(provinceIdStr)){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_IS_NULL,ParamsContants.PROVINCE_ID).toString());
        }
        if(!StringUtil.isDigital(provinceIdStr)){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_FORMAT_ERROR,ParamsContants.PROVINCE_ID).toString());
        }




    }
}
