/** ************************************************************
 * Project: Computer Cruelty
 * Programmer: Matthew Taylor
 * Date: 2020/04/13
 * Program: ComputerCruelty.java
 * Description: Charges fines to offenders of computer cruelty
 ************************************************************ */
package computercruelty;

import java.util.Scanner;
import java.text.DecimalFormat;

public class ComputerCruelty
{

    public static void main(String[] args)
    {
        // Variables
        String offenderName, investigatorName;
        int menuChoice;

        // Creates input scanners
        Scanner scanS = new Scanner(System.in);
        Scanner scanN = new Scanner(System.in);
        
        // Decimal formatter for menu and output usage
        DecimalFormat moneyMenuFmt = new DecimalFormat("($0.00)");
        DecimalFormat moneyDisplayFmt = new DecimalFormat("$0.00");

        // Displays the name of the society
        System.out.println("Society for the prevention of Cruelty to Computers");
        System.out.println("--------------------------------------------------");

        // Asks for the name of the offender and investigator
        System.out.println("Please type the name of the offender:");
        offenderName = scanS.nextLine();
        System.out.println("Please type the name of the investigator:");
        investigatorName = scanS.nextLine();

        // Creates the menu of possible offenses
        System.out.println("What cruelty offense did you observe?" +
                " Here are the choices:");
        System.out.println("      1. Making faces at the computer. " +  
                moneyMenuFmt.format(14.95));
        System.out.println("      2. Name calling at the computer. " + 
                moneyMenuFmt.format(34.95));
        System.out.println("      3. Finger shaking at the computer. " + 
                moneyMenuFmt.format(89.95));
        System.out.println("      4. Yelling loudly at the computer. " + 
                moneyMenuFmt.format(139.95));
        System.out.println("      5. Physically attacking the computer. " + 
                moneyMenuFmt.format(499.95));

        // Asks for the offense menu choice
        System.out.println("Please type the number(1-5) of the offense "
                + "that occured.");
        menuChoice = scanN.nextInt();

        // Determines fine based on input and prints formatted fine / message
        switch (menuChoice)
        {
            case 1:
            {
                System.out.println(offenderName + " charged a fine of " +  
                        moneyDisplayFmt.format(14.95) +
                        " for making faces at the computer.\n As reported by " +
                        investigatorName);
                break;
            }
            case 2:
            {
                System.out.println(offenderName + " charged a fine of " +
                        moneyDisplayFmt.format(34.95) +
                        " for name calling at the computer.\n As reported by " +
                        investigatorName);
                break;
            }
            case 3:
            {
                System.out.println(offenderName + " charged a fine of " +
                        moneyDisplayFmt.format(89.95) +
                         " for finger shaking at the computer.\n As reported by " +
                         investigatorName);
                break;
            }
            case 4:
            {
                if ((offenderName.equalsIgnoreCase("Mickey")
                        && investigatorName.equalsIgnoreCase("Minnie"))
                        || (offenderName.equalsIgnoreCase("Minnie")
                        && investigatorName.equalsIgnoreCase("Mickey")))
                {
                    // Fines over $100 cannot be issued against spouse 
                    System.out.println("Fines over " +
                            moneyDisplayFmt.format(100) +
                            " cannot be issued against a spouse");
                    break;
                }
                else
                {
                    System.out.println(offenderName + 
                            " charged a fine of " +
                            moneyDisplayFmt.format(139.95) +
                            " for yelling loudly at the computer." +
                            "\n As reported by " +
                            investigatorName);
                    break;
                }
            }
            case 5:
            {
                if (offenderName.equalsIgnoreCase("Donald"))
                {
                    // 40% special charge added for Donald
                    System.out.println(offenderName + " charged a fine of " +
                            moneyDisplayFmt.format(499.95* 1.4) +
                            " for physically attacking the computer." +
                            "\nAs reported by " +
                            investigatorName);
                    break;
                }
                else if ((offenderName.equalsIgnoreCase("Mickey")
                        && investigatorName.equalsIgnoreCase("Minnie"))
                        || (offenderName.equalsIgnoreCase("Minnie")
                        && investigatorName.equalsIgnoreCase("Mickey")))
                {
                    // Fines over $100 cannot be issued against spouse
                    System.out.println("Fines over " +
                            moneyDisplayFmt.format(100) +
                            " cannot be issued against a spouse");
                    break;
                }
                else
                {
                    System.out.println(offenderName +
                            " charged a fine of " +
                            moneyDisplayFmt.format(499.95) +
                            " for physically attacking the computer." +
                            "\n As reported by " +
                            investigatorName);
                    break;
                }
            }
            default:
            {
                // Invalid menu choice
                System.out.println(" You did not enter an apropriate response."
                        + "The program is ending");
                break;
            }
        }
    }
}
