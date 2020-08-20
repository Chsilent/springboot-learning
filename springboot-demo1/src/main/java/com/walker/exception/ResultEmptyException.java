package com.walker.exception;

/**
 * result null empty异常
 *
 * @author Walker
 * @date 2019/2/11 下午2:27
 */
public class ResultEmptyException extends Exception{

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultEmptyException(String message){
        this.message = message;
    }

    public ResultEmptyException(){
        setMessage("result is null or empty!");
    }
}
