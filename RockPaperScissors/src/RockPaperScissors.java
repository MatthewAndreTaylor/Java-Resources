/*******************************************
 * Project: Rock Paper Scissors
 * Programmer: Matthew Taylor
 * Date: 2020/03/23
 * Program: RockPaperScissors.java
 *******************************************/
import java.util.Scanner;

public class RockPaperScissors
{
    static final int PAPER_CHOICE = 0;
    static final int SCISSORS_CHOICE  = 1;
    static final int ROCK_CHOICE  = 2;
    
    static final String PAPER_STRING = "paper";
    static final String SCISSORS_STRING = "scissors";
    static final String ROCK_STRING = "rock";
    
   
    
    public static void main(String[] args)
    {
        Scanner scanS = new Scanner(System.in);
        
        // Banner
        System.out.println("Rock Paper Scissors");
        System.out.println("======================");

        
         // User Input Move
        System.out.println("Please input your choice(Paper, Scissors, Rock)");
        String playerChoiceStr = scanS.nextLine();
        
        // Process result
        int playerChoice;
        int computerChoice = (int)(Math.random()*3);
        
        switch(playerChoiceStr.toLowerCase())
        {
            case(RockPaperScissors.PAPER_STRING) :
                playerChoice = RockPaperScissors.PAPER_CHOICE;
                break;
            case(RockPaperScissors.SCISSORS_STRING) :
                playerChoice = RockPaperScissors.SCISSORS_CHOICE;
                break;
            case(RockPaperScissors.ROCK_STRING) :
                playerChoice = RockPaperScissors.ROCK_CHOICE;
                break;
            default :
                System.out.println("Bad input I will just choose rock for you");
                playerChoice = ROCK_CHOICE;
                break;
        }        
        
        int result =  computerChoice - playerChoice;
        
        // Display Results
        switch (result)
        {
            case -2:
            case 1:
                System.out.println("Computer Wins");
                break;
            case -1:
            case 2:
                System.out.println("User Wins");
                break;
            default:
                System.out.println("Tie play again");
                break;
        }
        
    }
    
}
