/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprinterspeedv2;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Matt
 */
public class SprinterSpeedV2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        int lineCount= scanner.nextInt();
        List<Point2D> samples = new ArrayList<>();
        for(int i = 0; i < lineCount; i++)
        {
            Point2D sample = new Point2D.Double();
            sample.setLocation(scanner.nextInt(),  scanner.nextInt());
            samples.add(sample);
        }
        
        Collections.sort(samples, Comparator.comparingDouble((aDouble) -> aDouble.getX()));
        double maxSpeed = 0;
        for(int i = 0; i < samples.size()-1; i++)
        {
            double deltaTime =  samples.get(i+1).getX() - samples.get(i).getX();
            double distance =  Math.abs(samples.get(i+1).getY() - samples.get(i).getY());
            double speed = distance/ deltaTime;
            if(speed > maxSpeed)
            {
                maxSpeed = speed;
            }
        }
        System.out.println(maxSpeed);
    }
    
}
