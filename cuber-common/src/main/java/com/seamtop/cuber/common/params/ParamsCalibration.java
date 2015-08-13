package com.seamtop.cuber.common.params;

import com.seamtop.cuber.common.entriy.Message;
import com.seamtop.cuber.common.base.DataObject;
import com.seamtop.cuber.common.entriy.ErrorCode;
import com.seamtop.cuber.common.exception.CuberParamsProcessException;
import com.seamtop.cuber.common.metadata.Column;
import com.seamtop.cuber.common.metadata.RowKey;
import com.seamtop.cuber.common.metadata.TableMetaData;
import com.seamtop.cuber.common.tableoperator.OperatorType;
import com.seamtop.cuber.common.tableoperator.TableOperatorBean;
import com.seamtop.cuber.common.util.DateUtil;
import com.seamtop.cuber.common.util.StringUtil;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 * 参数校验实体类
 */
public class ParamsCalibration {

    //参数验证
    public static void calibration(Message message) throws Exception{
        HashMap<String,String> dataMap = message.getMsgData();
        TableOperatorBean operatorBean = DataObject.INSTANCE.operatorDataMap.get(message.getMsgType());
        TableMetaData tableMetaData = DataObject.INSTANCE.metaDataMap.get(operatorBean.getOperatorTable());
        switch (operatorBean.getOperatorType()){
            case OperatorType.TABLE_OPERATOR_TYPE_ADD:
                caliAddOperatorParams(dataMap, tableMetaData);
                break;
            case OperatorType.TABLE_OPERATOR_TYPE_DELETE:
                break;
            case OperatorType.TABLE_OPERATOR_TYPE_UPDATE:
                break;
        }
    }


    /**
     * 数据校验实现 -- 判断数据添加参数
     * @param dataMap
     * @param tableMetaData
     * @return
     */
    public static boolean caliAddOperatorParams(HashMap<String,String> dataMap,TableMetaData tableMetaData) throws Exception{
        if(dataMap == null || dataMap.size() == 0){
            throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_IS_NULL,null).toString());
        }

        //判断传入参数是否符合XML配置要求
        HashMap<String,Column> columnMap = tableMetaData.getColumnMap();
        for(String param : dataMap.keySet()){
            //首先判断该字段是否为主键
            RowKey rowKey = tableMetaData.getRowKey();
            String value = dataMap.get(param);
            int valueType = 0;
            boolean isRequired = false;
            int maxSize = 0;
            if(rowKey.getKeyName().equals(param)){//为主键
                isRequired = true;
                valueType = rowKey.getKeyType();
                maxSize = rowKey.getKeyMaxSize();
            }else {
                //首先判断该参数是否在MetaData中存在
                Column column = columnMap.get(param);
                if(column == null){
                    throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_IS_NULL, param).toString());
                }
                isRequired = column.isIfRequired();
                valueType = column.getColumnType();
                maxSize = column.getColumnMaxSize();
            }
            //数据类型判断
            if(valueType != 0){
                boolean result = isTypeCorrect(valueType,value);
                if(!result){
                    throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_FORMAT_ERROR, param).toString());
                }
            }
            //是否必填
            if(isRequired && StringUtil.isEmpty(value)){
                throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_IS_NULL, param).toString());
            }
            //数据长度判断
            if(maxSize > 0 && value.length() > maxSize){
                throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_FORMAT_ERROR, param).toString());
            }
        }
        //判断XML必填字段传入参数中是否均已包含
        for(String param : columnMap.keySet()){
            Column column = columnMap.get(param);
            if(column.isIfRequired() && StringUtil.isEmpty(dataMap.get(param))){
                throw new CuberParamsProcessException(new ErrorCode(ErrorCode.PARAMS_IS_NULL, param).toString());
            }
        }
        return true;
    }

    private static boolean isTypeCorrect(int valueType,String str) throws Exception{
        boolean result = false;
        switch (valueType){
            case 1://字符串类型
                result = true;
                break;
            case 2://Int 类型
                result = StringUtil.isNumbric(str);
                break;
            case 3://Float类型
                result = StringUtil.isDouble(str);
                break;
            case 4://Double类型
                result = StringUtil.isDouble(str);
                break;
            case 5://LONG类型
                result = StringUtil.isNumbric(str);
                break;
            case 6://日期类型（时间戳）
                result = StringUtil.isNumbric(str) && DateUtil.isTimeStampBeforeNow(str);
                break;
            default:
                result = false;
                break;
        }
        return result;
    }
}
