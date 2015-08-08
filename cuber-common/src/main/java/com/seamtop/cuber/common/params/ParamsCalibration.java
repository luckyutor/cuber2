package com.seamtop.cuber.common.params;

import com.seamtop.cuber.common.util.DateUtil;
import com.seamtop.cuber.common.util.StringUtil;
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

        //city可为空，必须为数字
        String cityIdStr = paramsMap.get(ParamsContants.CITY_ID);
        if(!StringUtil.isEmpty(cityIdStr) && !StringUtil.isDigital(cityIdStr)){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_FORMAT_ERROR,ParamsContants.CITY_ID).toString());
        }

        //mileage不可为空，切必须为double类型
        String mileageStr = paramsMap.get(ParamsContants.MILEAGE);
        if(StringUtil.isEmpty(mileageStr)){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_IS_NULL,ParamsContants.MILEAGE).toString());
        }
        if(!StringUtil.isDouble(mileageStr)){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_FORMAT_ERROR,ParamsContants.MILEAGE).toString());
        }

        //license_date不可为空，且必须为时间戳
        String licenseDateStr = paramsMap.get(ParamsContants.LICENSE_DATE);
        if(StringUtil.isEmpty(licenseDateStr)){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_IS_NULL,ParamsContants.LICENSE_DATE).toString());
        }
        if(!DateUtil.isTimeStampBeforeNow(licenseDateStr)){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_FORMAT_ERROR,ParamsContants.LICENSE_DATE).toString());
        }

        //brandId不可为空，且必须为数字
        String brandIdStr = paramsMap.get(ParamsContants.BRAND_ID);
        if(StringUtil.isEmpty(brandIdStr)){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_IS_NULL,ParamsContants.BRAND_ID).toString());
        }
        if(!StringUtil.isDigital(brandIdStr)){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_FORMAT_ERROR,ParamsContants.BRAND_ID).toString());
        }

        //modelId不可为空，且必须为数字
        String modelIdStr = paramsMap.get(ParamsContants.MODEL_ID);
        if(StringUtil.isEmpty(modelIdStr)){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_IS_NULL,ParamsContants.MODEL_ID).toString());
        }
        if(!StringUtil.isDigital(modelIdStr)){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_FORMAT_ERROR,ParamsContants.MODEL_ID).toString());
        }

    }
}
