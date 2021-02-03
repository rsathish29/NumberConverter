package com.conversion.enums;

import org.springframework.http.HttpStatus;
/**
 * This Enum class holds all the error scenarios that includes a errorcode, errormessage & HTTP Status code
 * @author - Sathish Raghu
 */
public enum ErrorCodeEnum {

    INPUT_EMPTY(100, "Input cannot be empty", HttpStatus.BAD_REQUEST),
    INVALID_INPUT(101, "Invalid Input data, expected input is Integer. Range 1-3999", HttpStatus.BAD_REQUEST),
    GENERAL_ERROR(102, "Something went wrong. Please try later", HttpStatus.INTERNAL_SERVER_ERROR);

    public final int errorCode;
    public final String description;
    public final HttpStatus httpStatus;

    ErrorCodeEnum(int code, String description,HttpStatus httpStatus){
        this.errorCode = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }

}
