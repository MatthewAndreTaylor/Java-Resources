package clock;

/**
 *
 * @author Matt
 */
public class Clock {
    public static Clock clock;
    boolean m_running;
    long m_lastTimeNanos;
    
    public void start()
    {
        m_running = true;
        m_lastTimeNanos = System.nanoTime();
    }
    
    public void stop()
    {
        m_running = false;
    }
    
    public boolean isRunning()
    {
        return m_running;
    }
    
    public long getLastTime()
    {
        return m_lastTimeNanos;
    }
    
    public void update(double time)
    {
        System.out.println(time);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        clock = new Clock();
        clock.start();
        
        while(clock.isRunning())
        {
            long currentTimeNanos = System.nanoTime();
            long elapsedTimeNanos = currentTimeNanos - clock.getLastTime();
            double elapsedTimeInSecond = (double)elapsedTimeNanos / 1000000000;
            
            clock.update(elapsedTimeInSecond);
            try {
                Thread.sleep(1);
            } 
            catch (InterruptedException ex) {}
            
        }
    } 
}
