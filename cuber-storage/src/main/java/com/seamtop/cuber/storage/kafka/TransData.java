package com.seamtop.cuber.storage.kafka;

import org.apache.hadoop.hbase.client.Put;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zongjunfeng on 2015/8/12.
 */
public class TransData implements Serializable {

    private static final long serialVersionUID = -2381284536384630295L;

    private String rowKey;

    private String familyName;

    private String columnName;

    private String value;

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
