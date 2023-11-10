package snow;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Matt
 */
public class Snow 
{
    private int pixelsLost;
    private Point p;
    private Point nextP;
    private int pixelCount;
    private int width;
    private int height;
    private Color color;
    private BufferedImage img;    
 
    public Snow(Color col, int size, double density ) {
        pixelsLost = 0;
        p = new Point();
        nextP = new Point();
        width = size;
        height = size;
        color = col;
        pixelCount = (int) ( width * height * density );
        img = new BufferedImage( width, height, BufferedImage.TYPE_INT_ARGB );
    }
 
    public void generate() {
        // print text to the console
        System.out.println( "Drawing " + pixelCount + " pixels" );
        int background = img.getRGB( 0, 0 );
        img.setRGB(width / 2, height / 2, color.getRGB());
 
        for( int i = 0; i < pixelCount; i++ ) {
            p.x = (int) ( Math.random() * width );
            p.y = (int) ( Math.random() * height );
 
            while ( true ) {
                int dx = (int) ( Math.random() * 3 ) - 1;
                int dy = (int) ( Math.random() * 3 ) - 1;
                nextP.setLocation( p.x + dx, p.y + dy );
                // handle out-of-bounds
                if ( nextP.x < 0 || nextP.x >= width || nextP.y < 0
                        || nextP.y >= height ) {
                        // increment the number of pixels lost and escape the loop
                    pixelsLost++;
                    break;
                }
                if ( img.getRGB( nextP.x, nextP.y ) != background ) {
                    img.setRGB( p.x, p.y, color.getRGB());
                    break;
                }
                p.setLocation( nextP );
            }
            // Print a message every 2%
            if ( i % ( pixelCount / 50 ) == 0 ) {
                System.out.println( "Done with " + i + " pixels" );
            }
        }
        // We're done. Let the user know how many pixels were lost
        System.out.println( "Finished. Pixels lost = " + pixelsLost );
    }
 
    public BufferedImage getImage() {
        return img;
    }
 
    public int getWidth() {
        return width;
    }
 
    public int getHeight() {
        return height;
    }
    
    public static void main( String[] args ){
        // create the new generator
        Snow snow = new Snow(Color.magenta, 400, 0.6 );
        JFrame jframe = new JFrame();
        jframe.setSize(500, 500);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // generate the image
        snow.generate();
        
        try {
            // save the image to the file "image.png"\
            ImageIO.write( snow.getImage(), "png", new File( "image.png" ));
        } catch ( IOException e ) {}
        
        // display the saved image
        //jframe.add(new JLabel(new ImageIcon("C:\\Users\\Matt\\Documents\\NetBeansProjects\\Snow\\image.png")));
        jframe.add(new JLabel(new ImageIcon(snow.getImage())));
        jframe.setVisible(true);
    }
}
