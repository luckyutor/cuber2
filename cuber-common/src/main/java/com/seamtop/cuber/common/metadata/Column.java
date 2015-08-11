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
    private int columnType;

    /**
     * 字段字符长度
     */
    private int columnMaxSize;

    /**
     * 是否必填
     */
    private boolean ifRequired;

    /**
     * 列所在列族名称
     */
    private String familyName;


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


    public int getColumnMaxSize() {
        return columnMaxSize;
    }

    public void setColumnMaxSize(int columnMaxSize) {
        this.columnMaxSize = columnMaxSize;
    }

    public boolean isIfRequired() {
        return ifRequired;
    }

    public void setIfRequired(boolean ifRequired) {
        this.ifRequired = ifRequired;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }
}
