package com.brayden.other;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FormTheSameBST {

    public boolean formTheSameBST(List<Integer> list1, List<Integer> list2){
        if(list1.size() == 0)
            return list2.size() == 0;
        if(list2.size() == 0)
            return list1.size() == 0;
        if(list1.size() != list2.size() || list1.get(0) != list2.get(0))
            return false;
        
        Integer rootVal = list1.get(0);
        List<Integer> leftList1 = new ArrayList<>();
        for(int i = 1; i < list1.size(); i++)
            if(list1.get(i) <= rootVal)
                leftList1.add(list1.get(i));
        List<Integer> leftList2 = new ArrayList<>();
        for(int i = 1; i < list2.size(); i++)
            if(list2.get(i) <= rootVal)
                leftList2.add(list2.get(i));
        if(!formTheSameBST(leftList1, leftList2))
            return false;

        List<Integer> rightList1 = new ArrayList<>();
        for(Integer i: list1)
            if(i > rootVal)
                rightList1.add(i);
        List<Integer> rightList2 = new ArrayList<>();
        for(Integer i: list2)
            if(i > rootVal)
                rightList2.add(i);
        return formTheSameBST(rightList1, rightList2);
    }
    
    public boolean formTheSameBST1(int[] a, int[] b){
        return formTheSameBSTInt(a, b, Integer.MIN_VALUE, Integer.MAX_VALUE, 
                0, 0, a.length);
    }
    
    private boolean formTheSameBSTInt(int[] a, int[] b, int min, int max, 
            int aInd, int bInd, int len){
        if(aInd == len)
            return bInd == len;
        if(bInd == len)
            return aInd == len;
        if(a[aInd] != b[bInd])
            return false;
        int pivot = a[aInd];
        
        int aIndNew, bIndNew;
        for(aIndNew = aInd + 1; aIndNew < len; aIndNew++)
            if(a[aIndNew] > min && a[aIndNew] <= pivot)
                break;
        for(bIndNew = bInd + 1; bIndNew < len; bIndNew++)
            if(b[bIndNew] > min && b[bIndNew] <= pivot)
                break;
        
        if(formTheSameBSTInt(a, b, min, pivot, aIndNew, bIndNew, len) == false)
            return false;
        
        for(aIndNew = aInd; aIndNew < len; aIndNew++)
            if(a[aIndNew] <= max && a[aIndNew] > pivot)
                break;
        for(bIndNew = bInd; bIndNew < len; bIndNew++)
            if(b[bIndNew] <= max && b[bIndNew] > pivot)
                break;
        
        return formTheSameBSTInt(a, b, pivot, max, aIndNew, bIndNew, len);
    }
    
    @Test
    public void test(){
        assertTrue(formTheSameBST1(new int[]{2,1,3}, new int[]{2,3,1}));
    }
    
    @Test
    public void test1(){
        assertTrue(formTheSameBST(Arrays.asList(2,1,3), Arrays.asList(2,3,1)));
    }
}
