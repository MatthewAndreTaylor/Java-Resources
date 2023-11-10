package zzz4;

import java.util.*;

public class ZZZ4 
{
    
    public static void main(String[] args) 
    {
        int [][] nums = new int [][] {{1, 200, 4, 8}, {99}};
        System.out.println(max(nums));
    }
    
    public static int max(int[][] nums)
    {
        int max = nums[0][0];
        for(int i = 0; i < nums.length; i++)
        {
            for (int j = 0; j < nums[i].length; j++) {
                if(nums[i][j] > max)
                {
                    max = nums[i][j];
                }
            }
        }
        return max;
    }
}