package com.seamtop.cuber.common.metadata;


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
     * 主键信息
     */
    private RowKey rowKey;

    /**
     * 列族以及列族对应的列
     */
    private HashMap<String,Column> columnMap;

    /**
     * 表操作者对象
     */
    private HashMap<String,Integer> operatorMap;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public RowKey getRowKey() {
        return rowKey;
    }

    public void setRowKey(RowKey rowKey) {
        this.rowKey = rowKey;
    }

    public HashMap<String, Column> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(HashMap<String, Column> columnMap) {
        this.columnMap = columnMap;
    }

    public HashMap<String, Integer> getOperatorMap() {
        return operatorMap;
    }

    public void setOperatorMap(HashMap<String, Integer> operatorMap) {
        this.operatorMap = operatorMap;
    }
}
