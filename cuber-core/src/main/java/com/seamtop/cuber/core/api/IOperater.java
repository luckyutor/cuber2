package com.seamtop.cuber.core.api;

import com.seamtop.cuber.common.entriy.Result;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 */
public interface IOperater {

    public Result execute(HashMap<String,Object> paramsMap);
}
