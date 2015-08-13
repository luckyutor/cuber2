package com.seamtop.cuber.storage;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seamtop.cuber.common.base.DataObject;
import com.seamtop.cuber.common.entriy.TransData;
import com.seamtop.cuber.common.metadata.Column;
import com.seamtop.cuber.common.metadata.TableMetaData;
import com.seamtop.cuber.common.params.ParamsCalibration;
import com.seamtop.cuber.common.tableoperator.TableOperatorBean;
import com.seamtop.cuber.common.util.JSONUtil;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by feng on 2015/8/9.
 */
public class CuberSplitter extends BaseRichBolt {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CuberSplitter.class);

    private static final long serialVersionUID = 886149197481637894L;

    private OutputCollector collector;

    public void prepare(Map stormConf, TopologyContext context,
                        OutputCollector collector) {
        this.collector = collector;
    }

    public void execute(Tuple input) {
        String line = input.getString(0);
        LOG.info("Cuber Storage - dispose msg:"+ line);
        JSONObject taskObject = null;
        try{
            taskObject = JSON.parseObject(line);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(taskObject == null){
            return;
        }
        JSONObject jsonObject = (JSONObject)taskObject.get("task");
        JSONArray msgArray = (JSONArray)jsonObject.get("messageList");
        List<TransData> transDataList = null;
        try{
            transDataList = this.emitMsg(msgArray);
        }catch (Exception e){
            e.printStackTrace();
        }
        String s = "add";
        collector.emit(input,new Values(s,transDataList));
        collector.ack(input);
    }

    public List<TransData> emitMsg(JSONArray array) throws Exception{
        List<TransData> transDataList = null;
        for(int i=0;i<array.size();i++){
            JSONObject jsonObject = (JSONObject)array.get(i);
            String msgType = jsonObject.getString("msgType");
            JSONObject dataObject = jsonObject.getJSONObject("msgData");
            HashMap<String,String> dataMap = JSONUtil.jsonToMap(dataObject);
            TableOperatorBean operatorBean = DataObject.INSTANCE.operatorDataMap.get(msgType);
            TableMetaData tableMetaData = DataObject.INSTANCE.metaDataMap.get(operatorBean.getOperatorTable());
            if(ParamsCalibration.caliAddOperatorParams(dataMap, tableMetaData)){
                transDataList = getPutList(dataMap,tableMetaData);
            }
        }
        return transDataList;
    }


    public List<TransData> getPutList(HashMap<String,String> dataMap,TableMetaData tableMetaData){
        HashMap<String,Column> columnMap = tableMetaData.getColumnMap();
        List<TransData> list = new ArrayList<TransData>();
        for(String params : dataMap.keySet()){
            Column column = columnMap.get(params);
            if(column == null){
                continue;
            }
            int rowKey = Integer.parseInt(dataMap.get("car_id"));
            String columnName = column.getColumnName();
            String value = dataMap.get(columnName);
            TransData transData = new TransData();
            transData.setRowKey(rowKey+"");
            transData.setFamilyName(column.getFamilyName());
            transData.setColumnName(columnName);
            transData.setValue(value);
            list.add(transData);
        }
        return list;
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("s","transDataList"));
    }

}
