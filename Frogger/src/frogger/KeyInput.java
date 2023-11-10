/*****************************************************************
 * Class: KeyInput
 * Programmer: Matthew Taylor
 * Date: April 17, 2021
 * Program: KeyInput.java
 * Description: Keeps track of keyboard input
 *****************************************************************/
package frogger;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// KeyInput will inherit from KeyAdapter
public class KeyInput extends KeyAdapter
{
    // Which keys are pressed are stored in a boolean array
    private boolean [] keys;
    
    // Constructor
    public KeyInput()
    {
        keys = new boolean[4];
    }
    
    // Key pressed method ofKeyAdapter
    public void keyPressed(KeyEvent ke)
    {
         // Switch statement for different key presses
        switch(ke.getKeyCode())
        {
            case 38:
                // Handle up
                keys[0] = true;
                break;
            case 40:
                // Handle down 
                keys[1] = true;
                break;
            case 39:
                // Handle left
                keys[2] = true;
                break;
            case 37:
                // Handle right
                keys[3] = true;
                break;
        }
    }

    // Key released method ofKeyAdapter
    public void keyReleased(KeyEvent ke)
    {
        // Switch statement for different key releases
        switch(ke.getKeyCode())
        {
            case 38:
                // Handle up
                keys[0] = false;
                break;
            case 40:
                // Handle down 
                keys[1] = false;
                break;
            case 39:
                // Handle left
                keys[2] = false;
                break;
            case 37:
                // Handle right
                keys[3] = false;
                break;
        }
    }
    
    /**
     * Method Name: interrupt
     * Interrupt user input between presses
     */
    public void interrupt(int pos)
    {
        keys[pos] = false;
    }
    
    // Accessors and Mutators
    public boolean[] getKeys()
    {
        return keys;
    }
    
    public void setKeys(boolean[] keyIn)
    {
        keys = keyIn;
    }
}
