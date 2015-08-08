package com.seamtop.cuber.core.api.car;

import com.seamtop.cuber.common.entriy.ErrorCode;
import com.seamtop.cuber.core.api.IOperater;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 */
public class CarIndexDeleteOperater implements IOperater {
    public ErrorCode execute(HashMap<String,Object> paramsMap){
        System.out.println("增加车源方法--"+paramsMap);
        return null;
    }
}
