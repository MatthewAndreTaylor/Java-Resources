/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pianobot;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Matt
 */
public class PianoBot
{
    public static PianoBot pbot;
    public static Robot rob;
    boolean m_running;
    long m_lastTimeNanos;
    public int[] nums = {1440,1546, 1644, 1744};
    public static Color col;
    public boolean isScreenDone = false;
    public String inFile0 = "C:\\Users\\Matt\\Pictures\\Screenshots\\C1.png";
    public String inFile1 = "C:\\Users\\Matt\\Documents\\NetBeansProjects\\PianoBot\\C1.png";
    public static BufferedImage img;
    
    public float compareImage(File fileA, File fileB) {
    float percentage = 0;
    try {
        // take buffer data from both image files //
        BufferedImage biA = ImageIO.read(fileA);
        DataBuffer dbA = biA.getData().getDataBuffer();
        int sizeA = dbA.getSize();
        BufferedImage biB = ImageIO.read(fileB);
        DataBuffer dbB = biB.getData().getDataBuffer();
        int sizeB = dbB.getSize();
        int count = 0;
        // compare data-buffer objects //
        if (sizeA == sizeB) {

            for (int i = 0; i < sizeA; i++) {

                if (dbA.getElem(i) == dbB.getElem(i)) {
                    count = count + 1;
                }

            }
            percentage = (count * 100) / sizeA;
        } else {
            System.out.println("Both the images are not of same size");
        }

    } catch (Exception e) {
        System.out.println("Failed to compare image files ...");
    }
    return percentage;
}
    
    public static void Screenshot(String tcid,int sx,int sy,int ex,int ey) throws Exception
    {
        img = rob.createScreenCapture(new Rectangle(sx,sy,ex,ey));
        ImageIO.write(pbot.getImage(), "png" , new File("C1.png" ));
    }
    
    public BufferedImage getImage()
    {
        return img;
    }
    
    public void update(double elapsedTime) throws Exception
    {
            //System.out.println(MouseInfo.getPointerInfo().getLocation().x + "  " + MouseInfo.getPointerInfo().getLocation().y);
            //System.out.println(rob.getPixelColor(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y));
            Screenshot(inFile1, 100, 100, 100,100);
            System.out.println(compareImage(new File(inFile0),new File(inFile1) ));
            rob.delay(3000);
//            for(int i = 0; i < nums.length; i++)
//            {
//                if(rob.getPixelColor(nums[i], 720).equals(Color.BLACK) 
//                        || rob.getPixelColor(nums[i], 720).equals(col))
//                {
//                    rob.mouseMove(nums[i], 720);
//                    rob.mousePress(InputEvent.BUTTON2_MASK);
//                    rob.mouseRelease(InputEvent.BUTTON2_MASK);
//                }
//            }
    }
    
    public static void main(String[] args) throws AWTException, Exception
    {
        pbot = new PianoBot();
        pbot.start();
        rob = new Robot();
        
        while(pbot.isRunning())
        {
            long currentTimeNanos = System.nanoTime();
            long elapsedTimeNanos = currentTimeNanos - pbot.getLastTime();
            double elapsedTimeInSecond = (double)elapsedTimeNanos / 1000000000;
            
            pbot.update(elapsedTimeInSecond);
            try {
                Thread.sleep(1);
            } 
            catch (InterruptedException ex) {
                Logger.getLogger(PianoBot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
         
    /**
     * Starts the game running.
     */
    public void start()
    {
        m_running = true;
        m_lastTimeNanos = System.nanoTime();
    }
    
    // The game is running
    public boolean isRunning()
    {
        return m_running;
    }
    
    public long getLastTime()
    {
        return m_lastTimeNanos;
    } 
}
