/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matthewtay.maventest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Matt
 */
public final class Menu 
{
    public JFrame mainMenu;
    public JFrame teamMenu;
    public JFrame teamMemberMenu;
    public ArrayList <Team> teams = new ArrayList<>();
    int width, height;
    
    public Menu()
    {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        width = screen.width-50;
        height = screen.height-50;
        createMainMenu();
    }
    
    public void createTeamMenu()
    {
        teamMenu = new JFrame("TeamMenu");
        teamMenu.getContentPane().add(new TeamMenu().getComponent());
        teamMenu.pack();
        teamMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        teamMenu.setVisible(true);
    }
    
    public void createMainMenu()
    {
        // Create main menu
        mainMenu = new JFrame("Main Menu");
        mainMenu.setVisible(true);
        mainMenu.setSize(width, height);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton teamsButton = new JButton("Teams");
        JButton tournamentButton = new JButton("Tournament");
        teamsButton.setPreferredSize(new Dimension(width/2, height/2));
        tournamentButton.setPreferredSize(new Dimension(width/2, height/2));
        mainMenu.add(teamsButton, BorderLayout.LINE_START);
        mainMenu.add(tournamentButton, BorderLayout.LINE_END);
        
        // Trigger event when clicked
        teamsButton.addActionListener(new ActionListener()
        {
            // Button was clicked
            public void actionPerformed(ActionEvent e) 
            {
                //
            }
        });
        teamsButton.addActionListener(new ActionListener()
        {
            // Button was clicked
            public void actionPerformed(ActionEvent e) 
            {
                createTeamMenu();
            }
        });
    }
}
