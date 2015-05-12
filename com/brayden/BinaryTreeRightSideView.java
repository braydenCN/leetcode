package com.brayden;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView extends LeetcodeTree {
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null)
            return Collections.emptyList();
        
        List<Integer> resList = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        Deque<TreeNode> q1 = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()){
            resList.add(q.peekLast().val);
            while(!q.isEmpty()){
                TreeNode n = q.poll();
                if(n.left != null)
                    q1.offer(n.left);
                if(n.right != null)
                    q1.offer(n.right);
            }
            Deque<TreeNode> tmp = q;
            q = q1;
            q1 = tmp;
        }
        return resList;
    }
    
    public List<Integer> rightSideView1(TreeNode root) {
        if(root == null)
            return Collections.emptyList();
        
        List<Integer> resList = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            TreeNode n = null;
            for(int i = 0; i < size; i++){
                n = q.poll();
                if(n.left != null)
                    q.offer(n.left);
                if(n.right != null)
                    q.offer(n.right);
            }
            resList.add(n.val);
        }
        return resList;
    }
}
