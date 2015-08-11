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
    private String keyType;

    /**
     * 字段字符长度
     */
    private String keyMaxSize;

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

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getKeyMaxSize() {
        return keyMaxSize;
    }

    public void setKeyMaxSize(String keyMaxSize) {
        this.keyMaxSize = keyMaxSize;
    }
}
