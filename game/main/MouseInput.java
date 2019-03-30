
package game.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
     
        //Play button
        if(mx >= Game.WIDTH/2+100 && mx <= Game.WIDTH/2+200){
            if(my >= 150 && my <= 200){
                //Pressed play button
                Game.state = Game.STATE.GAME;
            }
        }
        //Play again
         if(mx >= Game.WIDTH/2+40 && mx <= Game.WIDTH/2+240){
            if(my >= 200 && my <= 250){
                //Pressed play again button
              Game.state  = Game.STATE.GAME;
               
             
            
            }
        }
        
         //Quit button
        if(mx >= Game.WIDTH/2+100 && mx <= Game.WIDTH/2+200){
            if(my >= 250 && my <= 300){
                //Pressed quit button
                System.exit(1);
            }
        }
         //Quit game over button
          if(mx >= Game.WIDTH/2+40 && mx <= Game.WIDTH/2+240){
            if(my >= 300 && my <= 350){
                //Pressed quit button
                System.exit(1);
            }
        }
         
         
   
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
