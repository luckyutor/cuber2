package com.seamtop.cuber.common.util;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.File;

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
        System.out.println("document:"+document);
    }
}
