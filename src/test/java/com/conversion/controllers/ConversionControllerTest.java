package com.conversion.controllers;

import com.conversion.service.ConversionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConversionController.class)
public class ConversionControllerTest {

    @MockBean
    ConversionService conversionService;

    @Autowired
    MockMvc mockMvc;

    @WithMockUser("user1")
    @Test
    public void testConvertIntegerToRomanNumeral_Success() throws Exception{

        Mockito.when(conversionService.integerToRomanNumeral(5)).thenReturn("V");

        mockMvc.perform(MockMvcRequestBuilders
                .get("/romannumeral?query=5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("V")));
    }

    @WithMockUser("user2")
    @Test
    public void testConvertIntegerToRomanNumeral_Failure() throws Exception{


        mockMvc.perform(MockMvcRequestBuilders
                .get("/romannumeral?query=a"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("Invalid Input data")));
    }

}
