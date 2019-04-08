package com.walker.exception;

/**
 * null empty异常
 *
 * @author Walker
 * @date 2019/2/11 下午2:27
 */
public class NullOrEmptyException extends Exception{

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NullOrEmptyException(String message){
        this.message = message;
    }

    public NullOrEmptyException(){
        setMessage("Parameter is null or empty!");
    }
}
