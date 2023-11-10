/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacontext;

/**
 *
 * @author matth
 */
public abstract class MonoAction implements Runnable{
    boolean m_running;
    long m_lastTimeNanos;
    
    /**
     * Starts the game running.
     */
    public void start()
    {
        m_running = true;
        m_lastTimeNanos = System.nanoTime();
    }
    
    /**
     * Stops the action from running
     */
    public void pause()
    {
        m_running = false;
    }
    
    /**
     * @return whether the action is running
     */
    public boolean isRunning()
    {
        return m_running;
    }
    
    /**
     * @return last timing snapshot
     */
    public long getLastTime()
    {
        return m_lastTimeNanos;
    }
    
    public abstract void update(double elapsedTimeInSecond);
   
    /**
     * While the app is running update with the current time
     */
    @Override
    public void run()
    {
        this.start();
        while(this.isRunning())
        {
            long currentTimeNanos = System.nanoTime();
            long elapsedTimeNanos = currentTimeNanos - this.getLastTime();
            double elapsedTimeInSecond = (double)elapsedTimeNanos / 1000000000;
            this.update(elapsedTimeInSecond);
        }
    }
}
