package com.meyasy.exception;

public class ServiceException extends RuntimeException{

    private String errorCode;

    private String errorMessage;

    /**
     * 构造函数
     */
    public ServiceException() {
        super();
    }

    /**
     * 构造函数
     * @param errorCode
     */
    public ServiceException(String errorCode) {
        super(errorCode);
    }

    /**
     * 构造函数
     * @param cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造函数
     * @param errorCode
     * @param cause
     */
    public ServiceException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    /**
     * 构造函数
     * @param errorCode
     * @param message
     */
    public ServiceException(String errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    /**
     * 构造函数
     * @param errorCode
     * @param message
     * @param cause
     */
    public ServiceException(String errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
