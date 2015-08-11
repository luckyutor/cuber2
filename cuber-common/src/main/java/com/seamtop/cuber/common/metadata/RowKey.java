package com.seamtop.cuber.common.metadata;

/**
 * Created by zongjunfeng on 2015/8/11.
 */
public class RowKey {

    /**
     * 列名
     */
    private String keyName;

    /**
     * 列描述
     */
    private String keyDesc;

    /**
     * 字段类型
     */
    private int keyType;

    /**
     * 字段字符长度
     */
    private int keyMaxSize;

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyDesc() {
        return keyDesc;
    }

    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
    }

    public int getKeyType() {
        return keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }

    public int getKeyMaxSize() {
        return keyMaxSize;
    }

    public void setKeyMaxSize(int keyMaxSize) {
        this.keyMaxSize = keyMaxSize;
    }
}
