/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchingforstrings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * The problem with this is it uses to much space for storing permutations
 * @author Matt
 */
public class SearchingForStrings {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        String inFile = "C:\\Users\\Matt\\Downloads\\all_data\\all_data\\senior_data\\s3\\s3.1-04.in";
        int counter = 0;
        
        BufferedReader reader = null;
        try
        {
        reader = new BufferedReader(new FileReader(inFile));
        
        String searchFor = reader.readLine();
        String searchString = reader.readLine();
        List<String> searchValues = new ArrayList<>();
        Set<String> set = generatePerm(searchFor);
        Iterator<String> iterator = set.iterator();
        
        while(iterator.hasNext())
        {
            String perm = iterator.next();
            //System.out.println(perm);
            searchValues.add(perm);
        }
        for(String s: searchValues)
        {
           if(searchString.contains(s))
           {
               counter++;
           }
        }
        System.out.println(counter);
        
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
    
    public static Set<String> generatePerm(String input)
    {
    Set<String> set = new HashSet<String>();
    if (input == "")
        return set;

    Character a = input.charAt(0);

    if (input.length() > 1)
    {
        input = input.substring(1);

        Set<String> permSet = generatePerm(input);

        for (String x : permSet)
        {
            for (int i = 0; i <= x.length(); i++)
            {
                set.add(x.substring(0, i) + a + x.substring(i));
            }
        }
    }
    else
    {
        set.add(a + "");
    }
    //System.out.println(set.toString());
    return set;
}
}
