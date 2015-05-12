package com.brayden;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * https://oj.leetcode.com/problems/pascals-triangle/
 * <p>
 * Given numRows, generate the first numRows of Pascal's triangle.
 *
 * @author brayden.zhang
 *
 */
public class PascalsTriangle extends LeetcodeCommon {

    public List<List<Integer>> generate(int numRows) {
        if(numRows < 0)
            throw new RuntimeException("index should > 1");
        List<List<Integer>> outterList = new ArrayList<List<Integer>>();

        if(numRows == 0)
        	return outterList;
        
        List<Integer> innerList = new ArrayList<>();
        innerList.add(1);
        outterList.add(innerList);
        
        List<Integer> lastList;
        for(int i = 1; i < numRows; i++){
        	lastList = outterList.get(outterList.size() - 1);
        	innerList = new ArrayList<>();
        	innerList.add(1);
            for(int j = 1; j < i; j++)
            	innerList.add(lastList.get(j - 1) + lastList.get(j));
            innerList.add(1);
            outterList.add(innerList);
        }
        return outterList;
    }

    @Test
    public void test(){
        PascalsTriangle pt = new PascalsTriangle();
        
        assertTrue(pt.generate(0).isEmpty());
        
        assertEqualBetween2DimArrayAndList(
        		new Object[][]{{1}}, 
        		pt.generate(1));
        
        assertEqualBetween2DimArrayAndList(
        		new Object[][]{{1}, {1, 1}}, 
        		pt.generate(2));
        
        assertEqualBetween2DimArrayAndList(
        		new Object[][]{{1}, {1, 1}, {1, 2, 1}}, 
        		pt.generate(3));
        
        assertEqualBetween2DimArrayAndList(
        		new Object[][]{{1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}}, 
        		pt.generate(4));
    }

}
