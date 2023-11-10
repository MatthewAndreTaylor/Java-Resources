/***************************************************************
 * Project: FBI Undercover Agent
 * Programmer: Matthew Taylor
 * Date: 2021/03/11
 * Program: UnderCoverAgent.java
 * Description: Program organizes and prints suspects for agents
 ***************************************************************/
package undercoveragent;

import java.util.Arrays;
import java.util.Scanner;

public class UndercoverAgent 
{
    public static void main(String[] args)
    {
        // Creating a scanner for user input
        Scanner scanS = new Scanner(System.in);
        
        // String for the detcectives name
        String agentName;
        
        // Parallel arrays for suspect information
        String [] names = {"Bowman", "Walker", "Christian", "Edwards", "Cummings" ,"Halpern", "Scott", "Rhineheart" ,"Haley", "Brooks"};
        char [] sex = {'M' , 'F' , 'M' , 'M' , 'M' , 'F' , 'M' , 'F' , 'F', 'M'};
        String [] cars = {"Saturn", "Olds", "Chev", "Chev", "Ford", "Chev", "Ford", "Cad", "Honda", "Ford"};
        int [] year = {1999, 2000, 2001, 2003, 2004, 2002, 2006, 2005, 2002, 2004};
        
        // Using the selection sort method to sort the suspects alphabetically
        selectionSort(names, cars, sex , year);
        
        // Printing a title and user input prompt
        System.out.println("F E D E R A L  B U R E A U  O F  I N V E S T I G A T I O N\n");
        System.out.print("Please enter your code name: ");
        
        // Get agents name
        agentName = scanS.nextLine();
        
        // Since they have proven they are an agent I print the inf they are looking for
        if(agentName.toUpperCase().equals("INSPECTOR HOLMES") || agentName.toUpperCase().equals("PINK PANTHER"))
        {
            // Welecome message for agent
            System.out.println("\n\n*****WELCOME " + agentName.toUpperCase() + "*****\n");
            
            // Title and headers for table
            System.out.println("\t  People of Intrest\n\t  -----------------");
            System.out.format("%-16s %-6s %-11s %-6s%n", "NAME" , "SEX" , "CAR" , "YEAR");
            System.out.format("%-16s %-6s %-11s %-6s%n", "===============" , "=====" , "===========" , "======");
            
            // Printing all the suspects in sorted order
            for(int i = 0; i < names.length; i++)
            {
                System.out.format("%-16s %-6s %-11s %-6s%n", names[i] , sex[i]  ,cars[i], year[i]);
            }
            
            // Title and headers for table
            System.out.println("\n\n\tYour narrowed suspect list\n\t--------------------------");
            System.out.format("%-16s %-6s %-11s %-6s%n", "NAME" , "SEX" , "CAR" , "YEAR");
            System.out.format("%-16s %-6s %-11s %-6s%n", "===============" , "=====" , "===========" , "======");
            
            // Different type of search based on agent
            if(agentName.toUpperCase().equals("PINK PANTHER"))
            {
                // Narrowed suspects for list Panther
                for(int e : linearSearch(sex , cars, "panther"))
                {
                    System.out.format("%-16s %-6s %-11s %-6s%n", names[e] , sex[e]  ,cars[e], year[e]);
                }
            }
            else
            {
                // Narrowed suspects list for Holmes
                for(int e : linearSearch(sex , cars, "holmes"))
                {
                    System.out.format("%-16s %-6s %-11s %-6s%n", names[e] , sex[e]  ,cars[e], year[e]);
                }
            }  
        }
        else
        {
            System.out.println("SECURITY BREACH ***** You are not authorized to view this page ***** SECURITY BREACH");
        }
    }// End of main
    
    /**
     * Method Name: linearSearch
     * Finds which indexes of the elements that match the search
     * @param automobiles - The type of automobile to search for
     * @param genders - The type of gender to search for
     * @param detectiveName - The name of detective who the search is for
     * @return an array of integers of the indexes where the search criteria is met
     */
    public static int[] linearSearch (char[] genders, String [] automobiles, String detectiveName)
    {        
        // Creating a counter for the number of matches found
        int matches = 0;
        
        // Creating a new array the size of the number of matches that were found
        int[] arr = new int[matches];
        
        // Linear pass of the elements adding the values of indexes that are matches
        for(int i = 0; i < genders.length; i++)
        {
            // Compare to see if it is a match
            if(genders[i] == 'M' && automobiles[i].equals("Ford") && detectiveName.equals("holmes"))
            {
                // Found a match increasing array size
                matches++;
                arr = Arrays.copyOf(arr, matches);
                
                // Putting the index that found the match in the array
                arr[matches-1] = i;
            }
            
            // Compare to see if it is a match for detective holmes
            if(genders[i] == 'F' && detectiveName.equals("panther"))
            {
                // Found a match increasing array size
                matches++;
                arr = Arrays.copyOf(arr, matches);
                
                // Putting the index that found the match in the array
                arr[matches-1] = i;
            }
        }
        
        // Return the array of all of the idexes that were matches to the search
        return arr;
    }// End of linearSearch
    
    /**
     * Method Name: linearSearch
     * Sorts a given set of arrays based on peoples name alphabetically
     * @param people - The people to sort with
     * @param cars - The people to sort with
     * @param sex - The sexes to sort with
     * @param years - The years to sort with
     */
    public static void selectionSort (String[] people, String[] cars, char[] sex, int [] years)
    {
        // Declare variables
        int i , j;
        int indexToSwap;    // The index that will be swapped
        String tempName;   // Temporary variable for names
        String tempCar;   // Temporary variable for car
        char tempSex;   // Temporary variable for sex
        int tempYear;   // Temporary variable for year
        
        for(i=0; i<people.length-1; i++)
        {
            // Initializae to the index of the first of the unsorted cells
            indexToSwap = i;            
            
            // Initialize temp's to the value in the first unsorted cell
            tempName = people[indexToSwap];   
            tempCar = cars[indexToSwap];
            tempSex = sex[indexToSwap];
            tempYear = years[indexToSwap];
            
            // Search through the unsorted values
            for (j=1+i; j<people.length; j++)    
            {
                // Sorting by the first character alphabetically
                // Compare to method returns an integer that can be used to sort
                if (tempName.compareTo(people[j]) > 0)
                {
                    indexToSwap = j;
                    tempName = people[indexToSwap];
                    tempCar = cars[indexToSwap];
                    tempSex = sex[indexToSwap];
                    tempYear = years[indexToSwap];
                }// End of if statement
            }// End of inner for loop
            
            // Swap the value at the front of the unsorted cells
            people[indexToSwap] = people[i];     
            cars[indexToSwap] = cars[i];
            sex[indexToSwap] = sex[i];
            years[indexToSwap] = years[i];
                    
            people[i] = tempName;
            cars[i] = tempCar;
            sex[i] = tempSex;
            years[i] = tempYear;
        }// End of outer for loop
    }// End of selectionSort
}
