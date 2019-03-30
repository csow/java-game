
package game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class GameOver {

    
    public Rectangle playAgainButton = new Rectangle(Game.WIDTH/2+40,200,200,50);
    public Rectangle quitButton = new Rectangle(Game.WIDTH/2+40,300,200,50);
   

    
    
    
    
    public void render(Graphics g){  
      Graphics2D g2d = (Graphics2D) g;  
   
    
      
    //Game over 
    Font font = new Font("ariel", Font.BOLD,50);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Game Over", Game.WIDTH/2,100);
    //Your score   
    Font font1 = new Font("ariel", Font.BOLD,30);
        g.setFont(font1);
        g.setColor(Color.WHITE);
        g.drawString("YOUR SCORE IS "+ String.valueOf(Game.SCORE), Game.WIDTH/2, 150);
     //High score   
     
        
        
     //Play again
        Font font2 = new Font("ariel", Font.BOLD,30);
        g.setFont(font2);
        g.setColor(Color.WHITE);
        
        g.drawString("Play Again", playAgainButton.x+20,playAgainButton.y+35);
        g2d.draw(playAgainButton);
     //Quit   
       
        g.setFont(font2);
        g.setColor(Color.WHITE);
        
        g.drawString("Quit", quitButton.x+60,quitButton.y+35);
        g2d.draw(quitButton);
       
        
       
        
         
     
    }
   
   
    
    
}
