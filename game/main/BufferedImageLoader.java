
package game.main;

import com.sun.javafx.iio.ImageLoader;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class BufferedImageLoader {
    
   private BufferedImage image;
   public BufferedImage loadImage(String path) throws IOException{
       image = ImageIO.read(getClass().getResource(path));
               return image;
   }
    
     
     
    
}
