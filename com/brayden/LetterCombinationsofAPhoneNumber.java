package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsofAPhoneNumber {
    
    private final static Map<Character, char[]> digitToLettersMap = 
            new HashMap<>();
    static{
        digitToLettersMap.put('2', new char[]{'a', 'b', 'c'});
        digitToLettersMap.put('3', new char[]{'d', 'e', 'f'});
        digitToLettersMap.put('4', new char[]{'g', 'h', 'i'});
        digitToLettersMap.put('5', new char[]{'j', 'k', 'l'});
        digitToLettersMap.put('6', new char[]{'m', 'n', 'o'});
        digitToLettersMap.put('7', new char[]{'p', 'q', 'r', 's'});
        digitToLettersMap.put('8', new char[]{'t', 'u', 'v'});
        digitToLettersMap.put('9', new char[]{'w', 'x', 'y', 'z'});
    }        
    
    private List<String> letterCombinationsInt(String digits) {
        List<String> resultList = new ArrayList<>();
        char digit = digits.charAt(0);
        if(digits.length() == 1)
            for(char c: digitToLettersMap.get(digit))
                resultList.add(String.valueOf(c));
        else
            for(char c: digitToLettersMap.get(digit))
                for(String str: letterCombinationsInt(digits.substring(1)))
                    resultList.add(c + str);
        
        return resultList;
    }
    
    private List<String> letterCombinationsInt1(String digits) {
        if(digits.length() == 0)
            return Arrays.asList("");
        
        List<String> resultList = new ArrayList<>();
        for(char c: digitToLettersMap.get(digits.charAt(0)))
                for(String str: letterCombinationsInt1(digits.substring(1)))
                    resultList.add(c + str);
        
        return resultList;
    }
    
    public List<String> letterCombinations1(String digits) {
        if(digits == null || digits.isEmpty() || 
                digits.contains("0") || digits.contains("1"))
            return Arrays.asList(new String[]{""});
        
        return letterCombinationsInt1(digits);
    }
}
