package com.conversion.integrationtest;

import com.conversion.enums.ErrorCodeEnum;
import com.conversion.service.ConversionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ConversionControlIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ConversionService conversionService;

    @WithMockUser("user1")
    @Test
    public void testconvertIntegerToRomanNumeral_EmptyInput() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/romannumeral?query="))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString(ErrorCodeEnum.INPUT_EMPTY.description)));
    }

    @WithMockUser("user2")
    @Test
    public void testconvertIntegerToRomanNumeral_InvalidInput() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/romannumeral?query=asd"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString(ErrorCodeEnum.INVALID_INPUT.description)));
    }

    @WithMockUser("user3")
    @Test
    public void testconvertIntegerToRomanNumeral_InputOutOfRange() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/romannumeral?query=190293"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString(ErrorCodeEnum.INVALID_INPUT.description)));
    }

    @WithMockUser("user4")
    @Test
    public void testconvertIntegerToRomanNumeral_Success() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/romannumeral?query=990"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("CMXC")));
    }

}
