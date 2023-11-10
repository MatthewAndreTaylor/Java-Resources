package zzz3;

import java.util.*;

public class ZZZ3 
{
    public static void main(String[] args) 
    {
        String[] words = new String[] {"time", "me", "bell", "ell", "ime"};
        System.out.println(minimumLengthEncoding(words));
    }
    
    public static int minimumLengthEncoding(String[] words) 
    {
        int totalLength =0;
        List <String> list = new ArrayList <>();
        List <String> eliminated = new ArrayList<>();
        for(String str: words)
        {
            if(!list.contains(str))
            {
                list.add(str);
            }
        }
        Collections.sort(list, Comparator.comparing(s -> -s.length()));
        System.out.println(list);
        int counter = 0;
        for(String str: list)
        {
            String f = list.get(counter);
            if(eliminated.contains(f))
            {
                continue;
            }
            for(int i = counter+1; i < list.size(); i++)
            {
                if(str.contains(list.get(i)) && list.get(i).length() < str.length())
                {
                    if(str.lastIndexOf(list.get(i)) + list.get(i).length() ==  str.length())
                    {
                        eliminated.add(list.get(i));
                    }
                }
            }
            counter++;
        }
        list.removeAll(eliminated);
        System.out.println(list);
        for(String str: list)
        {
            totalLength += str.length() + 1;
        }
        return totalLength;
    }
}