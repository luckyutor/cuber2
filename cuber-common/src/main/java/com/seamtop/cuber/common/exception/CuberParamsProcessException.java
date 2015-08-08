package com.seamtop.cuber.common.exception;

/**
 * Created by feng on 2015/8/8.
 * Cuber参数处理异常
 */
public class CuberParamsProcessException extends Exception{
    public CuberParamsProcessException(){
        super();
    }
    public CuberParamsProcessException(String msg){
        super(msg);
    }
    public CuberParamsProcessException(String msg,Throwable cause){
        super(msg,cause);
    }

    public CuberParamsProcessException(Throwable cause){
        super(cause);
    }
}
