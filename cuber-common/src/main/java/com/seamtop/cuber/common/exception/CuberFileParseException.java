package com.seamtop.cuber.common.exception;

/**
 * Created by feng on 2015/8/8.
 * Cuber服务连接异常
 */
public class CuberFileParseException extends Exception{
    public CuberFileParseException(){
        super();
    }
    public CuberFileParseException(String msg){
        super(msg);
    }
    public CuberFileParseException(String msg,Throwable cause){
        super(msg,cause);
    }

    public CuberFileParseException(Throwable cause){
        super(cause);
    }
}
