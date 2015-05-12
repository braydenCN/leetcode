package com.brayden;

public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] A) {
        if(A == null || A.length == 0)
            return 0;
        
        int count = 1, ind = 1;
        for(int i = 1; i < A.length; i++){
            count = A[i - 1] != A[i] ? 1 : count + 1;
            if(count <= 2)
                A[ind++] = A[i];
        }
        
        return ind;
    }
    
    /**
Submission Result: Wrong Answer
Input:      [1,1,1,2]
Output:     [1,1,1]
Expected:   [1,1,2]

     * So we also need to update the array :)
     */
}
