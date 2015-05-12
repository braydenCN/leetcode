package com.brayden.other;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

import com.brayden.LeetcodeTree;


/**
 * Write a non-recursive algorithm to find the preorder successor of a given node in a BST.
Note: Parent pointers are not given. If the node doesn¡¯t exist you should return NULL. 
 */
public class FindPreOrderSuccessor extends LeetcodeTree {
    public TreeNode findPreOrderSuccessor(TreeNode root, TreeNode node){
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        boolean found = false;
        while(!stack.isEmpty() || cur != null){
            if(cur != null){
                if(cur == node){
                    found = true;
                    if(cur.left != null)
                        return cur.left;
                }
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop().right;
                if(found && cur != null)
                    return cur;
            }
        }
        return null;
    }
    
    private static TreeNode tree  = makeTree("1, 2, 3, 4, #, 5, 6");
    private static TreeNode node1 = tree;
    private static TreeNode node2 = tree.left;
    private static TreeNode node4 = node2.left;
    private static TreeNode node3 = tree.right;
    private static TreeNode node5 = node3.left;
    private static TreeNode node6 = node3.right;
    
    
    @Test
    public void test(){
        assertSame(node2, findPreOrderSuccessor(tree, node1));
        assertSame(node4, findPreOrderSuccessor(tree, node2));
        assertSame(node3, findPreOrderSuccessor(tree, node4));
        assertSame(node5, findPreOrderSuccessor(tree, node3));
        assertSame(node6, findPreOrderSuccessor(tree, node5));
        assertSame(null,  findPreOrderSuccessor(tree, node6));
        
    }
}
