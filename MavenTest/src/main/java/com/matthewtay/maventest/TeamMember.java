/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matthewtay.maventest;

/**
 *
 * @author Matt
 */
public class TeamMember 
{
    public String position;
    public String name;
    public String teamMemberID;
    public String teamID;
    
    
    TeamMember(String memberName, String teamID, String memberID, String pos) 
    {
        name = memberName;
        position = pos;
        this.teamMemberID = memberID;
        this.teamID = teamID;
    }
    
    @Override
    public String toString()
    {
       return name + " " + position; 
    }
}
