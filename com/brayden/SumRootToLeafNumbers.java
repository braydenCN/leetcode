package com.brayden;

import java.util.ArrayList;
import java.util.List;

public class SumRootToLeafNumbers extends LeetcodeTree {
    public int sumNumbers(TreeNode root) {
    	if(root == null)
    	    return 0;
    	
    	int result  =  0;
    	for(Path p: sumNumbersInternal(root))
    		result += p.sum;
    	return result;
    }

    private static class Path{
    	int sum, level;
    	Path(int sum, int level){this.sum = sum; this.level = level;}
    }
    
	private List<Path> sumNumbersInternal(TreeNode node) {
        List<Path> list = new ArrayList<>();
		if(node.left == null && node.right == null){ // leaf
			list.add(new Path(node.val, 1));
			return list;
		}
		
		if(node.left != null)
			for(Path p: sumNumbersInternal(node.left)){
				int pathSum = p.sum + node.val * p.level * 10;
				list.add(new Path(pathSum, p.level * 10));
			}
		if(node.right != null)
			for(Path p: sumNumbersInternal(node.right)){
				int pathSum = p.sum + node.val * p.level * 10;
				list.add(new Path(pathSum, p.level * 10));
			}
		return list;
	}
 
	/** 
	 * people have much simpler code below:
    int sumNumbers(TreeNode *root) {
       return  sumNumberUtil(root,0);
    }
    int sumNumberUtil(struct TreeNode* node, int val)
    {
        if(node==NULL)
        return 0;

        val= val*10+node->val;
        if(node->left==NULL && node->right==NULL)
        {
            return val;
        }

        return sumNumberUtil(node->left,val)+sumNumberUtil(node->right, val);
    }
	 */
    
}
