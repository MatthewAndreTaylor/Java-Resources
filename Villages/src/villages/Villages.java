/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villages;

import java.util.Scanner;

/**
 *
 * @author Matt
 */
public class Villages {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner s = new Scanner(System.in);
        int l= s.nextInt();
        int [] nums = new int[l];
        double minDist = Integer.MAX_VALUE;
        for(int i = 0; i < l; i++)
        {
            nums[i] = s.nextInt();
        }
        nums = mergeSort(nums, 0, nums.length-1);
        double dist;
        for(int e = 1; e < nums.length-1; e++)
        {
            dist = (nums[e+1] - nums[e-1]);
            if(dist <  minDist)
            {
                minDist = dist; 
            }
        }       
        System.out.printf("%.1f",(double)(minDist/2));
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
}
