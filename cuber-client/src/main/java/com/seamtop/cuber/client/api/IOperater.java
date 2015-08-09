package com.seamtop.cuber.client.api;

import java.util.HashMap;

/**
 * Created by feng on 2015/8/8.
 */
public interface IOperater {

    public void execute(int apiType,HashMap<String,String> paramsMap) throws Exception;
}
