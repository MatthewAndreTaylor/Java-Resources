/*************************************************************
 * Project: Worksheet Methods Styles 1&2- #4
 * Programmer: Matthew Taylor
 * Date: 2020/05/20
 * Program: RoundOfRisk.java
 * Description: Program simulates game play from Risk
 *************************************************************/
package roundofrisk;

public class RoundOfRisk
{
    public static void main(String[] args)
    {
        // Plays 100 rounds
        for (int i = 0; i < 100; i++)
        {
            // Prints round #
            System.out.println("\nRound " + (i+1) );
            riskRound();
        }
    }
    /**
     * Method Name: riskRound
     * Outputs one round of risk, the highest rolls and winner of the round
     */
    public static void riskRound()
    {
        
        // Creates array attackRolls sets size to 3 elements
        int attackRolls[] = new int[3];
        
        // Sets three random numbers at index points
        attackRolls[0] = (int)(1 + Math.random()* 6);
        attackRolls[1] = (int)(1 + Math.random()* 6);
        attackRolls[2] = (int)(1 + Math.random()* 6);
        
        // Loops finding biggest number in the array
        int biggestAttack = 1;
        for (int i = 0; i < attackRolls.length; i++)
        {
            if (attackRolls[i] > biggestAttack)
            {
                biggestAttack = attackRolls[i];
            }
        }
        
        // Prints out the attackers rolls
        System.out.println("Attackers dice rolls:( " + attackRolls[0] + ", "
                + attackRolls[1] + ", "  + attackRolls[2] + ")");
        System.out.println("Attackers biggest roll is " + biggestAttack);
        
        // Creates array defenseRolls sets size to 2 elements
        int defenseRolls[] = new int[2];
        
        // Sets two random numbers at index points
        defenseRolls[0] = (int)(1 + Math.random()* 6);
        defenseRolls[1] = (int)(1 + Math.random()* 6);
        
        // Finds bigger number in the array
        int biggestDefense = Math.max(defenseRolls[0], defenseRolls[1]);
        
        // Prints out the defenders rolls
        System.out.println("Defenders dice rolls:( " + defenseRolls[0] + ", "
                + defenseRolls[1] +  ")");
        System.out.println("Defenders biggest roll is " + biggestDefense);
        
        // Checks to see which player wins
        if (biggestAttack > biggestDefense)
        {
            System.out.println("Attacker wins");
        }
        if  (biggestDefense > biggestAttack)
        {
            System.out.println("Defender wins");
        }
        if (biggestDefense == biggestAttack)
        {
            System.out.println("In Risk tie goes to the defender");
        }
    }      
}
