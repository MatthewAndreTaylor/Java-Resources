/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytrees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Matt
 */
public class BinaryTrees
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        TreeNode t = new TreeNode(2);
        t.insert(t, 0);
        t.insert(t, 5);
        t.insert(t, 1);
        t.insert(t, -1);
        t.insert(t, 4);
        t.insert(t, 7);
        t.insert(t, 9);
        t.insert(t, -4);
        t.insert(t, 3);
        
//        List<List<String>> list = printTree(t);
//        for(List<String> l : list)
//        {
//            for(String str : l)
//            {
//                System.out.print("  "+ str);
//            }
//            System.out.println("\n");
//        }
        
        String [][] strings = printTreeArr(t);
        for(String[] s : strings)
        {
            for(String str : s)
            {
                System.out.print("  "+ str);
            }
            System.out.println("\n");
        }
    }
    
    public static String[][] printTreeArr(TreeNode root) 
    {
        int height = root.findHeight(root); 
        String[][] matrix=new String[height][(int)Math.pow(2,height)-1];

        for(int i=0;i<height;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
                matrix[i][j]=" ";
        }

        BinaryForm(matrix,root,0,0,matrix[0].length-1);
        return matrix;
    }
    
    //List
    public static List<List<String>> printTree(TreeNode root) 
    {
        int height = root.findHeight(root); 
        String[][] matrix=new String[height][(int)Math.pow(2,height)-1];

        for(int i=0;i<height;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
                matrix[i][j]=" ";
        }

        BinaryForm(matrix,root,0,0,matrix[0].length-1);
        List<List<String>> list=new ArrayList<>();

        for(int i=0;i<height;i++)
            list.add(Arrays.asList(matrix[i]));

        return list; 
    }
    
    public static void BinaryForm(String matrix[][],TreeNode root,int row,int start,int end)
    {
        if(row == matrix.length || root == null)
            return;

        int mid=(start+end)/2;
        matrix[row][mid]=Integer.toString(root.val);

        BinaryForm(matrix,root.left,row+1,start,mid-1);
        BinaryForm(matrix,root.right,row+1,mid+1,end);
    }
}
