package com.seamtop.cuber.common.entriy;

/**
 * Created by feng on 2015/8/8.
 */
public class ErrorCode {

    /**
     * 返回成功
     */
    public static final int SUCCESS = 0;

    /**
     * 参数为空
     */
    public static final int PARAMS_IS_NULL = 1;

    /**
     * 参数格式错误
     */
    public static final int PARAMS_FORMAT_ERROR = 2;

    public int code;

    public String desc;



    public ErrorCode(int code, String params){
        switch (code){
            case 0:
                this.setCode(0);
                break;
            case 1:
                this.setCode(1);
                this.setDesc("参数[" + params + "]为空！");
                break;
            case 2:
                this.setCode(2);
                this.setDesc("参数[" + params + "]格式错误！");
                break;
            default:
                this.setCode(0);
                break;
        }
    }

    public String toString(){
        return this.getDesc();
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
