package com.brayden;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s == null && t == null)
            return true;
        if(s == null || t == null || s.length() != t.length())
            return false;
        int[] arr = new int[26];
        for(char c: s.toCharArray())
            arr[c - 'a']++;
        for(char c: t.toCharArray())
            arr[c - 'a']--;
        for(int i: arr)
            if(i != 0)
                return false;
        return true;
    }
}
