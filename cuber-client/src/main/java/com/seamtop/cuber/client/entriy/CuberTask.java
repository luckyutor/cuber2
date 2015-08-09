package com.seamtop.cuber.client.entriy;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by feng on 2015/8/9.
 */
public class CuberTask {
    /**
     * 任务ID
     */
    private long taskId;

    /**
     * 任务类型
     */
    private int taskType;

    /**
     * 任务数据
     */
    private HashMap<String,String> taskData;

    public CuberTask(){
        long t = Math.round(Math.random() * 100);
        while (t<10 || t >= 100){
            t = Math.round(Math.random() * 100);
        }
        Date d = new Date();
        this.setTaskId(Long.valueOf(d.getTime() + "" + t));
        this.setTaskTime(d.getTime());
    }

    /**
     * 任务时间
     */
    private long taskTime;

    private long getTaskId() {
        return taskId;
    }

    private void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public HashMap<String, String> getTaskData() {
        return taskData;
    }

    public void setTaskData(HashMap<String, String> taskData) {
        this.taskData = taskData;
    }

    private long getTaskTime() {
        return taskTime;
    }

    private void setTaskTime(long taskTime) {
        this.taskTime = taskTime;
    }
}
