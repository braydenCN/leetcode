package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class MedianOfTwoSortedArrays extends LeetcodeCommon {

    public double findMedianSortedArrays(int A[], int B[]) {
        if(A == null || B == null)
            return 0;
        if(A.length == 0)
            return median(B);
        if(B.length == 0)
            return median(A);
        
        int lenA = A.length, lenB = B.length;
        boolean totalNumIsEven = (lenA + lenB) % 2 == 0;        

        int lowA = 0, highA = lenA - 1, medianInd = (lenA + lenB) / 2;
        if(totalNumIsEven)
            medianInd--; // index of the first median number of the two
                
        int midA = 0, num1 = 0, num2 = 0;
        while(lowA <= highA){
            midA = ((highA - lowA) >> 1) + lowA;
            /** for b in B[lowB..highB-1], A[midA]<=b<A[midA+1] **/
            int lowB = findLower(B, A[midA], 0, lenB - 1) + 1;
            int highB = (midA == lenA - 1) ? lenB : 
                                    findLower(B, A[midA + 1], 0, lenB - 1) + 1;

            if(midA == 0 && lowB - 1 >= medianInd){
                num1 = B[medianInd];
                num2 = (lowB - 1 == medianInd) ? A[midA] : B[medianInd + 1];
                break;
            }
            
            if(midA + lowB > medianInd)
                highA = midA - 1;
            else if(midA + highB < medianInd)
                lowA = midA + 1;
            else{
                if(midA + lowB == medianInd){ // case lowB==highB handled here
                    num1 = A[midA];
                    if(midA == lenA - 1)
                        num2 = (lowB == lenB) ? 0 : B[lowB];
                    else 
                        num2 = (lowB == lenB) ? A[midA + 1] : 
                            Math.min(A[midA + 1], B[lowB]);
                }else if(midA + highB == medianInd){
                    num1 = B[highB - 1];
                    if(midA == lenA - 1)
                        num2 = (highB == lenB) ? 0 : B[highB];
                    else 
                        num2 = (highB == lenB) ? A[midA + 1] : 
                            Math.min(A[midA + 1], B[highB]);
                }else{
                    num1 = B[medianInd - midA - 1];
                    num2 = B[medianInd - midA];
                }
                break;
            }
        }
        
        return totalNumIsEven ? (num1 + num2) / 2.0 : num1;
    }

    
    public double findMedianSortedArrays_Obsolete(int A[], int B[]) {
        if(A == null || B == null)
            return 0;
        if(A.length == 0)
            return median(B);
        if(B.length == 0)
            return median(A);
        
        int lenA = A.length;
        int lenB = B.length;
        if(lenA < lenB)
            return findMedianSortedArrays(B, A);
        
        boolean totalNumIsEven = (lenA + lenB) % 2 == 0;
        if(A[lenA - 1] <= B[0]){
            if(lenA == lenB)
                return (A[lenA - 1] + B[0]) / 2.0;
            int ind = (lenA + lenB) / 2;
            return totalNumIsEven ? (A[ind - 1] + A[ind]) / 2.0 : A[ind];
        }
        if(B[lenB - 1] <= A[0]){
            if(lenA == lenB)
                return (B[lenB - 1] + A[0]) / 2.0;
            int ind = (lenA - lenB) / 2;
            return totalNumIsEven ? (A[ind - 1] + A[ind]) / 2.0 : A[ind];            
        }
        
        int lowA = 0, highA = lenA - 1;
        int lowB_Bound = 0, highB_Bound = lenB - 1;
        if(totalNumIsEven)
            /** in this case, turn it into %2==1 case **/
            if(A[lenA - 1] >= B[lenB - 1])
                highA--;
            else 
                highB_Bound--;
        
        int totalLen = highA - lowA + highB_Bound - lowB_Bound + 2;
        
        int midA = 0, midB = 0;
        boolean resultInB = false;
outter: while(lowA <= highA){
            midA = ((highA - lowA) >> 1) + lowA;
            int lower = findLower(B, A[midA], lowB_Bound, highB_Bound);
            int higher = findHigher(B, A[midA], lowB_Bound, highB_Bound);
            if(lower >= 0 && midA + lower == totalLen / 2 && 
                    (midA == 0 || A[midA - 1] < B[lower])){
                resultInB = true;
                midB = lower;
                midA--;
                break;
            }
            if(higher < lenB && midA + higher == totalLen / 2 - 1 && 
                    (midA == lenA - 1 || A[midA + 1] > B[higher])){
                resultInB = true;
                midB = higher;
                break outter;
            }
            for(midB = lower + 1; midB <= higher; midB++){
                /** so B[midB] >= A[midA] **/
                if(midA + midB == totalLen / 2)
                    break outter;
                if(midB == higher)break;
            }
            if(midA + midB < totalLen / 2)
                lowA = midA + 1;
            else highA = midA - 1;
        }
        
        if(resultInB){
            if(!totalNumIsEven)
                return B[midB];
            if(midA > lenA - 1)
                return (B[midB + 1] + B[midB]) / 2.0;
            if(midB >= lenB - 1)
                return (A[midA + 1] + B[midB]) / 2.0;
            return (B[midB] + Math.min(B[midB + 1], A[midA + 1])) / 2.0;
        }else{
            if(!totalNumIsEven)
                return A[midA];
            if(midA >= lenA - 1)
                return (A[midA] + B[midB]) / 2.0;
            if(midB > lenB - 1)
                return (A[midA + 1] + A[midA]) / 2.0;
            return (A[midA] + Math.min(B[midB], A[midA + 1])) / 2.0;
        }
    }

    @Test
    public void test(){
        assertEquals(1.5, findMedianSortedArrays(new int[]{1}, new int[]{2}), 0.1);
    }
    
    @Test
    public void test1(){
        assertEquals(3, findMedianSortedArrays(new int[]{1, 4}, new int[]{3}), 0.1);
    }
    
    @Test
    public void test2(){
        assertEquals(2.5, findMedianSortedArrays(new int[]{2, 4}, new int[]{1, 3}), 0.1);
    }
    
    @Test
    public void test3(){
        assertEquals(5, findMedianSortedArrays(new int[]{3, 3, 6}, new int[]{5, 5}), 0.1);
    }
    
    @Test
    public void test4(){
        assertEquals(4.5, findMedianSortedArrays(new int[]{3, 3, 6}, new int[]{4, 5, 5}), 0.1);
    }
    
    @Test
    public void test5(){
        assertEquals(2.5, findMedianSortedArrays(new int[]{2, 3, 4}, new int[]{1}), 0.1);
    }
    
    @Test
    public void test6(){
        assertEquals(1, findMedianSortedArrays(new int[]{1}, new int[]{1}), 0.1);
    }
    
    @Test
    public void test7(){
        assertEquals(2.5, findMedianSortedArrays(new int[]{2}, new int[]{1, 3, 4}), 0.1);
    }
    
    @Test
    public void test8(){
        assertEquals(2.5, findMedianSortedArrays(new int[]{3}, new int[]{1, 2, 4}), 0.1);
    }
    
    @Test
    public void test9(){
        assertEquals(3.5, findMedianSortedArrays(new int[]{2}, new int[]{1, 3, 4, 5, 6}), 0.1);
    }
    
    @Test
    public void test10(){
        assertEquals(4, findMedianSortedArrays(new int[]{2}, new int[]{1, 3, 4, 5, 6, 7}), 0.1);
    }
    
    @Test
    public void test11(){
        assertEquals(3, findMedianSortedArrays(new int[]{2, 3}, new int[]{1, 3, 4, 5}), 0.1);
    }
    
    @Test
    public void test12(){
        assertEquals(4, findMedianSortedArrays(new int[]{2, 6}, new int[]{1, 3, 4, 5, 5}), 0.1);
    }
    
    @Test
    public void test13(){
        assertEquals(3, findMedianSortedArrays(new int[]{2, 3}, new int[]{1, 4, 5}), 0.1);
    }
    
    @Test
    public void test14(){
        assertEquals(3.5, findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4, 5, 6}), 0.1);
    }
    
    @Test
    public void test15(){
        /** ok then, the whole algorithm was destroyed by this test case **/
        assertEquals(3.5, findMedianSortedArrays(new int[]{1, 5, 6}, new int[]{2, 3, 4}), 0.1);
    }
}
