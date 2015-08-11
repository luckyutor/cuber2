package com.seamtop.cuber.common.base;

import com.seamtop.cuber.common.exception.CuberFileParseException;
import com.seamtop.cuber.common.metadata.Column;
import com.seamtop.cuber.common.metadata.RowKey;
import com.seamtop.cuber.common.metadata.TableMetaData;
import com.seamtop.cuber.common.metadata.ValueTypeContants;
import com.seamtop.cuber.common.util.StringUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zongjunfeng on 2015/8/11.
 */
public class SysInit {

    public static void main(String [] args){
        loadTableSchema();
    }

    public static HashMap<String,TableMetaData> loadTableSchema(){
        String path = SysInit.class.getClassLoader().getResource("").getPath();
        String filePath = path + "schema.xml";
        HashMap<String,TableMetaData> metaDataMap = null;
        try {
            metaDataMap = loadTableSchema(filePath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return metaDataMap;
    }

    public static HashMap<String,TableMetaData> loadTableSchema(String filePath) throws Exception{

        SAXReader saxReader = new SAXReader();
        Document document = null;
        try{
            document = saxReader.read(new File(filePath));
        }catch (Exception e){
            e.printStackTrace();
        }
        // 获取根元素
        Element root = document.getRootElement();
        List<Element> tableList = root.elements();
        HashMap<String,TableMetaData> metaDataMap = new HashMap<String, TableMetaData>();
        for(int i=0;i<tableList.size();i++){
            TableMetaData tableMetaData = new TableMetaData();
            Element e = tableList.get(i);
            //设置表名
            String tableName = e.attributeValue("name");
            tableMetaData.setTableName(tableName);
            //设置表操作者
            HashMap<String,Integer> tablerOperatorMap = new HashMap<String, Integer>();
            Element tableOperatorElement = e.element("table-operator"); //设置表操作对象
            Element addElement = tableOperatorElement.element("add-operator");
            tablerOperatorMap.put(addElement.getStringValue(),0);//表数据增加操作者
            Element deleteElement = tableOperatorElement.element("delete-operator");
            tablerOperatorMap.put(deleteElement.getStringValue(),1);//表数据删除操作者
            Element updateElement = tableOperatorElement.element("update-operator");
            tablerOperatorMap.put(updateElement.getStringValue(),2);//表数据增加操作者
            tableMetaData.setOperatorMap(tablerOperatorMap);
            //设置主键数据
            Element keyElement = e.element("table-key");
            RowKey rowKey = new RowKey();
            rowKey.setKeyName(keyElement.getStringValue());
            rowKey.setKeyDesc(keyElement.attributeValue("desc"));
            rowKey.setKeyMaxSize(Integer.parseInt(keyElement.attributeValue("maxsize")));
            rowKey.setKeyType(getKeyType(keyElement.attributeValue("type")));
            tableMetaData.setRowKey(rowKey);

            //设置列数据
            HashMap<String,Column> columnMap = new HashMap<String, Column>();
            Element columnsElements = e.element("table-columns");
            List<Element> familyElements = columnsElements.elements("family");
            for(int j=0;j<familyElements.size();j++){
                Element family = familyElements.get(j);
                String familyName = family.attributeValue("name");
                System.out.println(familyName);
                List<Element> elements = family.elements("column");
                for(int m=0;m<elements.size();m++){
                    Element columnElement = elements.get(m);
                    Column column = new Column();
                    column.setFamilyName(familyName);
                    column.setColumnName(columnElement.getStringValue());
                    column.setColumnDesc(columnElement.attributeValue("desc"));
                    column.setColumnType(getKeyType(columnElement.attributeValue("type")));
                    column.setColumnMaxSize(StringUtil.isEmpty(columnElement.attributeValue("maxsize"))?0:Integer.valueOf(columnElement.attributeValue("maxsize")));
                    column.setIfRequired("true".equals(columnElement.attributeValue("isRequired"))?true:false);
                    columnMap.put(columnElement.getStringValue(),column);
                }
            }
            tableMetaData.setColumnMap(columnMap);
            metaDataMap.put(tableName,tableMetaData);
        }
        return metaDataMap;
    }

    public static int getKeyType(String keyType) throws Exception{
        if("string".equals(keyType)){
            return ValueTypeContants.VALUE_TYPE_STRING;
        }else if("int".equals(keyType)){
            return ValueTypeContants.VALUE_TYPE_INT;
        }else if("float".equals(keyType)){
            return ValueTypeContants.VALUE_TYPE_FLOAT;
        }else if("double".equals(keyType)){
            return ValueTypeContants.VALUE_TYPE_DOUBLE;
        }else if("long".equals(keyType)){
            return ValueTypeContants.VALUE_TYPE_LONG;
        }else if("date".equals(keyType)){
            return ValueTypeContants.VALUE_TYPE_DATE;
        }else {
            throw new CuberFileParseException();
        }
    }
}
