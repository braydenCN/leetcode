package com.brayden;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Other_LeastCommonAncesterOfBinaryTree extends LeetcodeTree {
    
    private TreeNode n1;
    private TreeNode n2;
    private List<TreeNode> n1List;
    private List<TreeNode> n2List;
    private boolean n1Reached = false;
    private boolean n2Reached = false;
    
    public TreeNode getLeastCommonAncester1(TreeNode root, TreeNode n1, TreeNode n2){
        if(root == null || n1 == null || n2 == null)
            return null;
        
        if(n1 == n2)
            return n1;
        
        this.n1 = n1;
        this.n2 = n2;
        buildPathLists(root, new ArrayList<TreeNode>());
        if(!n1Reached || !n2Reached)
            return null;
        
        TreeNode lcaNode = root;
        int i;
        for(i = 0; i < n1List.size() && i < n2List.size(); i++){
            if(n1List.get(i) != n2List.get(i))
                return lcaNode;
            lcaNode = n1List.get(i);
        }
        
        if(i == n1List.size())
            return n1List.get(i - 1);
        if(i == n2List.size())
            return n2List.get(i -  1);
        return null; // should never happen
    }

    private void buildPathLists(TreeNode root, List<TreeNode> pathList) {
        if(root == null)
            return;
        pathList.add(root);
        if(root == n1){
            n1Reached = true;
            n1List = new ArrayList<>(pathList);
        }
        if(root == n2){
            n2Reached = true;
            n2List = new ArrayList<>(pathList);
        }
        if(!n1Reached || !n2Reached)
            buildPathLists(root.left, pathList);
        if(!n1Reached || !n2Reached)
            buildPathLists(root.right, pathList);
        
        pathList.remove(pathList.size() - 1);
    }
    
    /**
     * Tree for test.
     *           1
     *      2          3
     *    4   5      6   7
     *  8
     */
    private TreeNode root = makeTree("1, 2, 3, 4, 5, 6, 7, 8");

    
    @Test
    public void test(){
        TreeNode n1 = root.left.left.left; // 8
        TreeNode n2 = root.left.right;  // 5
        TreeNode lca = root.left; // 2
        assertTrue(lca == getLeastCommonAncester(root, n1, n2));
    }
    
    @Test
    public void test3(){
        TreeNode n2 = root.left.left.left; // 8
        TreeNode n1 = root.left.right;  // 5
        TreeNode lca = root.left; // 2
        assertTrue(lca == getLeastCommonAncester(root, n1, n2));
    }
    
    @Test
    public void test1(){
        TreeNode root = makeTree("1, 2, 3, 4, 5, 6, 7, 8");
        TreeNode n1 = root.left.left.left; // 8
        TreeNode n2 = root.right.left;  // 6
        TreeNode lca = root;            // 1
        assertTrue(lca == getLeastCommonAncester(root, n1, n2));
    }
    
    @Test
    public void test2(){
        TreeNode root = makeTree("1, 2, 3, 4, 5, 6, 7, 8");
        TreeNode n1 = root.left.left.left; // 8
        TreeNode n2 = root.left;  // 2
        TreeNode lca = n2;            // 2
        assertTrue(lca == getLeastCommonAncester(root, n1, n2));
    }
    
    @Test
    public void test4(){
        TreeNode n2 = root.left.left.left; // 8
        TreeNode n1 = new TreeNode(100); 
        TreeNode lca = null;
        assertTrue(lca == getLeastCommonAncester(root, n1, n2));
    }
    
    /** solution from gfg **/
    public TreeNode getLeastCommonAncester(TreeNode root, TreeNode n1, TreeNode n2){
        if(root == null || n1 == null || n2 == null)
            return null;
        
        if(n1 == n2)
            return n1;

        TreeNode result = getLcaInternal(root, n1, n2);
        if(!n1Reached || !n2Reached)
            return null;
        
        return result;
    }
    
    private TreeNode getLcaInternal(TreeNode root, TreeNode n1, TreeNode n2){
        if(root == null)
            return null;
        
        TreeNode res = null;
        if(root == n1){
            n1Reached = true;
            res = root;
        }
        if(root == n2){
            n2Reached = true;
            res =root;
        }
        
        TreeNode left = getLcaInternal(root.left, n1, n2);
        TreeNode right = getLcaInternal(root.right, n1, n2);
        if(left != null && right != null)
            res = root;

        if(res != null)
            return root;
        return left != null ? left : right;
    }
}
