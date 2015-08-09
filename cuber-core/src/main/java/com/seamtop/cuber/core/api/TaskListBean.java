package com.seamtop.cuber.core.api;

import com.seamtop.cuber.core.CuberTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by feng on 2015/8/8.
 */
public class TaskListBean {

    public static List<HashMap<String,String>> taskList = new ArrayList<HashMap<String,String>>();

    public synchronized static void addTask(CuberTask task){

    }
}
