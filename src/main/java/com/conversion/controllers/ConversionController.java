package com.conversion.controllers;

import com.conversion.enums.ErrorCodeEnum;
import com.conversion.exception.ConversionException;
import com.conversion.model.ConversionResponse;
import com.conversion.service.ConversionService;
import com.conversion.validators.ConversionValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ConversionController - Exposes rest API endpoints for converting integer to romannumerals
 * @author - Sathish Raghu
 */
@RestController
@RequestMapping(value = "/romannumeral")
@Api(produces = MediaType.APPLICATION_JSON_VALUE, value = "Operations pertaining to Number Conversion - Integer to Roman Numeral")
public class ConversionController {

    Logger LOGGER = LoggerFactory.getLogger(ConversionController.class);

    @Autowired
    ConversionService conversionService;

    /**
     * This method exposes the endpoint using the URI - /romannumeral
     * @param input
     * @return ConversionResponse
     * @throws ConversionException
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Convert Integer to Roman Numeral", response = ConversionResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully converted integer to Roman Numeral"),
            @ApiResponse(code = 400, message = "Invalid Input data, expected input is Integer. Range 1-3999"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Something went wrong. Please try later")
    }
    )
    public ConversionResponse convertIntegerToRomanNumeral(@RequestParam(value = "query", required = false) String input) throws ConversionException {

        try {
            int number = ConversionValidator.parseInt(input);
            String result = conversionService.integerToRomanNumeral(number);
            LOGGER.info("Conversion successful : Input - {}, Output - {}", number, result);
            return new ConversionResponse(result);

        }catch (ConversionException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Unable to convert - " + e.getMessage());
            throw new ConversionException(ErrorCodeEnum.GENERAL_ERROR);
        }

    }
}
