package com.seamtop.cuber.common.util;
import com.seamtop.cuber.common.exception.CuberFileParseException;
import com.seamtop.cuber.common.metadata.Column;
import com.seamtop.cuber.common.metadata.RowKey;
import com.seamtop.cuber.common.metadata.TableMetaData;
import com.seamtop.cuber.common.metadata.ValueTypeContants;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import scala.Int;
import scala.xml.MetaData;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by feng on 2015/8/11.
 */
public class XMLParseUtil {

    public static void main(String [] args) throws Exception{
        System.out.println("Test --");
    }

    public static void loadTableSchema() throws Exception{
        String path = XMLParseUtil.class.getClassLoader().getResource("").getPath();
        String filePath = path + "schema.xml";
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File(filePath));
        // 获取根元素
        Element root = document.getRootElement();
        List<Element> tableList = root.elements();
        for(int i=0;i<tableList.size();i++){
            TableMetaData tableMetaData = new TableMetaData();
            Element e = tableList.get(i);
            //设置表名
            tableMetaData.setTableName(e.attributeValue("name"));
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

            //设置列数据
            List<Column> columnList = new ArrayList<Column>();
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
                    columnList.add(column);
                }
            }
            tableMetaData.setColumnList(columnList);
        }
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
