/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matthewtay.maventest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Matt
 */
public class TeamMenu 
{
    public ArrayList<Team> teams = new ArrayList<>();
    public String[] columns = {"Name", "ID", "Region",  "Rank"};
    public DefaultTableModel model = new DefaultTableModel(columns, 0);
    public JTable table = new JTable(model);
    public JPanel teamPane = new JPanel();
    public File [] myTeams;
    

    public TeamMenu()
    {
        JButton addButton = new JButton("Add Team");
        JButton removeButton = new JButton("Remove Team");
        JButton veiwButton = new JButton("Veiw Team");
        JButton saveButton = new JButton("Save Teams");
        JPanel buttons = new JPanel();
        buttons.add(addButton, BorderLayout.LINE_START);
        buttons.add(removeButton, BorderLayout.LINE_END);
        buttons.add(veiwButton, BorderLayout.CENTER);
        buttons.add(saveButton, BorderLayout.CENTER);
        
        addButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                addTeam();
            }
        });
        veiwButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                veiwTeam();
            }
        });
        removeButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                removeTeam();
            }
        });
        model = new DefaultTableModel(columns, 0) {

            @Override
            public Class getColumnClass(int column) 
            {
                return getValueAt(0, column).getClass();
            }
        };
        table = new JTable(model) 
        {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) 
            {
                Component c = super.prepareRenderer(renderer, row, column);
                if (isRowSelected(row) && isColumnSelected(column)) 
                {
                    ((JComponent) c).setBorder(new LineBorder(Color.red));
                }
                return c;
            }
        };
        teamPane.add(new JScrollPane(table), BorderLayout.CENTER);
        teamPane.add(buttons, BorderLayout.SOUTH);
    }

    public void addTeam() 
    {
        String[] row = new String[columns.length];
        row[0] = teamValues("Team Name:" , "Team0");
        row[1] = teamValues("Team Id:" , "x00001");
        row[2] = teamValues("Team Region:" , "Ontario");
        row[3] = "0";
        
        teams.add(new Team(row[0], row[1] , row[2]));
        model.addRow(row);
    }
    
    public void removeTeam() 
    {
        String teamRevove = teamValues("Which Team to remove" , "Team0");
        for (int i = 0; i < table.getRowCount(); i++) 
        {
            if(table.getValueAt(i, 0).equals(teamRevove))
            {
                model.removeRow(i);
                teams.remove(i);
                break;
            }
        }
    }
    
    public void saveTeams()
    {
        myTeams = new File[teams.size()];
        int counter = 0;
        for(File file: myTeams)
        {
            file = new File(teams.get(counter).teamName + ".txt");
            try
            {
                PrintWriter pt = new PrintWriter(file);
                pt.println(teams.get(counter).toString());
                for(TeamMember member: teams.get(counter).members)
                {
                    pt.println(teams.get(counter).toString());
                }
            }
            catch(IOException e)
            {
                System.out.println(e);
            }
            counter++;
        }
    }
    
    public void veiwTeam()
    {
        String teamGet = teamValues("Which Team to veiw:" , "Team0");
        for (int i = 0; i < table.getRowCount(); i++) 
        {
            if(table.getValueAt(i, 0).equals(teamGet))
            {
                JFrame memberMenu = new JFrame();
                memberMenu.getContentPane().add(new MemberMenu(teams.get(i)).getComponent());
                memberMenu.pack();
                memberMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                memberMenu.setVisible(true);
                break;
            }
        }
    }
    
    public String teamValues(String message, String defaults)
    {
        TeamInputPanel namePanel = new TeamInputPanel(message , defaults);
        namePanel.showPanel("New Team");
        if(!namePanel.isValidText())
        {
            return teamValues(message, defaults);
        }
        return namePanel.getText();
    }

    public JComponent getComponent() 
    {
        return teamPane;
    }
}
