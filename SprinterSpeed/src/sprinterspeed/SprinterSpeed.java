/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprinterspeed;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;



class Sample implements Comparable
{
    public int time;
    public int position;
    
    @Override
    public int compareTo(Object o) 
    {
        Sample other = (Sample)o;
        if (this.time > other.time)
            return 1;
        else if (this.time < other.time)
            return -1;
        else
            return 0;   
    }
}

/**
 *
 * @author Matt
 */
public class SprinterSpeed {

        
    public static void main(String[] args) 
    {
        String inFile = "C:\\Users\\Matt\\Downloads\\all_data\\all_data\\senior_data\\s1\\s1.1-03.in";
        String outFile = "C:\\Users\\Matt\\Downloads\\all_data\\all_data\\senior_data\\s1\\s1.1-03.out";
        
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(inFile));
            String numberLines = reader.readLine();
            int lineCount = Integer.parseInt(numberLines);
            
            List<Sample> samples = new ArrayList<>();
            for (int i = 0; i < lineCount; i++)
            {
               String sampleLine = reader.readLine();
               StringTokenizer st = new StringTokenizer(sampleLine);
               Sample sample = new Sample();
               sample.time = Integer.parseInt(st.nextToken());
               sample.position = Integer.parseInt(st.nextToken());
               
               samples.add(sample);
            }            
            Collections.sort(samples);
            
            double maxSpeed = 0;
            for (int i = 0; i < samples.size()-1; i++)
            {
                Sample sample0 = samples.get(i);
                Sample sample1 = samples.get(i+1);
                int deltaTime =  sample1.time - sample0.time;
                double distance =  Math.abs(sample1.position - sample0.position);
                double speed = distance/ deltaTime;
                if(speed > maxSpeed)
                {
                    maxSpeed = speed;
                }
            }
            System.out.println(maxSpeed);
        }
        catch(Exception e)
        {
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch(IOException ioe)
            {
                
            }
        }
    }
    
}
