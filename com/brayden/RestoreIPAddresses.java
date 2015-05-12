package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class RestoreIPAddresses extends LeetcodeCommon {
    
    public List<String> restoreIpAddresses1(String s) {
        if(s == null || s.length() < 4 || s.matches("^.*\\D.*$"))
            return Collections.emptyList();
                    
        int strLen = s.length();
        
        List<List<List<String>>> cache = new ArrayList<>();
        cache.add(new ArrayList<List<String>>());
        for(int i = 0; i < 3; i++){
            cache.get(0).add(new ArrayList<String>());
            String strToAdd = s.substring(0, i + 1);
            if(valid(strToAdd))
                cache.get(0).get(i).add(strToAdd);
        }
        
        for(int j = 1; j < 4; j++){
            List<List<String>> partList = new ArrayList<>();
            cache.add(partList);
            for(int i = j; i < (j + 1) * 3; i++){
                /** for the i-prefix of the string, j parts of IP addr **/
                List<String> list = new ArrayList<>();
                partList.add(list);
                if(digitNumValid(i, j)){
                    /** the new i-th digit added as a new part of IP **/
                    if(digitNumValid(i - 1, j - 1))
                        for(String str: cache.get(j - 1).get(i - 1))
                            if(valid(s.substring(i, i + 1)))
                                list.add(str + "." + s.substring(i, i + 1));
                    
                    if(digitNumValid(i - 2, j - 1))
                        for(String str: cache.get(j - 1).get(i - 2))
                            if(valid(s.substring(i - 1, i + 1)))
                                list.add(str + "." + s.substring(i - 1, i + 1));
                    
                    if(digitNumValid(i - 3, j - 1))
                        for(String str: cache.get(j - 1).get(i - 3))
                            if(valid(s.substring(i - 2, i + 1)))
                                list.add(str + "." + s.substring(i - 2, i + 1));
                }
            }
        }
        return cache.get(strLen - 1).get(3);
    }
    
    public List<String> restoreIpAddresses(String s) {
        if(s == null || s.length() < 4 || s.length() > 12 || s.matches("^.*\\D.*$"))
            return Collections.emptyList();
                    
        int strLen = s.length();
        
        @SuppressWarnings("unchecked")
        List<String>[][] cache = new List[4][strLen];
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < strLen; j++)
                cache[i][j] = new ArrayList<String>();
        
        for(int i = 0; i < 3; i++){
            String strToAdd = s.substring(0, i + 1);
            if(valid(strToAdd))
                cache[0][i].add(strToAdd);
        }
        
        for(int j = 1; j < 4; j++)
            for(int i = j; i < (j + 1) * 3 && i < strLen; i++)
                for(int k = 0; k < 3; k++){
                    int lastDigitIndex = i - k - 1;
                    if(digitNumValid(lastDigitIndex, j - 1))
                        for(String str: cache[j - 1][lastDigitIndex]){
                            String strToAdd = s.substring(i - k, i + 1);
                            if(valid(strToAdd))
                                cache[j][i].add(str + "." + strToAdd);
                        }
                }
        
        return cache[3][strLen - 1];
    }
    
    private boolean valid(String s) {
        if(s.equals("0"))
            return true;
        if(s.startsWith("0"))
            return false;
        int num = Integer.valueOf(s);
        return num >= 0 && num <= 255;
    }

    private boolean digitNumValid(int indexOfDigits, int indexOfPart){
        int digitNum = indexOfDigits + 1;
        int partNum = indexOfPart + 1;
        return digitNum >= partNum && digitNum <= partNum * 3 && 
                partNum >= 1 && partNum <= 4;
    }
    
    @Test
    public void test(){
        assertListEquals(Arrays.asList(new String[]{"255.255.11.135", 
                                                    "255.255.111.35"}),
                restoreIpAddresses("25525511135"));
    }
    
    @Test
    public void test2(){
        /**

Submission Result: Wrong Answer
Input:  "010010"
Output:     ["010.0.1.0","01.00.1.0","0.100.1.0","01.0.01.0","0.10.01.0","0.1.001.0","01.0.0.10","0.10.0.10","0.1.00.10","0.1.0.010"]
Expected:   ["0.10.0.10","0.100.1.0"]
         */
        
        assertListEquals(Arrays.asList(new String[]{"0.10.0.10", 
                                                    "0.100.1.0"}),
                restoreIpAddresses("010010"));
    }
}
