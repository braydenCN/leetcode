package com.brayden;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetcodeCommon {

    protected static class Point{
        int x, y;
        Point(int a, int b){x = a; y = b;}
    }
    
	protected static class ListNode {
		public int key, val;
		public ListNode next = null, pre = null;
		public ListNode(int val) {
			this.val = val;
		}
		public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
        @Override
        public String toString() {
            return "ListNode [key=" + key + ", val=" + val + ", next=" + next
                    + ", pre=" + pre + "]";
        }
	}

	public static class TreeNode {
		public int val;
		public TreeNode left, right;
		
		public TreeNode(int x) {
			val = x;
		}
	
		@Override
		public String toString() {
			return "TreeNode [val=" + val + ", left=" + left + ", right="
					+ right + "]";
		}
		
	}

	protected static class Interval{
            public int start;
            public int end;
            Interval(int s, int e) {start = s; end = e;}
            @Override
            public String toString() {
                return "Interval [start=" + start + ", end=" + end + "]";
            }
            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + end;
                result = prime * result + start;
                return result;
            }
            @Override
            public boolean equals(Object obj) {
                if (this == obj)
                    return true;
                if (obj == null)
                    return false;
                if (getClass() != obj.getClass())
                    return false;
                Interval other = (Interval) obj;
                if (end != other.end)
                    return false;
                if (start != other.start)
                    return false;
                return true;
            }
            public boolean cover(int i) {
                return i >= start && i <= end;
            }
        }

    protected int digitLength(int num) {
		int count = 1;
		while((num /= 10) != 0)
		    count++;
		return count;
	}

	protected int getDigitAtIndex(int num, int index) {
		while((--index) >= 0)
			num /= 10;
		return num % 10;
	}

	protected boolean isPalindrome(String s) {
	    int len = s.length();
	    for(int i = len / 2 - 1; i >= 0; i--)
	        if(s.charAt(i) != s.charAt(len - i - 1))
	            return false;
	    return true;
	}

	protected void assertEqualBetween2DimArrayAndList(Object[][] arr,
		List<List<Integer>> list) {
		for(int i = 0; i < arr.length; i++)
		   	assertArrayEquals(arr[i], list.get(i).toArray());
	}

	public int reverse(int x) {
	    int sign = 1;
	    if(x < 0){
	    	sign = -1;
	    	x *= -1;
	    }
	    
	    long result = 0;
	    while(x != 0){
	    	result = result * 10 + x % 10;
	    	if(sign * result > Integer.MAX_VALUE ||
	    			sign * result < Integer.MIN_VALUE)
	    		return 0;
	    	x = x / 10;
	    }
	    
	    return (int)result * sign;
	}

	protected char[][] get2DimCharArrayFromStringArr(String[] strArr) {
		char[][] arr = new char[strArr.length][strArr[0].length()];
	   	int i = 0;
	   	for(String s: strArr)
	   		arr[i++] = s.toCharArray();
	   	return arr;
	}

	protected void assert2DimArrayEquals(char[][] expected, char[][] arr) {
		for(int i = 0; i < expected.length; i++)
		    assertArrayEquals(expected[i], arr[i]);
	}

	protected void assert2DimArrayEquals(int[][] expected, int[][] arr) {
        for(int i = 0; i < expected.length; i++)
            assertArrayEquals(expected[i], arr[i]);
    }
	
	protected void assertListOfListEquals(List<List<Integer>> list1, List<List<Integer>> list2) {
		int len = list1.size();
		assertTrue(len == list2.size());		
		
		while(--len >= 0)
			assertListEquals(list1.get(len), list2.get(len));
	}

	protected <T> void assertListEquals(List<T> list1, List<T> list2) {
		assertTrue(list1.size() == list2.size());		
		
		for(int i = 0; i < list1.size(); i++)
		    assertEquals(list1.get(i), list2.get(i));
	}

	protected List<Integer> cloneList(List<Integer> pathList) {
		List<Integer> list = new ArrayList<>();
		list.addAll(pathList);
		return list;
	}

	protected int getIndex(int[] arr, int begin, int end, int num) {
		for(int i = begin; i < end; i++)
			if(arr[i] == num)
				return i;
		return -1;
	}

    protected int findFirst(int[] A, int target) {
        return findFirst(A, target, 0, A.length - 1);
    }

    protected int findFirst(int[] A, int target, int low, int high) {
        /* see detail logic in commented code below */
        while(low <= high){
            int mid = ((high - low) >> 1) + low;
            if(A[mid] < target)
                low = mid + 1;
            else if(A[mid] == target && (mid == 0 || A[mid - 1] < target))
                return mid;
            else
                high = mid - 1;
        }
        return -1;
    }
    
//    protected int findFirst(int[] A, int target, int low, int high) {
//        while(low <= high){
//            int mid = ((high - low) >> 1) + low;
//            if(A[mid] == target){
//                if(mid == 0 || A[mid - 1] < target)
//                    return mid;
//                else 
//                    high = mid - 1;
//            }else if(A[mid] < target)
//                low = mid + 1;
//            else //if(A[mid] > target)
//                high = mid - 1;
//        }
//        return -1;
//    }
//    
    protected int findLower(int[] A, int target){
        return findLower(A, target, 0, A.length - 1);
    }
    
    /** 
     * return index i, as A[i] < target <= A[i + 1] 
     * return -1 if no lower item
     */
    protected int findLower(int[] A, int target, int low, int high) {
        int lowBound = low;
        while(low <= high){
            int mid = ((high - low) >> 1) + low;
            if((A[mid] < target) && (mid == A.length - 1 || A[mid + 1] >= target))
                return mid;
            if(A[mid] < target)
                low = mid + 1;
            if(A[mid] >= target)
                high = mid - 1;
        }
        return lowBound - 1;
    }
    
    protected int findHigher(int[] A, int target) {
        return findHigher(A, target, 0, A.length - 1);
    }
    
    /** 
     * return index i, as A[i - 1] <= target < A[i]
     * return A.length if no higher item
     */
    protected int findHigher(int[] A, int target, int low, int high) {
        int highBound = high;
        while(low <= high){
            int mid = ((high - low) >> 1) + low;
            if((mid == 0 || A[mid - 1] <= target) && (A[mid] > target))
                return mid;
            if(A[mid] <= target)
                low = mid + 1;
            if(A[mid] > target)
                high = mid - 1;
        }
        return highBound + 1;
    }
    
    protected int findLast(int[] A, int target) {
        return findLast(A, target, 0, A.length - 1);
    }

    protected int findLast(int[] A, int target, int low, int high) {
        while(low <= high){
            int mid = ((high - low) >> 1) + low;
            if(A[mid] > target)
                high = mid - 1;
            else if(A[mid] == target && 
                    (mid == A.length - 1 || A[mid + 1] > target))
                return mid;
            else 
                low = mid + 1;
        }
        return -1;
    }    
    
//    protected int findLast(int[] A, int target, int low, int high) {
//        while(low <= high){
//            int mid = ((high - low) >> 1) + low;
//            if(A[mid] == target)
//                if(mid == A.length - 1 || A[mid + 1] > target)
//                    return mid;
//                else 
//                    low = mid + 1;
//            if(A[mid] < target)
//                low = mid + 1;
//            if(A[mid] > target)
//                high = mid - 1;
//        }
//        return -1;
//    }
    
    protected boolean intersected(Interval i1, Interval i2) {
//        return i1.start <= i2.end && i1.end >= i2.end ||
//                i2.start <= i1.end && i2.end >= i1.end;
        return !(i1.start > i2.end || i2.start > i1.end);
    }

    protected double median(int[] arr) {
        int len = arr.length;
        if(len % 2 == 1)
            return arr[len / 2];
        
        return (arr[len / 2 - 1] + arr[len / 2]) / 2.0;
    }

    protected void swap(int[] arr, int i, int j) {
        if(i == j)
            return;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    protected <T> void swap(T[] arr, int i, int j) {
        if(i == j)
            return;
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    protected ListNode cloneLinkList(ListNode head) {
        if(head == null)
            return null;
        ListNode newHead = new ListNode(head.val);
        newHead.next = cloneLinkList(head.next);
        return newHead;
    }

    protected void reverse(int[] num, int low, int high) {
        while(low < high)
            swap(num, low++, high--);
    }

    private List<Integer> arrToList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for(int n: arr)
            list.add(n);
        return list;
    }

    protected void assertStackEquals(Stack<Integer> s1, Stack<Integer> s2) {
        assertTrue(s1.equals(s2));
    }

    protected int gcd(int a, int b) { 
        return b == 0 ? a : gcd(b, a % b); 
    }

    protected static class SegmentTree{
       final private int[] tree;
       final private int[] arr;
       final private int len;
       
       private int getMid(int s, int e) {  
           return s + ((e - s) >> 1);  
       }

       private int RMQUtil(int treeLow, int treeHigh, 
               int queryLow, int queryHigh, int index){
         if(queryLow <= treeLow && queryHigh >= treeHigh)
             return tree[index];
      
         if(treeHigh < queryLow || treeLow > queryHigh)
             return -1;
         
         int mid = getMid(treeLow, treeHigh);
         int leftMinInd  =  RMQUtil(treeLow, mid, 
                 queryLow, queryHigh, 2 * index + 1);
         int rightMinInd =  RMQUtil(mid + 1, treeHigh, 
                 queryLow, queryHigh, 2 * index + 2);
         
         if(leftMinInd == -1)
             return rightMinInd;
         if(rightMinInd == -1)
             return leftMinInd;
         return arr[leftMinInd] < arr[rightMinInd] ? leftMinInd : rightMinInd;
     }
     
     int RMQ(int queryLow, int queryHigh){
         if (queryLow < 0 || queryHigh > tree.length - 1 || 
                 queryLow > queryHigh)
             return -1;
         
         return RMQUtil(0, len - 1, queryLow, queryHigh, 0);
     }

    private int constructSTUtil(int low, int high, int index){
         if (low == high){
             tree[index] = low;
             return low;
         }
         
         int mid         =  getMid(low, high);
         int leftMinInd  =  constructSTUtil(low, mid, index * 2 + 1);
         int rightMinInd =  constructSTUtil(mid + 1, high, index * 2 + 2);
         tree[index] = 
                 arr[leftMinInd] < arr[rightMinInd] ? leftMinInd : rightMinInd;
         return tree[index];
     }
     
    SegmentTree(int arr[]){
        this.arr = arr;
        len = arr.length; 
        int x = (int)(Math.ceil((Math.log(len)) / Math.log(2)));
        int max_size = 2 * (int)Math.pow(2, x) - 1;
        tree = new int[max_size];
      
         constructSTUtil(0, len - 1, 0);
     }
    }

    public static class Pair{
            public int index, val;
    
            public Pair(int index, int val) {
                this.index = index;
                this.val = val;
            }

            @Override
            public String toString() {
                return "Pair [index=" + index + ", val=" + val + "]";
            }

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + index;
                result = prime * result + val;
                return result;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj)
                    return true;
                if (obj == null)
                    return false;
                if (getClass() != obj.getClass())
                    return false;
                Pair other = (Pair) obj;
                if (index != other.index)
                    return false;
                if (val != other.val)
                    return false;
                return true;
            }
        }
   
}
