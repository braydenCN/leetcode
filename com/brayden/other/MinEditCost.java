package com.brayden.other;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * ��һ���ַ���ͨ�������ַ�������ɾ���ַ����ķ�ʽת��Ϊ��һ���������ַ�����ɾ������n���ַ��Ĳ����Ĵ���Ϊ2������n���ַ��Ĵ���Ϊn+2����ת������С���ۡ�
int getmincost(char *s1, char *s2)
����:
aaabc=>bcd����Ҫ����Ϊɾ��aaa 2�����d 3
abc=>acd ɾ��b�����d
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
