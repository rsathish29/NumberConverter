package com.conversion.serviceimpl;

import com.conversion.service.ConversionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the implementation class for ConversionService that has the concrete logic to do the conversion
 * @author  Sathish Raghu
 */
@Service
public class RomanConversionService implements ConversionService {

    static final int[] numbers = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
    static final String[] romanNumerals = new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    /**
     *
     * Algorithm
     *
     * 1. We store the 14 possible roman numerals and its corresponding integers in descending fashion in 2 different arrays
     * 2. Iterate through element in the numbers array from first to last
     *      - If the given number < element, move on to the next element in the array.
     *      - Else, we get the roman numeral for the given index from the romanNumerals[] and store in the result, also subtract the number with the element
     *
     * Time Complexity - O(1)
     * Space Complexity - O(1)
     *
     * @param num - Input integer
     * @return - Roman Numeral that is equivalent to the given input number
     */
    @Override
    public String integerToRomanNumeral(Integer num) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < numbers.length;){
            if (num >= numbers[i]) {
                result.append(romanNumerals[i]);
                num -= numbers[i];
            } else {
                i++;
            }
        }
        return result.toString();
    }
}
