/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;
import java.util.Collections;

/**
 *
 * @author Matt
 */
public class Anagrams {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        ArrayList arr = new ArrayList();
        int maxSupply = 100;
        
        for (int i = 1; i <= maxSupply; i++) {
            arr.add(i);
        }
        for (int i = 0; i < maxSupply; i++) {
            int rand = (int)(Math.random() *100);
            int n = i + rand % (maxSupply - i);
            Collections.swap(arr, i, n);
        }
        System.out.println(arr);
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//        boolean flag = false;
//        String f = "soaspgpqworgeogsdmfasfmalsfmasgknaiognaoidnvoidfmvodimvxvimxlvasdlwlkeqwkejnksjvndkjvndknbsdkbnsdkjl";
//        String n = "****************************************************************************************************";
//        Map<Character, Integer>  nMap= new HashMap<>();
//        for(int i = 0; i < n.length(); i++)
//        {
//            if (!nMap.containsKey(n.charAt(i)))
//            {
//                nMap.put(n.charAt(i), 1);
//            }
//            else
//            {
//                int count = nMap.get(n.charAt(i));
//                nMap.put(n.charAt(i), count + 1);
//            }
//        }
//        
//        //checking
//        for(int i = 0; i < f.length(); i++)
//        {
//            if(nMap.containsKey(f.charAt(i)))
//            {
//                int count = nMap.get(f.charAt(i));
//                if(count > 0)
//                {
//                    nMap.put(f.charAt(i), count - 1);
//                }
//                else
//                {
//                    if(nMap.containsKey('*'))
//                    {
//                        int countAst = nMap.get('*');
//                        if(countAst > 0)
//                        {
//                            nMap.put('*', countAst - 1);
//                        }
//                        else
//                        {
//                            flag = true;
//                            break;
//                        }
//                    }
//                    else
//                    {
//                        flag = true;
//                        break;
//                    }
//                }
//            }
//            else
//            {
//                if(nMap.containsKey('*'))
//                {
//                    int countAst = nMap.get('*');
//                        if(countAst > 0)
//                        {
//                            nMap.put('*', countAst - 1);
//                        }
//                        else
//                        {
//                            flag = true;
//                            break;
//                        }
//                }
//                else
//                {
//                    flag = true;
//                    break;
//                }
//            }
//        }
//        if(flag == true)
//        {
//            System.out.println("N");
//        }
//        else
//        {
//            System.out.println("A");
//        }
//    } 

