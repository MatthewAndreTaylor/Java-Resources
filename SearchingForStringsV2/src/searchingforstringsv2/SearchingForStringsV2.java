/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchingforstringsv2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Matt
 */
public class SearchingForStringsV2
{
    public static int counter = 0;
    public static SearchingForStringsV2 search;
    public static Set<Integer> set = new HashSet<Integer>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        search = new SearchingForStringsV2();
        String inFile = "C:\\Users\\Matt\\Downloads\\all_data\\all_data\\senior_data\\s3\\s3.1-04y .in";
        int counter = 0;
        
        BufferedReader reader = null;
        try
        {
        reader = new BufferedReader(new FileReader(inFile));
        String s = reader.readLine();
        String st = reader.readLine();
        computePermutations(s.toCharArray(), 0, st);
        System.out.println(search.counter);
        
         }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch(IOException ioe)
            {
                System.out.println(ioe.toString());    
            }
        }
    }
    public static void swap(char[] ch, int i, int j)
    {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }
 
    public static void computePermutations(char[] ch, int currentIndex, String st)
    {
        if (currentIndex == ch.length - 1) {
            String value = String.valueOf(ch);
            Integer hashcode = new Integer(value.hashCode());
            if (!set.contains(hashcode))
            {
                set.add(hashcode);
                stringCheck(String.valueOf(ch), st);
            }
        }
 
        for (int i = currentIndex; i < ch.length; i++)
        {
            swap(ch, currentIndex, i);
            computePermutations(ch, currentIndex + 1, st);
            swap(ch, currentIndex, i);
        }
    }
    
    public static void stringCheck(String searchFor, String searchingIn)
    {
        if(searchingIn.contains(searchFor))
        {
            search.counter++;
        }
    }
}
