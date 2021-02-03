package com.conversion.controllers;

import com.conversion.enums.ErrorCodeEnum;
import com.conversion.exception.ConversionException;
import com.conversion.model.ConversionResponse;
import com.conversion.service.ConversionService;
import com.conversion.validators.ConversionValidator;
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
    public ConversionResponse convertIntegerToRomanNumeral(@RequestParam(value = "query", required = false) String input) throws ConversionException {

        try {
            int number = ConversionValidator.parseInt(input);
            String result = conversionService.integerToRomanNumeral(number);
            LOGGER.info("Conversion successful : Input - {}, Output - {}", number, result);
            return new ConversionResponse(result);

        }catch (ConversionException e) {
            throw e;
        } catch (Exception e) {
            throw new ConversionException(ErrorCodeEnum.GENERAL_ERROR);
        }

    }
}
