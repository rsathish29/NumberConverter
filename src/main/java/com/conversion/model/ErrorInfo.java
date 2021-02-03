package com.conversion.model;
/**
 * This is a model object that stores error related information
 *
 * @author  Sathish Raghu
 */
public class ErrorInfo {

    private int code;
    private String description;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ErrorInfo(int code, String description){
        this.code = code;
        this.description = description;
    }
}
