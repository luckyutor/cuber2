package com.seamtop.cuber.common.util;
import com.seamtop.cuber.common.exception.CuberFileParseException;
import com.seamtop.cuber.common.metadata.RowKey;
import com.seamtop.cuber.common.metadata.TableMetaData;
import com.seamtop.cuber.common.metadata.ValueTypeContants;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import scala.Int;
import scala.xml.MetaData;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by feng on 2015/8/11.
 */
public class XMLParseUtil {

    public static void main(String [] args) throws Exception{
        System.out.println("Test --");
        parseTableSchema();
    }

    public static void parseTableSchema() throws Exception{
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
