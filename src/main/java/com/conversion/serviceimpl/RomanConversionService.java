package com.conversion.serviceimpl;

import com.conversion.service.ConversionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RomanConversionService implements ConversionService {

    final static Map<Integer, String> romanNumerals;

    static {
        romanNumerals = new HashMap<>();
        populateRomanNumerals();
    }

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


    private int getRomanNumeral(int a , int b, StringBuffer sb){

        String c = romanNumerals.get(b);
        for(int i=0;i<a/b;i++){
            sb.append(c);
        }
        return a%b;
    }

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
