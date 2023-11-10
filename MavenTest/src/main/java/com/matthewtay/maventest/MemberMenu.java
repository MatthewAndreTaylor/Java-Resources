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
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComponent;
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
public class MemberMenu 
{
    public Team myTeam;
    public String[] columns = {"Name", "ID", "TeamID",  "Position"};
    public DefaultTableModel model = new DefaultTableModel(columns, 0);
    public JTable table = new JTable(model);
    public JPanel teamPane = new JPanel();
    public Random random = new Random();

    public MemberMenu(Team team)
    {
        myTeam = team;
        JButton addButton = new JButton("Add Member");
        JButton removeButton = new JButton("Remove Member");
        JPanel buttons = new JPanel();
        buttons.add(addButton, BorderLayout.LINE_START);
        buttons.add(removeButton, BorderLayout.LINE_END);
        addButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                addMember();
            }
        });
        removeButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                removeMember();
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

    public void addMember() 
    {
        String[] row = new String[columns.length];
        row[0] = memberValues("Member Name:" , "Player0");
        row[1] = myTeam.teamID;
        row[2] = memberValues("Id:" , "z00001");
        row[3] = memberValues("Position:" , "Foward");
        
        myTeam.members.add(new TeamMember(row[0], row[1] , row[2], row[3]));
        model.addRow(row);
    }
    
    public void removeMember() 
    {
        String teamRevove = memberValues("Which Member to remove:" , "Player0");
        for (int i = 0; i < table.getRowCount(); i++) 
        {
            if(table.getValueAt(i, 0).equals(teamRevove))
            {
                model.removeRow(i);
                myTeam.members.remove(i);
                break;
            }
        }
    }
    
    public String memberValues(String message, String defaults)
    {
        TeamInputPanel namePanel = new TeamInputPanel(message , defaults);
        namePanel.showPanel("New Team");
        if(!namePanel.isValidText())
        {
            return memberValues(message, defaults);
        }
        return namePanel.getText();
    }

    public JComponent getComponent() 
    {
        return teamPane;
    }
}