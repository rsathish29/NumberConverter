package com.conversion.controllers;

import com.conversion.enums.ErrorCodeEnum;
import com.conversion.exception.ConversionException;
import com.conversion.service.ConversionService;
import com.conversion.validators.ConversionValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;


@WebMvcTest(ConversionController.class)
public class ConversionControllerTest {

    @MockBean
    ConversionService conversionService;

    @MockBean
    ConversionValidator conversionValidator;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testConvertIntegerToRomanNumeral_Success() throws Exception{
        Mockito.when(conversionValidator.parseInt("5")).thenReturn(5);
        Mockito.when(conversionService.integerToRomanNumeral(5)).thenReturn("V");
        mockMvc.perform(MockMvcRequestBuilders
                .get("/romannumeral?query=5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("V")));
    }

    @Test
    public void testConvertIntegerToRomanNumeral_Failure() throws Exception{
        Mockito.when(conversionValidator.parseInt("a")).thenThrow(new ConversionException(ErrorCodeEnum.INVALID_INPUT));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/romannumeral?query=a"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("Invalid Input data")));
    }

}
