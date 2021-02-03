package com.conversion.exception;

import com.conversion.enums.ErrorCodeEnum;

public class ConversionException extends RuntimeException {
    public ErrorCodeEnum errorCodeEnum;
    public ConversionException(ErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }
}
