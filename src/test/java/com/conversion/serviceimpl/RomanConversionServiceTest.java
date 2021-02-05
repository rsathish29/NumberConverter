package com.conversion.serviceimpl;

import com.conversion.service.ConversionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class RomanConversionServiceTest {

    ConversionService conversionService = new RomanConversionService();

    @Test
    public void testIntegerToRomanNumeral(){
        Assertions.assertEquals("X", conversionService.integerToRomanNumeral(10));
        Assertions.assertEquals("CVIII", conversionService.integerToRomanNumeral(108));
        Assertions.assertEquals("MLXXX", conversionService.integerToRomanNumeral(1080));
        Assertions.assertEquals("CMXC", conversionService.integerToRomanNumeral(990));
        Assertions.assertEquals("DCCCLXXVIII", conversionService.integerToRomanNumeral(878));
        Assertions.assertEquals("MMMDCCXC", conversionService.integerToRomanNumeral(3790));
    }
}
