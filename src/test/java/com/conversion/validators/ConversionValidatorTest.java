package com.conversion.validators;


import com.conversion.enums.ErrorCodeEnum;
import com.conversion.exception.ConversionException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.*;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConversionValidator.class)
public class ConversionValidatorTest {

    ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testParseInt_EmptyString(){

        PowerMockito.mockStatic(ConversionValidator.class);
        PowerMockito.doThrow(new ConversionException(ErrorCodeEnum.INPUT_EMPTY)).when(ConversionValidator.class);
        ConversionValidator.parseInt("");

        expectedException.expect(ConversionException.class);
        expectedException.expectMessage(ErrorCodeEnum.INPUT_EMPTY.description);
    }

    @Test
    public void testParseInt_InvalidCharacter() {

        PowerMockito.mockStatic(ConversionValidator.class);
        PowerMockito.doThrow(new ConversionException(ErrorCodeEnum.INVALID_INPUT)).when(ConversionValidator.class);
        ConversionValidator.parseInt("a");

        expectedException.expect(ConversionException.class);
        expectedException.expectMessage(ErrorCodeEnum.INVALID_INPUT.description);
    }

    @Test
    public void testParseInt_InputOutOfRange(){

        PowerMockito.mockStatic(ConversionValidator.class);
        PowerMockito.doThrow(new ConversionException(ErrorCodeEnum.INVALID_INPUT)).when(ConversionValidator.class);
        ConversionValidator.parseInt("");

        expectedException.expect(ConversionException.class);
        expectedException.expectMessage(ErrorCodeEnum.INVALID_INPUT.description);
    }

    @Test
    public void testParseInt_SuccessScenario(){

        PowerMockito.mockStatic(ConversionValidator.class);
        PowerMockito.when(ConversionValidator.parseInt("5")).thenReturn(5);
        Assert.assertEquals(5, ConversionValidator.parseInt("5").intValue());

    }
}
