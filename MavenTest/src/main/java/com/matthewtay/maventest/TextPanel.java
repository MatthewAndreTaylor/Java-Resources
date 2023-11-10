/*****************************************************************
 * Class: TextPanel
 * Programmer: Matthew Taylor
 * Date: April 17, 2021
 * Program: TextPanel.java
 * Description: Keeps track of a text panel for a user menu and text message
 *****************************************************************/
package com.matthewtay.maventest;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Defines a simple text panel 
 */
public class TextPanel extends JPanel 
{
    /**
     * Constructor
     */
    public TextPanel(String labelText) 
    {   
        // Add a label to prompt user
        add(new JLabel(labelText));
    }
    
    /**
     * Method Name: showPanel
     * Shows a menu box to player
     * @return an integer representing the users menu choice
     */
    public int showOptions(String title, int optionType)
    {
        return JOptionPane.showConfirmDialog(null,this, title, optionType);
    }
}