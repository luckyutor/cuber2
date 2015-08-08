package com.seamtop.cuber.common.params;

import com.seamtop.cuber.common.StringUtil;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 * 参数校验实体类
 */
public class ParamsCalibration {

    public static boolean calibration(int caliType,HashMap<String,Object> paramMap){
        boolean result = false;
        switch (caliType){
            case CalibrationConstants.API_ADD_CAR_INDEX:
                result = caliAddCarParamsCali(paramMap);
                break;
            case 2:
                break;
        }
        return false;
    }

    /**
     * 添加车源方法参数验证
     * @param paramsMap
     * @return
     */
    public static boolean caliAddCarParamsCali(HashMap<String,Object> paramsMap){
        if(paramsMap == null || paramsMap.size() == 0){
            return false;
        }

        //carId不可为空且必须为数字
        Object oCarId = paramsMap.get("car_id");
        if(!StringUtil.isDigital((String)oCarId)){
            return false;
        }
        return true;
    }
}
