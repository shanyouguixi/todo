package com.shangui.common.exception;

public class MyException extends Exception {
    private String errorMsg;
    private Integer errorCode;

    public MyException(String errorMsg,Integer errorCode){
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public String getErrorMsg(){
        return this.errorMsg;
    }

    public Integer getErrorCode(){
        return this.errorCode;
    }
}
