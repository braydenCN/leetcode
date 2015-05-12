package com.brayden;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * https://oj.leetcode.com/problems/pascals-triangle-ii/
 * <p>
 * Given an index k, return the kth row of the Pascal's triangle.
 * <p>
 * use only O(k) extra space
 * 
 * @author brayden.zhang
 *
 */
public class PascalsTriangleII {
	
    public List<Integer> getRow(int rowIndex) {
    	List<Integer> list = new ArrayList<>();
    	for(int i = 0; i < rowIndex + 1; i++)
    		list.add(0);
        list.set(0, 1);
        for(int i = 1; i <= rowIndex; i++)
        	for(int j = i; j >= 1; j--)
        		list.set(j, list.get(j) + list.get(j - 1));
        return list;
    }
    
    @Test
    public void test(){
    	PascalsTriangleII pt = new PascalsTriangleII();
    	Object[] arr = {1, 3, 3, 1};
    	assertArrayEquals(arr, pt.getRow(3).toArray());
    	arr = new Object[]{1, 1};
    	assertArrayEquals(arr, pt.getRow(1).toArray());
    	arr = new Object[]{1, 2, 1};
    	assertArrayEquals(arr, pt.getRow(2).toArray());
    	arr = new Object[]{1, 5, 10, 10, 5, 1};
    	assertArrayEquals(arr, pt.getRow(5).toArray());   	
    }
    /**
     * To improve, using List.add(int, E);
     */
}
