package com.seamtop.cuber.common.base;

import com.seamtop.cuber.common.metadata.TableMetaData;
import com.seamtop.cuber.common.tableoperator.TableOperatorBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zongjunfeng on 2015/8/11.
 */
public enum DataObject{

    INSTANCE;

    public final static HashMap<String,TableMetaData> metaDataMap = SysInit.loadTableSchema();


    public final static HashMap<String,TableOperatorBean> operatorDataMap = SysInit.loadTableOperator();
}
