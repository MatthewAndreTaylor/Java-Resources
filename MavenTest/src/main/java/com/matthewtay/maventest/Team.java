/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matthewtay.maventest;

import java.util.ArrayList;

/**
 *
 * @author Matt
 */
public class Team 
{
    public ArrayList <TeamMember> members = new ArrayList<>();
    public String teamID;
    public String teamName;
    public String teamRegion;
    public int rank;
    
    public Team(String name, String id, String region)
    {
        teamName = name;
        teamID = id;
        teamRegion = region;
        rank = 0;
    }
    
    public String toString()
    {
        return teamName + " "+ teamID + " "+ teamRegion + " "+ rank;
    }
}
