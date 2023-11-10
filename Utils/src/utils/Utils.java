/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Matt
 */
class FactorPair
{
    public int r;
    public int c;

}

public class Utils {

    /**
     * @param args the command line arguments
     */
     public static List<FactorPair> getFactors(int num, int m, int n)
    {
        List<FactorPair> pairs = new ArrayList<>();
        for (int i = 1; i * i <= num; i++) 
        {
            if (num % i == 0) 
            {
               FactorPair p0 = new FactorPair();
               p0.c = i;
               p0.r = num/i;
               if(p0.c <= m && p0.r <= n)
               {
                   pairs.add(p0);
               }
               if(p0.c != p0.r)
               {
                   FactorPair p1 = new FactorPair();
                   p1.r = i;
                   p1.c = num/i;
                   if(p1.c <= m && p1.r <= n)
                   {
                        pairs.add(p1);
                   }
               }
            }  
        }   
        return pairs;
    }
    
    public static void main(String[] args) 
    {
        //number of digits in a number
        //int length = (int) (Math.log10(number) + 1);
        
        //sort list based on string length swap negative for greatest to least or least to greatest
        //Collections.sort(list, Comparator.comparing(s -> -s.length()));
        List <Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(5);
        nums.add(6);
        nums.add(7);
        System.out.println(square56(nums));
        
        // Ex. 
        // 
        BufferedInputStream bis = null;
        try
        {
            bis = new BufferedInputStream(new FileInputStream("C:/Users/Matt/Downloads/new.txt"));
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        Scanner scan = new Scanner(bis);
        System.out.println(scan.nextLine());
        
//        Scanner scan = new Scanner(System.in);
//        int goal = 6;
//        int source = 0;
//        int dimension = 4; //num of nodes
//        List<Integer> list = new ArrayList<>();
//        Map<Integer, Boolean> map =  new HashMap<>();
//        int m = list.size();
//        int dist = Integer.MAX_VALUE;
//        boolean[][] adjmat = new boolean[dimension][dimension];
//        Queue <Integer> q = new LinkedList<>();
//        q.add(source);
//        for(int i = 0; i < dimension-1; i++)
//        {
//            int p = scan.nextInt();
//            int z = scan.nextInt();
//            adjmat[p][z] = true;
//            adjmat[z][p] =true;
//        }
//        while(!q.isEmpty())
//        {
//            for(int i = 0; i < dimension-1; i++) 
//            {
//                if(adjmat[q.peek()][i])  
//                {
//                    if(!map.containsKey(i))
//                    {
//                        q.add(i);
//                    }
//                }
//            }
//            map.put(q.peek(), Boolean.TRUE);
//            q.remove();
//        }
        


//        //ordered least to greatest
//        Map <String, Integer> m0 = new TreeMap<>();
//        //ordered from key first placed in
//        Map <String, Integer> m1 = new LinkedHashMap<>();
//        //unordered
//        Map <String, Integer> m2 = new HashMap<>();
//        
//        for(String anything: m0.keySet()) //values for values
//        {
//            
//        }
//        for(Map.Entry<String, Integer> entry: m0.entrySet())
//        {
//            //both keys and values
//        }
        
//        for(int i = 0; i < 1000000; i++)
//        {
//            getFactors(18, 30, 30);
//        }
//        int currentH = 0;
//        int count = 0;
//        Scanner scanner = new Scanner(System.in);
//        do
//        {
//            int i = scanner.nextInt();
//            if(i > currentH)
//            {
//                i = currentH;
//                count++;
//            }
//            else
//            {
//                count++;
//            }
//        }
//        while(count< 9);
        
        
//        int[] arr = new int[]{2, 7, 4, 1, 5 ,3, 90, 800, 51, 42, 30, 16, 17, 40, 20, 60, 23, 900, 12, 19, 18, 500, 43, 160, 180, 170, 130, 612, 754, 328, 987, 654,
//        111, 666, 999, 505, 414, 232, 577, 588, 959, 909, 642};
//        long then = System.currentTimeMillis();
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(BubbleSortArr(arr)));
//        System.out.println(Arrays.toString(mergeSort(arr, 0, arr.length-1)));
//        long duration = System.currentTimeMillis() - then;
//        System.out.println(duration);
    }
    
    //given prices of stock at diffeent days gives max profit
    public int maxProfit(int[] prices, int fee) 
    {
//        int buy = 0;
//        int sell = -prices[0];
//        int length = prices.length;
//        for (int i = 1; i < length; i++) {
//            buy = Math.max(buy, sell + prices[i] - fee);
//            sell = Math.max(sell, buy - prices[i]);
//        }
//        return buy;
        
        int profit = 0;
        int purchase = -prices[0];
        for (int i = 1; i < prices.length; i++) 
        {
            profit = Math.max(profit, purchase + prices[i] - fee);
            purchase = Math.max(purchase, profit - prices[i]);
        }
        return profit;
    }
    
    
    public static int[] mergeSort(int arr[], int l, int r)
{
    if (l < r)
    {
        int m = (l+r)/2;

        mergeSort(arr, l, m);
        mergeSort(arr , m+1, r);

        // Merge the sorted halves
        merge(arr, l, m, r);
    }
    return arr;
}
    
    public static void merge(int arr[], int l, int m, int r)
{
    int n1 = m - l + 1;
    int n2 = r - m;

    int L[] = new int [n1];
    int R[] = new int [n2];

    for (int i=0; i<n1; ++i)
        L[i] = arr[l + i];
    for (int j=0; j<n2; ++j)
        R[j] = arr[m + 1+ j];

    int i = 0, j = 0;

    int k = l;
    while (i < n1 && j < n2)
    {
        //change the sign here to sort from max to min or min to max
        if (L[i] <= R[j])
        {
            arr[k] = L[i];
            i++;
        }
        else
        {
            arr[k] = R[j];
            j++;
        }
        k++;
    }
    while (i < n1)
    {
        arr[k] = L[i];
        i++;
        k++;
    }
    while (j < n2)
    {
        arr[k] = R[j];
        j++;
        k++;
    }
}
    
    //sorts an array from least to greatest in On^2 time
    //array is sorted by swapping elements
    public static int[] BubbleSortArr(int [] initialArr)
    { 
        boolean isChanged;
        for(int j = 0; j < initialArr.length-1; j++)
        {
            isChanged = false;
            for(int i  = 0; i <initialArr.length - j - 1; i++)
            {
                if(initialArr[i] > initialArr[i+1])
                {
                    isChanged = true;
                    int temp = initialArr[i];
                    initialArr[i] = initialArr[i +1];
                    initialArr[i +1] = temp;
                }
            }
            if(isChanged == false)
                break;
        }
        return initialArr;
    }
    
    public static void selcectionSort(int arr[]) {
		int min,minindex;
		for(int i=0;i<arr.length;i++)
		{
			min = arr[i];
			minindex = i;
			for(int j=i+1;j<arr.length;j++)
			{
				if(arr[j]<min)
				{
					min = arr[j];
					minindex = j;
				}
			}
			arr[minindex] = arr[i];
			arr[i] = min;
		}
	}
    
    //rotates an int array clockwise
    private int[][] rotateClockWise(int[][] matrix) {
        int size = matrix.length;
        int[][] ret = new int[size][size];

        for (int i = 0; i < size; ++i) 
        for (int j = 0; j < size; ++j) 
        ret[i][j] = matrix[size - j - 1][i]; //***counter clockwise -> ret[i][j] = matrix[j][size - i - 1]

        return ret;
    }
    
    /**
     * selectionSort
     * sorting via selection sort
     * @param nums - arrays of numbers
     */
    
    public static void selectionSort (int[] nums)
    {
        //declare variables
        int i;
        int j;
        int indexToSwap;    //the index that we are going to swap
        int temp;   //temporary variable
        
        for(i=0; i<nums.length-1; i++)
        {
            //initialize variables
            indexToSwap = i;            //initializae to the index of the first of the unsorted cells
            temp = nums[indexToSwap];   //initialize temp to the value in the first unsorted cell
            
            //search through the unsorted values
            for (j=1+i; j<nums.length; j++)    
            {
                if (temp>nums[j])
                {
                    indexToSwap = j;
                    temp = nums[indexToSwap];
                }// end of if statement
            }//end of inner for loop
            
            
            nums[indexToSwap] = nums[i];     //swap the value at the front of the unsorted cells
            nums[i] = temp;   
        }//end of outer for loop
    }//end of selectionSort
    
    public boolean wordBreak(String s, List<String> wordDict)
    {
        for(String str : wordDict)
        {
            if(s.contains(str))
            {
                s =s.replace(str, "");
            }
        }
        return s.isEmpty();
        
    }
    //doubles each element in a list
    public static List<Integer> doubling(List<Integer> nums) 
    {
        for(int i = 0; i < nums.size(); i ++)
        {
            nums.set(i, nums.get(i)*2);
        }
        return nums;
    }
    
    public boolean searchMatrix(int[][] matrix, int target) 
    {
//        if(target < matrix[0][0] || matrix == null) return false;
//        for(int i = 0; i < matrix.length; i++)
//        {
//            if(matrix[i][0] <= target && matrix[i][matrix[0].length-1] >= target)
//            {
//                int midPointer;
//                int leftPointer  = 0;
//                int rightPointer = matrix[0].length - 1;
//                while(leftPointer <= rightPointer)
//                {
//                    midPointer = (leftPointer + rightPointer)/2;
//                    if(matrix[i][midPointer] == target)
//                    {
//                       return true;
//                    }
//                    else if(matrix[i][midPointer] > target)
//                    {
//                        rightPointer = midPointer - 1;
//                    }
//                    else
//                    {
//                        leftPointer = midPointer + 1;
//                    }
//                }   
//            }
//        }
//        return false;
        
        for(int [] row: matrix) 
        {
            if(row[0] <= target && row[row.length-1] >= target)
            {
                int retVal = Arrays.binarySearch(row, target);
                if(retVal >= 0) return true;
            }
        }
        return false;
    }
    
    //add two binary numbers
    public String addBinary(String a, String b) 
    {
    int num0 = Integer.parseInt(a, 2);
    int num1 = Integer.parseInt(b, 2);

    int total = num0 + num1;
    return Integer.toBinaryString(total);
    }
    
    //editing and checking lists
    public static List<Integer> square56(List<Integer> nums) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < nums.size(); i++)
            {
                nums.set(i, 10+(nums.get(i)*nums.get(i)));
            }
        for(Integer i : nums)
        {
            if(i % 10 == 5 || i % 10 == 6)
            {
                list.add(i);
            }
        }
        nums.removeAll(list);
        return nums;
    }
}
