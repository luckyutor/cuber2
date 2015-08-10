package com.seamtop.cuber.common.metadata;

import joptsimple.internal.Column;

import java.util.HashMap;
import java.util.List;

/**
 * Created by feng on 2015/8/10.
 */
public class TableMetaData {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 列族以及列族对应的列
     */
    private HashMap<String,List<Column>> tableMetaData;

    /**
     * 表操作者对象
     */
    private HashMap<String,Integer> operaterMap;
}
