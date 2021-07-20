package com.project.Objects.Entities;

public class BasicResponseModel {
    private int errorCode;
    private String message;
    private Object object;

    public BasicResponseModel(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public BasicResponseModel(Object object) {
        this.object = object;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
