package com.youthen.lj.app.bean;

import java.io.Serializable;

public class Result implements Serializable {

    /**
     * 失败
     */
    public static int FAIL = 0;

    /**
     * 成功
     */
    public static int SUCCESS = 1;

    /**
     * 未登录
     */
    public static int NO_LOGIN = 2;

    /**
     * 多账户登录
     */
    public static int MULT_LOGIN = 3;

    public static int Exception = -1;
    private static final long serialVersionUID = 1L;

    public String message;

    public int messageCode;

    public Object resultObject;

    public Result()
    {
        this.messageCode = SUCCESS;
    }

    public Object getResultObject() {
        return this.resultObject;
    }

    public void setResultObject(final Object resultObject) {
        this.resultObject = resultObject;
    }

    /**
     * getter for messageCode.
     * 
     * @return messageCode
     */
    public int getMessageCode() {
        return this.messageCode;
    }

    /**
     * setter for messageCode.
     * 
     * @param aMessageCode messageCode
     */
    public void setMessageCode(final int aMessageCode) {
        this.messageCode = aMessageCode;
    }

    /**
     * getter for message.
     * 
     * @return message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * setter for message.
     * 
     * @param aMessage message
     */
    public void setMessage(final String aMessage) {
        this.message = aMessage;
    }

}
