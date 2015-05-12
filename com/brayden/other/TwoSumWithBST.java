package com.brayden.other;

import java.util.Stack;

import org.junit.Test;
import static org.junit.Assert.*;

import com.brayden.LeetcodeTree;

public class TwoSumWithBST extends LeetcodeTree {

    public int[] findPair(TreeNode root, int target){
        if(root == null)
            return null;
        
        Stack<TreeNode> stack = new Stack<>(), stackR = new Stack<>();
        TreeNode cur, iter = root, curR, iterR = root;
        while(iter.left != null){
            stack.push(iter);
            iter = iter.left;
        }
        while(iterR.right != null){
            stackR.push(iterR);
            iterR = iterR.right;
        }
        cur = iter; curR = iterR;
        while(cur != curR){
            int sum = cur.val + curR.val;
            if(sum == target)
                return new int[]{cur.val, curR.val};
            if(sum < target)
                while(!stack.isEmpty() || iter != null)
                    if(iter != null){
                        stack.push(iter);
                        iter = iter.left;
                    }else{
                        cur = stack.peek();
                        iter = stack.pop().right;
                        break;
                    }
            else
                while(!stackR.isEmpty() || iterR != null)
                    if(iterR != null){
                        stackR.push(iterR);
                        iterR = iterR.right;
                    }else{
                        curR = stackR.peek();
                        iterR = stackR.pop().left;
                        break;
                    }
        }
        return null;
    }
    
    private static TreeNode root = makeTree("5, 3, 7, #, 4, 6");
    
    @Test
    public void test(){
        int[] expected = new int[]{5, 7};
        assertArrayEquals(expected, findPair(root, 12));
    }
}
