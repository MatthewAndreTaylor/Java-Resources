package runtimetest;

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
public class RunTimeTest{
    
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
        for(int i = 0; i < needle.length(); i++)
        {
            f_needle.put(needle.charAt(i), ((f_needle.get(needle.charAt(i)) != null) ? f_needle.get(needle.charAt(i)) : 0) + 1);
        }
        
        Set<Integer> usedPermutations = new HashSet<>();
        for(int i = 0; i < haystack.length()-needle.length() +1; i++)
        {
            String haysubstr = haystack.substring(i, i + needle.length());
            if(i ==0)
            {
                for(int j =0; j < haysubstr.length(); j++)
                {
                    f_haystack.put( haystack.charAt(j), ((f_haystack.get(haystack.charAt(j)) != null) ? f_haystack.get(haystack.charAt(j)) : 0) + 1 );
                }
            }
            else
            {
                char added = haystack.charAt(i + haysubstr.length() -1);
                f_haystack.put(added, ((f_haystack.get(added) != null) ? f_haystack.get(added) : 0) + 1);

                char removed = haystack.charAt(i -1);
                int countRemove = f_haystack.get(removed);
                f_haystack.put(removed, countRemove - 1);
                if(f_haystack.get(removed) == 0)
                {
                    f_haystack.remove(removed);
                }
            }
            
            if(f_needle.equals(f_haystack) && !usedPermutations.contains(haysubstr.hashCode()))
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