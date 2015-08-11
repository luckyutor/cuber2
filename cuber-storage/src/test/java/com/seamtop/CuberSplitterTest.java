package com.seamtop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seamtop.cuber.common.base.DataObject;
import com.seamtop.cuber.common.exception.ColumnNotExistException;
import com.seamtop.cuber.common.exception.CuberParamsProcessException;
import com.seamtop.cuber.common.metadata.Column;
import com.seamtop.cuber.common.metadata.RowKey;
import com.seamtop.cuber.common.metadata.TableMetaData;
import com.seamtop.cuber.common.metadata.ValueTypeContants;
import com.seamtop.cuber.common.tableoperator.TableOperatorBean;
import com.seamtop.cuber.common.util.DateUtil;
import com.seamtop.cuber.common.util.JSONUtil;
import com.seamtop.cuber.common.util.StringUtil;
import com.seamtop.cuber.storage.kafka.CuberSplitter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.util.hash.Hash;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by feng on 2015/8/9.
 */
public class CuberSplitterTest {
    @Test
    public void testCuberSplitter() throws Exception{
        String res = "{\"task\":{\"messageList\":[{\"msgData\":{\"sale_price\":\"120000\",\"mileage\":\"1000\"," +
                "\"province_id\":\"110000\",\"brand_id\":\"191\",\"license_date\":\"1439272017637\"," +
                "\"trimm_id\":\"3700\",\"car_id\":\"1\",\"city_id\":\"111000\",\"flag\":\"0\"," +
                "\"create_date\":\"1439272017637\",\"color\":\"黑色\",\"flag_source\":\"0\",\"model_id\":" +
                "\"2344\",\"dealer_id\":\"110000\"},\"msgType\":\"ADD_CAR_INDEX\"}]," +
                "\"taskId\":143927201774758,\"taskSource\":1,\"taskTime\":1439272017747}}";
        JSONObject taskObject = JSON.parseObject(res);
        JSONObject jsonObject = (JSONObject)taskObject.get("task");
        JSONArray msgArray = (JSONArray)jsonObject.get("messageList");
        emitMsg(msgArray);
    }

    public void emitMsg(JSONArray array) throws Exception{
        for(int i=0;i<array.size();i++){
            JSONObject jsonObject = (JSONObject)array.get(i);
            String msgType = jsonObject.getString("msgType");
            JSONObject dataObject = jsonObject.getJSONObject("msgData");
            HashMap<String,String> dataMap = JSONUtil.jsonToMap(dataObject);
            TableOperatorBean operatorBean = DataObject.INSTANCE.operatorDataMap.get(msgType);
            TableMetaData tableMetaData = DataObject.INSTANCE.metaDataMap.get(operatorBean.getOperatorTable());
            this.caliAddOperatorParams(dataMap,tableMetaData);
//            List<Put> putList = getPutList(dataMap,tableMetaData);
//            Configuration conf = HBaseConfiguration.create();
//            HTable table = new HTable(conf,tableMetaData.getTableName());
//            table.put(putList);
//            table.close();
        }
    }


    public List<Put> getPutList(HashMap<String,String> dataMap,TableMetaData tableMetaData){
        HashMap<String,Column> columnMap = tableMetaData.getColumnMap();
        List<Put> list = new ArrayList<Put>();
        for(String params : dataMap.keySet()){
            Column column = columnMap.get(params);
            if(column == null){
                continue;
            }
            int rowKey = Integer.parseInt(dataMap.get("id"));
            String columnName = column.getColumnName();
            String value = dataMap.get(columnName);
            System.out.println("value"+value);
            System.out.println("rowKey:"+rowKey);
            Put p = new Put(Bytes.toBytes(rowKey));
            p.add(Bytes.toBytes(column.getFamilyName()),Bytes.toBytes(columnName),Bytes.toBytes(value));
            list.add(p);
        }
        return list;
    }


    /**
     * 数据校验实现 -- 判断数据添加参数
     * @param dataMap
     * @param tableMetaData
     * @return
     */
    public boolean caliAddOperatorParams(HashMap<String,String> dataMap,TableMetaData tableMetaData) throws Exception{
        if(dataMap == null || dataMap.size() == 0 || tableMetaData == null){
            throw new CuberParamsProcessException("数据存储异常");
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
                    throw new ColumnNotExistException("表"+ tableMetaData.getTableName()+"中列"+param + "不存在");
                }
                isRequired = column.isIfRequired();
                valueType = column.getColumnType();
                maxSize = column.getColumnMaxSize();
            }
            //数据类型判断
            if(valueType != 0){
                boolean result = isTypeCorrect(valueType,value);
                if(!result){
                    throw new CuberParamsProcessException("表"+ tableMetaData.getTableName()+"中列"+param + "参数格式错误");
                }
            }
            //是否必填
            if(isRequired && StringUtil.isEmpty(value)){
                throw new CuberParamsProcessException("表"+ tableMetaData.getTableName()+"中列"+param + "不可为空");
            }
            //数据长度判断
            if(maxSize > 0 && value.length() > maxSize){
                throw new CuberParamsProcessException("表"+ tableMetaData.getTableName()+"中列"+param + "超出字符限制");
            }
        }

        //判断XML必填字段传入参数中是否均已包含

        return true;
    }

    private boolean isTypeCorrect(int valueType,String str) throws Exception{
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





    public List<Delete> getDeleteList(){
        return null;
    }

    public List<Put> getUpdateList(){
        return null;
    }
}
