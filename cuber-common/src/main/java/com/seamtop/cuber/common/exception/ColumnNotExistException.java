package com.seamtop.cuber.common.exception;

/**
 * Created by feng on 2015/8/11.
 */
public class ColumnNotExistException extends Exception{

    public ColumnNotExistException(){
        super();
    }
    public ColumnNotExistException(String msg){
        super(msg);
    }
    public ColumnNotExistException(String msg,Throwable cause){
        super(msg,cause);
    }

    public ColumnNotExistException(Throwable cause){
        super(cause);
    }
}
