/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tandembike;

import java.util.Scanner;

/**
 *
 * @author Matt
 */
public class TandemBike {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner s = new Scanner(System.in);
        int sum = 0;
        int q = s.nextInt();
        int l = s.nextInt();
        int[] a = new int[l];
        int[] p = new int[l];
        for(int i=0; i< l; i++)
        {
            a[i] = s.nextInt();
        }
        for(int i=0; i< l; i++)
        {
            p[i]=s.nextInt();
        }
        if(q == 1)
        {
            a = mergeSortMax(a, 0, l-1);
            p = mergeSortMax(p, 0, l-1);
            for(int i =0; i < l; i++)
            {
                sum += Math.max(a[i], p[i]);
            }
        }
        if(q == 2)
        {
            a = mergeSortMin(a, 0, l-1);
            p = mergeSortMax(p, 0, l-1);
            for(int i =0; i < l; i++)
            {
                sum += Math.max(a[i], p[i]);
            }
        }
        System.out.println(sum);
    }
    
    
     public static int[] mergeSortMax(int arr[], int l, int r)
{
    if (l < r)
    {
        int m = (l+r)/2;

        mergeSortMax(arr, l, m);
        mergeSortMax(arr , m+1, r);

        // Merge the sorted halves
        mergeMaxMin(arr, l, m, r);
    }
    return arr;
}
    public static int[] mergeSortMin(int arr[], int l, int r)
{
    if (l < r)
    {
        int m = (l+r)/2;

        mergeSortMin(arr, l, m);
        mergeSortMin(arr , m+1, r);

        // Merge the sorted halves
        mergeMinMax(arr, l, m, r);
    }
    return arr;
}
     
    public static void mergeMaxMin(int arr[], int l, int m, int r)
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
        if (L[i] >= R[j])
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
    
    public static void mergeMinMax(int arr[], int l, int m, int r)
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
