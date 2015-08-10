package com.seamtop.cuber.common.metadata;

/**
 * Created by feng on 2015/8/10.
 */
public class Column {

    /**
     * 列名
     */
    private String columnName;

    /**
     * 列描述
     */
    private String columnDesc;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 字段字符长度
     */
    private String columnMaxSize;

    /**
     * 是否必填
     */
    private boolean ifRequired;


    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnDesc() {
        return columnDesc;
    }

    public void setColumnDesc(String columnDesc) {
        this.columnDesc = columnDesc;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnMaxSize() {
        return columnMaxSize;
    }

    public void setColumnMaxSize(String columnMaxSize) {
        this.columnMaxSize = columnMaxSize;
    }

    public boolean isIfRequired() {
        return ifRequired;
    }

    public void setIfRequired(boolean ifRequired) {
        this.ifRequired = ifRequired;
    }
}
