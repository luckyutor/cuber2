package com.seamtop.cuber.common.entriy;

import com.seamtop.cuber.common.entriy.Message;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by feng on 2015/8/9.
 */
public class CuberTask {
    /**
     * 任务ID
     */
    private long taskId;

    /**
     * 任务来源
     */
    private int taskSource;

    /**
     * 任务时间
     */
    private long taskTime;

    /**
     * 任务数据
     */
    private ArrayList<Message> messageList;


    public CuberTask(){
        long t = Math.round(Math.random() * 100);
        while (t<10 || t >= 100){
            t = Math.round(Math.random() * 100);
        }
        Date d = new Date();
        this.setTaskId(Long.valueOf(d.getTime() + "" + t));
        this.setTaskTime(d.getTime());
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public int getTaskSource() {
        return taskSource;
    }

    public void setTaskSource(int taskSource) {
        this.taskSource = taskSource;
    }

    public long getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(long taskTime) {
        this.taskTime = taskTime;
    }

    public ArrayList<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList<Message> messageList) {
        this.messageList = messageList;
    }

    public void setMessage(Message message){
        if(this.messageList == null){
            messageList = new ArrayList<Message>();
        }
        messageList.add(message);
    }
}
