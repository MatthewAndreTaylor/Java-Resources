/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Matt
 */
public class BinarySearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        Scanner scanS = new Scanner(System.in);
        File boys = new File("boys.txt");
        File girls = new File("girls.txt");
        Scanner scanBoys = new Scanner(boys);
        Scanner scanGirls = new Scanner(girls);
        ArrayList<String> boyNames = new ArrayList<>();
        ArrayList<String> girlNames = new ArrayList<>();
        while(scanBoys.hasNextLine())
        {
            boyNames.add(scanBoys.nextLine());
        }
        while(scanGirls.hasNextLine())
        {
            girlNames.add(scanGirls.nextLine());
        } 
        System.out.println("Enter a boys name:");
        if(boyNames.contains(scanS.nextLine()))
        {
            System.out.println("Wow that is a popular name");
        }
        else
        {
            System.out.println("Wow that is a unique name");
        }
        System.out.println("Enter a girls name:");
        if(girlNames.contains(scanS.nextLine()))
        {
            System.out.println("Wow that is a popular name");
        }
        else
        {
            System.out.println("Wow that is a unique name");
        }
        scanBoys.close();
        scanGirls.close();
            
// This type of search only works if the array is already sorted 
//        int[] nums = new int[] {-2, 0, 1 , 3, 5, 6, 7, 8, 90, 1000, 1001, 9000};
//        int target = 1000;
//        System.out.println(search(nums, target));
    
//        Scanner scanS = new Scanner(System.in);
//        ArrayList<String> strings = new ArrayList<>();
//        for(int i = 0; i < 10; i++)
//        {
//            strings.add(0, scanS.nextLine());
//        }
//        for(String e: strings)
//        {
//            System.out.println(e);
//        }
    }
    
    //finds index of target integer in an array using binary search
    public static int search(int[]nums, int target)
    {
        int midPointer;
        int leftPointer  = 0;
        int rightPointer = nums.length - 1;
        while(leftPointer <= rightPointer)
        {
            midPointer = (leftPointer + rightPointer)/2;
            if(nums[midPointer] == target)
            {
                return midPointer;
            }
            else if(nums[midPointer] > target)
            {
                rightPointer = midPointer - 1;
            }
            else
            {
                leftPointer = midPointer + 1;
            }
        }
        return -1;
    }
}
