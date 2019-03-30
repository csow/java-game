
package game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Menu {
    //gombok
    public Rectangle playButton = new Rectangle(Game.WIDTH/2+100,150,100,50);//x,y kordináta, magasság, szélleség
    //public Rectangle helpButton = new Rectangle(Game.WIDTH/2+100,250,100,50);
    public Rectangle quitButton = new Rectangle(Game.WIDTH/2+100,250,100,50);
    
    public void render(Graphics g){
        //gombok
        Graphics2D g2d = (Graphics2D) g;
        
        //Cím
        Font font = new Font("ariel", Font.BOLD,50);//50 a betüméret
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("SPACE GAME", Game.WIDTH/2,100);
        
        //gombok
         Font font1 = new Font("ariel", Font.BOLD,30);//
        g.setFont(font1);
        g.setColor(Color.WHITE);
        g.drawString("Play", playButton.x+19,playButton.y+35);
      // g.drawString("Help", playButton.x+19,playButton.y+135);
        g.drawString("Quit", playButton.x+19,playButton.y+135);
        g2d.draw(playButton);
        //g2d.draw(helpButton);
        g2d.draw(quitButton);
    }
    
}
