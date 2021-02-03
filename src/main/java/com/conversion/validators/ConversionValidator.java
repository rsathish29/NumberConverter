package com.conversion.validators;


import com.conversion.enums.ErrorCodeEnum;
import com.conversion.exception.ConversionException;
import org.springframework.util.StringUtils;

public class ConversionValidator {

    public static Integer parseInt(String input){
        int result;
        if(StringUtils.isEmpty(input))
            throw new ConversionException(ErrorCodeEnum.INPUT_EMPTY);
        try{
            result =  Integer.parseInt(input);
            if(result<1 || result > 3999)
                throw new ConversionException(ErrorCodeEnum.INVALID_INPUT);
        }catch (Exception e){
            throw new ConversionException(ErrorCodeEnum.INVALID_INPUT);
        }
        return result;
    }

}
