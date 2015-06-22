package com.brayden.other;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 将一个字符串通过插入字符串或者删除字符串的方式转换为另一个给定的字符串。删除连续n个字符的操作的代价为2，插入n个字符的代价为n+2。求转换的最小代价。
int getmincost(char *s1, char *s2)
例子:
aaabc=>bcd所需要代价为删除aaa 2，添加d 3
abc=>acd 删除b，添加d
 * @author pengczha
 *
 */
public class MinEditCost {
    public int getMinCost(String s1, String s2){
        if(s1 == null || s2 == null)
            return -1;
        int len1 = s1.length(), len2 = s2.length();
        if(s1.isEmpty())
            return s2.isEmpty() ? 0 : len2 + 2;
        if(s2.isEmpty())
            return 2;
        
        int add[][] = new int[len1 + 1][len2 + 1];
        int delete[][] = new int[len1 + 1][len2 + 1];
        add[0][0] = Integer.MAX_VALUE - 10;
        delete[0][0] = 2;
        for(int i = 1; i <= len1; i++){
            delete[i][0] = 2;
            add[i][0] = Integer.MAX_VALUE - 10;
        }
        for(int i = 1; i <= len2; i++){
            add[0][i] = i + 2;
            delete[0][i] = Integer.MAX_VALUE - 10;
        }
        
        for(int i = 1; i <= len1; i++)
            for(int j = 1; j <= len2; j++){
                add[i][j] = Math.min(add[i - 1][j - 1] + 1, 
                        delete[i - 1][j - 1] + 3);
                delete[i][j] = Math.min(delete[i - 1][j], add[i - 1][j] + 2);
            }
        return Math.min(add[len1][len2], delete[len1][len2]);
    }
    
    @Test
    public void test(){
        assertEquals(5, getMinCost("aaabc", "bcd"));
    }
}
