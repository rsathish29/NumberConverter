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

    final static Map<Integer, String> romanNumerals;

    static {
        romanNumerals = new HashMap<>();
        populateRomanNumerals();
    }

    /**
     * This method computes the romanNumeral for the given input based on the following logic
     *      - Reduces the input number by dividing by 1000, 500, 100, 50, 10, 5 & 1 in specific order
     *      - In case of special numbers like [4,9,40,90,400,900], we directly look it up in the hashMap
     *      - In all other cases, we compute the romanNumeral using the helperMethod
     *      - This logic is repeated until the input becomes zero and the string buffer gets popuplated.
     * @param n - Input integer
     * @return - Roman Numeral that is equivalent to the given input number
     */
    @Override
    public String integerToRomanNumeral(Integer n) {

        StringBuffer result = new StringBuffer();
        while(n>0){
            if(n/1000 > 0){
                n = getRomanNumeral(n, 1000, result);
            }else if(n/100 > 0 && (n/100 == 4 || n/100 == 9)){
                result.append(romanNumerals.get(100*(n/100)));
                n = n%100;
            }else if(n/500 > 0){
                n = getRomanNumeral(n, 500, result);
            }else if(n/100 > 0){
                n = getRomanNumeral(n, 100, result);
            }else if(n/10 > 0 && (n/10 == 4 || n/10 == 9)){
                result.append(romanNumerals.get(10*(n/10)));
                n = n%10;
            }else if(n/50 > 0){
                n = getRomanNumeral(n, 50, result);
            }else if(n/10 > 0){
                n = getRomanNumeral(n, 10, result);
            }else if(n==4 || n==9){
                result.append(romanNumerals.get(n));
                n = 0;
            }else if(n/5> 0){
                n = getRomanNumeral(n, 5, result);
            }else{
                n = getRomanNumeral(n, 1, result);
            }
        }
        return result.toString();
    }

    /**
     *
     * This method computes the romanNumeral for the given number and its symbol value
     *
     * Example : number = 32,symbolValue = 10, XXX gets appended to buffer and return 2 since 32%10 = 2
     *
     * @param number - Will only get one of the following values [1,2,3,6,7,8]
     * @param symbolValue - Will only get one of the following values [1,5,10,50,100,500,1000]
     * @param sb - Stringbuffer in which the computed romanNumeral gets appended to
     * @return - Remainder of the number/symbolValue
     */
    private int getRomanNumeral(int number , int symbolValue, StringBuffer sb){

        String c = romanNumerals.get(symbolValue);
        for(int i=0;i<number/symbolValue;i++){
            sb.append(c);
        }
        return number%symbolValue;
    }

    /**
     * Helper method to populate the default numbers and its romanNumerals
     */
    private static void populateRomanNumerals(){
        romanNumerals.put(1, "I");
        romanNumerals.put(5, "V");
        romanNumerals.put(10, "X");
        romanNumerals.put(50, "L");
        romanNumerals.put(100, "C");
        romanNumerals.put(500, "D");
        romanNumerals.put(1000, "M");

        romanNumerals.put(4, "IV");
        romanNumerals.put(9, "IX");
        romanNumerals.put(40, "XL");
        romanNumerals.put(90, "XC");
        romanNumerals.put(400, "CD");
        romanNumerals.put(900, "CM");
    }
}
