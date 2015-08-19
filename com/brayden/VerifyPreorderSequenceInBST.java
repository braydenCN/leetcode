package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

/*
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
 * You may assume each number in the sequence is unique.

 * Follow up:
 * Could you do it using only constant space complexity?
 */
public class VerifyPreorderSequenceInBST {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0)
            return true;
        return verify1(preorder, 0, preorder.length - 1, Integer.MIN_VALUE, 
                Integer.MAX_VALUE);
    }

    private boolean verify(int[] p, int l, int r, int min, int max) {
        if (l > r)
            return true;
        if (l == r)
            return p[l] >= min && p[l] <= max;
        int i = l + 1;
        for (; i <= r && p[i] < p[l]; i++);
        return verify(p, l + 1, i - 1, min, p[l]) && 
                verify(p, i, r, p[l], max);
    }
    
    private boolean verify1(int[] p, int l, int r, int min, int max) {
        if (l > r)
            return true;
        if (p[l] < min || p[l] > max)
            return false;
        if (l == r)
            return true;
        int low = l + 1, high = r + 1;
        while (low < high) {
            int mid = ((high - low) >> 1) + low;
            if (p[mid] < p[l])
                low = mid + 1;
            else
                high = mid;
        }
        return verify1(p, l + 1, high - 1, min, p[l]) && 
                verify1(p, high, r, p[l], max);
    }
    
    @Test
    public void test(){
        assertTrue(verifyPreorder(new int[]{1}));
        assertTrue(verifyPreorder(new int[]{1, 2}));
        assertTrue(verifyPreorder(new int[]{1, 2, 3}));
        assertTrue(verifyPreorder(new int[]{1, 3, 2}));

        assertTrue(verifyPreorder(new int[]{2, 1}));
        assertTrue(verifyPreorder(new int[]{2, 1, 3}));
        assertTrue(verifyPreorder(new int[]{3, 2, 1}));
        assertTrue(verifyPreorder(new int[]{3, 1, 2}));
        assertTrue(verifyPreorder(new int[]{3, 2, 1, 4}));
    }
    
    @Test
    public void test1(){
        assertFalse(verifyPreorder(new int[]{2, 3, 1}));
        assertFalse(verifyPreorder(new int[]{4, 2, 3, 1}));
    }
}
