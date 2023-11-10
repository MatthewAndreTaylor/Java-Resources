/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchingforstringsv3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Matt
 */
public class SearchingForStringsV3 {
    
    //checks that both maps are the same
    public static boolean isPermutation(Map<Character, Integer> m0, Map<Character, Integer> m1)
    {
//        for(int i = 0; i < 26; i++)
//        {
//            char c = (char)(i+'a');
//            if(!Objects.equals(m0.get(c), m1.get(c)))
//            {
//                return false;
//            }
//        }
        
        return m0.equals(m1);
    }
    
    public static void main(String[] args)
    {   
        long then = System.currentTimeMillis();
        int searchCounter = 0;
        
        String inFile = "C:\\Users\\Matt\\Downloads\\all_data\\all_data\\senior_data\\s3\\s3.4-30.in";
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(inFile));
            String needle = reader.readLine();
            String haystack = reader.readLine();
        
        Map<Character, Integer> f_needle = new HashMap<>();
        Map<Character, Integer> f_haystack = new HashMap<>();
        //This maps the number of each character in the needle
        for(int i = 0; i < needle.length(); i++)
        {
            if (!f_needle.containsKey(needle.charAt(i)))
            {
                f_needle.put(needle.charAt(i), 1);
            }
            else
            {
                int count = f_needle.get(needle.charAt(i));
                f_needle.put(needle.charAt(i), count + 1);
            }
        }
        
        Set<Integer> usedPermutations = new HashSet<>();
        //This maps the number of each character in the substring of the haystack
        for(int i = 0; i < haystack.length()-needle.length() +1; i++)
        {
            String haysubstr = haystack.substring(i, i + needle.length());
            // putting in the first ones
            if(i ==0)
            {
                for(int j =0; j < haysubstr.length(); j++)
                {
                    if (!f_haystack.containsKey(haystack.charAt(j)))
                    {
                        f_haystack.put(haystack.charAt(j), 1);
                    }
                    else
                    {
                        int count = f_haystack.get(haystack.charAt(j));
                        f_haystack.put(haystack.charAt(j), count + 1);
                    }
                }
            }
            //adding in one and taking out one
            else
            {
                char added = haystack.charAt(i + haysubstr.length() -1);
                char removed = haystack.charAt(i -1);
                //adding the one
                if (!f_haystack.containsKey(added))
                {
                    f_haystack.put(added, 1);
                }
                else
                {
                    int countAdd = f_haystack.get(added);
                    f_haystack.put(added, countAdd + 1);
                }
                //removing the other
                int countRemove = f_haystack.get(removed);
                f_haystack.put(removed, countRemove - 1);
                if(f_haystack.get(removed) == 0)
                {
                    f_haystack.remove(removed);
                }
            }
            
            if(isPermutation(f_needle,f_haystack) && !usedPermutations.contains(haysubstr.hashCode()))
            {
                usedPermutations.add(haysubstr.hashCode());
                searchCounter++;
            }
        }
        }
        catch(IOException e)
        {
        }
        finally
        {
            System.out.println(searchCounter);
            long duration = System.currentTimeMillis() - then;
            System.out.println(duration);
            try
            {
                reader.close();
            }
            catch(IOException ioe)
            {       
            }
        }
    }  
}
