package com.conversion.exception.handler;


import com.conversion.controllers.ConversionController;
import com.conversion.enums.ErrorCodeEnum;
import com.conversion.exception.ConversionException;
import com.conversion.model.ConversionResponse;
import com.conversion.model.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Iterator;

@ControllerAdvice(assignableTypes = {ConversionController.class})
public class ConversionControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConversionController.class);

    @ExceptionHandler(ConversionException.class)
    public final ResponseEntity<ConversionResponse> handleInvalidRoman(Exception exception, WebRequest request) {
        ServletWebRequest req = (ServletWebRequest) request;
        ConversionException conversionException = (ConversionException) exception;
        LOGGER.error("Requested URI: "+req.getRequest().getRequestURI() +" - Invalid input in the request: "+getParam(request));
        ErrorInfo apiError = new ErrorInfo(conversionException.errorCodeEnum.errorCode, conversionException.errorCodeEnum.description);
        return new ResponseEntity<>(new ConversionResponse(apiError), conversionException.errorCodeEnum.httpStatus);
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<ConversionResponse> handleAll(Exception exception, WebRequest request) {
        ServletWebRequest req = (ServletWebRequest) request;
        LOGGER.error("Requested URI: "+req.getRequest().getRequestURI() +" - Invalid input in the request: "+getParam(request));
        ErrorInfo apiError = new ErrorInfo(ErrorCodeEnum.GENERAL_ERROR.errorCode, exception.getMessage());
        return new ResponseEntity<>(new ConversionResponse(apiError), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getParam(WebRequest request){

        Iterator<String> iterator = request.getParameterNames();
        StringBuilder sb = new StringBuilder();
        while(iterator.hasNext()){
            String key = iterator.next();
            sb.append(request.getParameter(key));
        }
        return sb.toString();
    }


}
