/*****************************************************************
 * Class: SafeArea
 * Programmer: Matthew Taylor
 * Date: April 18, 2021
 * Program: SafeArea.java
 * Description: Keeps track of a container of a single rectangle with no movement
 *****************************************************************/
package frogger;

import java.awt.Color;

public class SafeArea extends GameObject
{
    // Constructor
    public SafeArea(int x, int y, int width, int height, Color col) 
    {
        super(x, y, width, height, col, 0);
    }
}
