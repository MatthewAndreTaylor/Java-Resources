/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindromes;

/**
 *
 * @author Matt
 */
public class Palindromes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        String s = "ababa";
        System.out.println(removePalindromeSub(s));
    }
    public static int removePalindromeSub(String s) 
    {
        //Slower
//        if (s.isEmpty())
//        {
//            return 0;
//        }
//        String pop = "";
//        for(int i = s.length()-1; i >=1; i--)
//        {
//            pop = pop.concat(Character.toString(s.charAt(i)));
//        }
//        System.out.println(pop);
//        return s.equals(pop) ? 1 : 2;
        
        //String builder
        if (s.isEmpty())
        {
            return 0;
        }  
        String pop = s;
        StringBuilder input = new StringBuilder();
        input.append(pop);
        input.reverse();
        pop = input.toString();
        return s.equals(pop) ? 1 : 2;
    }
}
