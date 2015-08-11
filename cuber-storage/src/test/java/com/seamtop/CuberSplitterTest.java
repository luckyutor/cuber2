package com.seamtop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seamtop.cuber.common.base.DataObject;
import com.seamtop.cuber.common.metadata.Column;
import com.seamtop.cuber.common.metadata.TableMetaData;
import com.seamtop.cuber.common.tableoperator.TableOperatorBean;
import com.seamtop.cuber.common.util.JSONUtil;
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
        String res = "{\"task\":{\"messageList\":[{\"msgData\":{\"sale_price\":\"120000\",\"mileage\":\"1000\",\"province_id\":\"110000\",\"brand_id\":\"191\",\"first_license_date\":\"1439272017637\",\"trimm_id\":\"3700\",\"id\":\"1\",\"city_id\":\"111000\",\"flag\":\"0\",\"create_date\":\"1439272017637\",\"color\":\"黑色\",\"flag_source\":\"0\",\"model_id\":\"2344\",\"dealer_id\":\"110000\"},\"msgType\":\"ADD_CAR_INDEX\"}],\"taskId\":143927201774758,\"taskSource\":1,\"taskTime\":1439272017747}}";
        JSONObject taskObject = JSON.parseObject(res);
        JSONObject jsonObject = (JSONObject)taskObject.get("task");
        JSONArray msgArray = (JSONArray)jsonObject.get("messageList");
        emitMsg(msgArray);
    }

    public void emitMsg(JSONArray array) throws Exception{
        for(int i=0;i<array.size();i++){
            JSONObject jsonObject = (JSONObject)array.get(i);
            System.out.println(jsonObject);
            String msgType = jsonObject.getString("msgType");
            JSONObject dataObject = jsonObject.getJSONObject("msgData");
            HashMap<String,String> dataMap = JSONUtil.jsonToMap(dataObject);
            TableOperatorBean operatorBean = DataObject.INSTANCE.operatorDataMap.get(msgType);
            System.out.println(operatorBean.getOperatorName() + " " + operatorBean.getOperatorType() + " " + operatorBean.getOperatorTable());
            TableMetaData tableMetaData = DataObject.INSTANCE.metaDataMap.get(operatorBean.getOperatorTable());
            System.out.println("msgType"+msgType);
            List<Put> putList = getPutList(dataMap,tableMetaData);
            Configuration conf = HBaseConfiguration.create();
            HTable table = new HTable(conf,tableMetaData.getTableName());
            table.put(putList);
            table.close();
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
            Put p = new Put(Bytes.toBytes(rowKey));
            p.add(Bytes.toBytes(column.getFamilyName()),Bytes.toBytes(columnName),Bytes.toBytes(value));
            list.add(p);
        }
        return list;
    }




    public List<Delete> getDeleteList(){
        return null;
    }

    public List<Put> getUpdateList(){
        return null;
    }
}
