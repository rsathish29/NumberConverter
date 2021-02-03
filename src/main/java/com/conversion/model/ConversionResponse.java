package com.conversion.model;

/**
 * This is a model object that stores the result and error details.
 * Only either of them will be populated at any given time
 *
 * @author  Sathish Raghu
 */
public class ConversionResponse {

    private String result;
    private ErrorInfo error;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ErrorInfo getError() {
        return error;
    }

    public void setError(ErrorInfo error) {
        this.error = error;
    }

    public ConversionResponse(String result) {
        this.result = result;
    }

    public ConversionResponse(ErrorInfo error) {
        this.error = error;
    }
}
