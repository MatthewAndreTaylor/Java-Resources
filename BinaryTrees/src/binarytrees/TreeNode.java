/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytrees;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Matt
 */
public class TreeNode 
{
    int val;
    TreeNode left;
    TreeNode right;
      
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) 
    {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    
    public TreeNode searchTree(TreeNode t, int target)
    {
        if(t == null)
            return null;
        if(t.val == val)
            return t;
        if(val > t.val)
            return searchTree(t.right, target); //in this case I assume larger values are on the right of the tree
        else
            return searchTree(t.left, target);
    }
    
    // returns a tree that is inverted
    public TreeNode invertTree(TreeNode t)
    {
        //recursing
        TreeNode leftNode = invertTree(t.left);
        TreeNode rightNode = invertTree(t.right);
        
        t.left = rightNode;
        t.right = leftNode;
        return t;
    }
    
    /**
     * finds the height of a desired node in a binary tree
     * @param t
     * @return the height of the node
     */
    public int findHeight(TreeNode t) 
    {
        if (t == null) 
        {
            return 0; //starts at height 1
        }

        int leftHeight = findHeight(t.left);
        int rightHeight = findHeight(t.right);

        if (leftHeight > rightHeight) 
        {
            return leftHeight + 1;
        }
        else 
        {
            return rightHeight + 1;
        }
    }
    
   public TreeNode createTree(int[] array) 
   {
       if (array == null || array.length==0) {
           return null;
       }

       Queue<TreeNode> treeNodeQueue = new LinkedList<>();
       Queue<Integer> integerQueue = new LinkedList<>();
       for (int i = 1; i < array.length; i++) {
           integerQueue.offer(array[i]);
       }

       TreeNode treeNode = new TreeNode(array[0]);
       treeNodeQueue.offer(treeNode);

       while (!integerQueue.isEmpty()){
           Integer leftVal = integerQueue.isEmpty() ? null : integerQueue.poll();
           Integer rightVal = integerQueue.isEmpty() ? null : integerQueue.poll();
           TreeNode current = treeNodeQueue.poll();
           if (leftVal !=null) {
                   TreeNode left = new TreeNode(leftVal);
                   current.left = left;
                   treeNodeQueue.offer(left);
           }
           if (rightVal !=null){
                   TreeNode right = new TreeNode(rightVal);
                   current.right = right;
                   treeNodeQueue.offer(right);
           }
       }
       return treeNode;
   }
    
    public TreeNode insert(TreeNode t, int value) 
    {
        if (t == null) {
            return new TreeNode(value);
        }

        if (value < t.val)
        {
            t.left = insert(t.left, value);
        } 
        else if (value > t.val) 
        {
            t.right = insert(t.right, value);
        } 
        else 
        {
            // value already exists
            return t;
        }
        return t;
    }
}
