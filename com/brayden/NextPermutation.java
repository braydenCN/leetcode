package com.brayden;

import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;

public class NextPermutation extends LeetcodeCommon {
    public void nextPermutation(int[] num) {
        if(num == null || num.length <= 1)
            return;
        
        int len = num.length;
        for(int i = len - 2; i >= 0; i--)
            if(num[i] < num[i + 1]){
                int j = i + 1;
                for(;j < len && num[j] > num[i]; j++);
                swap(num, i, --j);
                reverse(num, i + 1, len - 1);
                return;
            }
       
        reverse(num, 0, len - 1);
    }

    private void doPermutateAt(int[] num, int i) {
        int len = num.length;
        Arrays.sort(num, i + 1, len);
        
        /** find index, num[index - 1] <= num[i] < num[index] **/
        int low = i + 1, high = len - 1, mid = -1;
        while(low <= high){
            mid = ((high - low) >> 1) + low;
            if(num[mid] <= num[i])
                low = mid + 1;
            else if(num[mid] > num[i] && 
                    (mid == i + 1 || num[mid - 1] <= num[i]))
                break;
            else 
                high = mid - 1;
        }
        
        /**
         * the b-search above has a problem: when mid returned as len-1, you
         * will never know if num[mid] satisfied or no item satisfied. However,
         * we have no problem here because we are certain we always get what we
         * need. This is guaranteed by hasNextPermutation()
         */
        swap(num, i, mid);
    }

    private boolean hasNextPermutation(int[] num, int i) {
        for(int j = num.length - 1; j > i; j--)
            if(num[j] > num[i])
                return true;
        return false;
    }
    
    @Test
    public void test(){
        int[] arr = new int[]{1, 2, 3};
        nextPermutation(arr);
        assertArrayEquals(new int[]{1, 3, 2}, arr);
        nextPermutation(arr);
        assertArrayEquals(new int[]{2, 1, 3}, arr);
        nextPermutation(arr);
        assertArrayEquals(new int[]{2, 3, 1}, arr);
        arr = new int[]{3, 2, 1};
        nextPermutation(arr);
        assertArrayEquals(new int[]{1, 2, 3}, arr);
        arr = new int[]{1, 1, 5};
        nextPermutation(arr);
        assertArrayEquals(new int[]{1, 5, 1}, arr);
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Wrong Answer
Input:      [2,3,1]
Output:     [1,3,2]
Expected:   [3,1,2]
         */
        int[] arr = new int[]{2, 3, 1};
        nextPermutation(arr);
        assertArrayEquals(new int[]{3, 1, 2}, arr);
    }
   
    @Test
    public void test2(){
    /**
Submission Result: Wrong Answer
Input:      [2,2,0,4,3,1]
Output:     [2,2,3,1,0,4]
Expected:   [2,2,1,0,3,4]
     */
        // a bug. stupid coding
        int[] arr = new int[]{2,2,0,4,3,1};
        nextPermutation(arr);
        assertArrayEquals(new int[]{2,2,1,0,3,4}, arr);
    }
    
    public void method1(int[] num, int i){
        /** 
         * Given a sorted int array and index i;
         * num.length >= 2; 0 <= i < num.length - 1
         * Find index, index > i, and num[index - 1] <= num[i] < num[index],
         * swap num[i] and num[index].
         * If no such index found, insert num[i] onto the end of the array.
         */
    }
}
