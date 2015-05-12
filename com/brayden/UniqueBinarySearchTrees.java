package com.brayden;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class UniqueBinarySearchTrees extends LeetcodeTree {

	public int numTrees(int n) {
		/** use NORE as "nodes on rightmost edge" below **/
		int[] nums = new int[n + 1];
		nums[1] = 1;
		for(int i = 2; i <= n; i++){
			/** calculate from 2-node to n-node tree **/
			int[] newNums = new int[n + 1];
			for(int j = 1; j < i; j++){ 
				/** for every tree with j NORE **/
			    for(int k = 1; k <= j + 1; k++)
			    	/** 
			    	 * cur is number of trees with j NORE; for every tree with
			    	 * j NORE, it produces cur of trees with 1/2/../j+1 NORE 
			    	 */
			    	newNums[k] += nums[j];
			}
			nums = newNums;
		}
		return sum(nums, 1, nums.length);
	}
	
	private int sum(int[] nums, int begin, int end) {
		int result = 0;
		for(int i = begin; i < end; i++)
			result += nums[i];
		return result;
	}

	public int numTreesV1(int n) {
		/**
		 * Ok then, it's all about the rightmost edge of the tree.
		 * need to record node numbers on the rightmost edge of each f(n) tree
		 * say for one tree of f(n), it has x node on the rightmost edge,
		 * the extended tree number of this tree is x+1; 
		 * with node number on the rightmost edge of:
		 *    1, 2, 3, ..., x+1
		 */
		List<Integer> resList = new LinkedList<>();
		List<Integer> tmpList = new LinkedList<>();
		resList.add(1);
		while(--n > 0){
			tmpList.clear();
			tmpList.addAll(resList);
			resList.clear();
			for(int i: tmpList)
				for(int j = 0; j <= i; j++)
					resList.add(j + 1);
		}
		return resList.size();
	}

	public int numTrees_obselete(int n) {
        /**
         * WRONG!!!!!
         * f(n+1) = 2*f(n) + {cases that n is not root}
         * in case n is not root: 
         *   1) add n+1 as new root;
         *   2) add n+1 as rightmost node of root
         *   3) left rotate 1)
         * in case n is root:
         *   4) add n+1 as root's right child
         *   5) add n+1 as new root
         * note: n becomes root for 1)5), non-root for 2)3)4)
         * say for n, it has x(n) cases of as non-root, y(n) cases as root
         *   f(n)   = x(n)   + y(n)
         *   f(n+1) = 3*x(n) + 2*y(n)
         *   x(n+1) = 2*x(n) + y(n)
         *   y(n+1) = x(n) + y(n)
         */
    	int nonRootNum = 0, rootNum = 1;
    	for(int i = 1; i < n; i++){
    		int tmp = 2 * nonRootNum + rootNum;
    		rootNum += nonRootNum;
    		nonRootNum = tmp;
    	}
    	return nonRootNum + rootNum;
    }

    @Test
    public void test(){
    	assertEquals(1,          numTrees(1));
    	assertEquals(2,          numTrees(2));
    	assertEquals(5,          numTrees(3));
    	assertEquals(14,         numTrees(4)); // yes, 1423 is missing
    	System.out.println(numTrees(13));
    	assertEquals(9694845,    numTrees(15));
    	assertEquals(35357670,   numTrees(16));
    	assertEquals(129644790,  numTrees(17));
    	assertEquals(477638700,  numTrees(18));
    	assertEquals(1767263190, numTrees(19));
    }
    
    public int numTrees1(int n) {
        int[] a = new int[n+1];
        a[0] = a[1] = 1;
        for(int i = 2; i <= n; i++)
            for(int j = 0; j < i; j++)  
                a[i] += a[j]*a[i - 1 - j];
        return a[n];
    }
}
