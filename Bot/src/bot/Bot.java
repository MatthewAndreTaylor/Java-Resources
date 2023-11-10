/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

/**
 *
 * @author Matt
 */
public class Bot
{

     //public static Color col;
    public static Bot bot;
     public static Robot rob;
     public static boolean bo;
     public static boolean m_running;
    public long m_lastTimeNanos;
    
     public boolean isRunning()
    {
        return m_running;
    }
    public void start()
    {
        m_running = true;
        m_lastTimeNanos = System.nanoTime();
    } 
    public long getLastTime()
    {
        return m_lastTimeNanos;
    }
     
    public static void main(String[] args) throws AWTException 
    {
        //int[] nums = {1440,1546, 1644, 1744};
        rob = new Robot();
        //col = new Color(2, 2 ,2);
        rob.setAutoDelay(100);
        bo = true;
        Scanner scan = new Scanner(System.in);
        bot = new Bot();
        bot.start();
        
        while(true)
        {
            rob.mouseMove(964, 524);
            rob.mousePress(InputEvent.BUTTON1_MASK);
            rob.delay(2);
            rob.mouseRelease(InputEvent.BUTTON1_MASK);
            long elapsedTime = System.nanoTime() - bot.getLastTime();
            double elapsedTimeSeconds = (double)elapsedTime/1000000000;
            //System.out.println(MouseInfo.getPointerInfo().getLocation().x + "  " + MouseInfo.getPointerInfo().getLocation().y);
            if(Math.round(elapsedTimeSeconds) % 60 == 0)
            {
                if(scan.nextInt() != 0)
                {
                    break;
                }
            }
//System.out.println(rob.getPixelColor(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y));
            
//            for(int i = 0; i < nums.length; i++)
//            {
//                if(rob.getPixelColor(nums[i], 690).equals(col))
//                {
    //                    rob.mouseMove(824, 629);
    //                    
    //                }
    //            }
            }
        }

    }
