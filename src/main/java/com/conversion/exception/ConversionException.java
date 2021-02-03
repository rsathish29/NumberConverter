package com.conversion.exception;

import com.conversion.enums.ErrorCodeEnum;
/**
 * This is a custom Exception that get's created and thrown at various places in the application
 * @author - Sathish Raghu
 */
public class ConversionException extends RuntimeException {
    public ErrorCodeEnum errorCodeEnum;
    public ConversionException(ErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }
}
