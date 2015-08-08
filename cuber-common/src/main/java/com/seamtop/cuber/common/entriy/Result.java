package com.seamtop.cuber.common.entriy;

/**
 * Created by feng on 2015/8/8.
 */
public class Result {

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

    public int resultCode;

    public String resultDesc;

    public String resultData;

    public Result(){}

    public Result(int code){
        switch (code){
            case 0:
                this.setResultCode(0);
                break;
            default:
                this.setResultCode(0);
                break;
        }
    }

    public Result(int code, String params){
        switch (code){
            case 0:
                this.setResultCode(0);
                break;
            case 1:
                this.setResultCode(1);
                this.setResultDesc("参数[" + params + "]为空！");
                break;
            case 2:
                this.setResultCode(2);
                this.setResultDesc("参数[" + params + "]格式错误！");
                break;
            default:
                this.setResultCode(0);
                break;
        }
    }

    public boolean isSuccess(){
        if(this.getResultCode() == 0){
            return true;
        }else{
            return false;
        }
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }
}
