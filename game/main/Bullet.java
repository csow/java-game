
package game.main;

import game.main.lib.Animation;
import game.main.newpackage.entity.EntityA;
import game.main.newpackage.entity.EntityB;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Bullet extends GameObject implements EntityA{//Entitynél
   
    
    private Textures tex;
    private Game game; //collision-nél
   Controller controller;
   
    
    //Animáció
    Animation anim;

    public Bullet(double x, double y, Textures tex,Game game,Controller controller) {//B2
        super(x,y);
        this.tex = tex;
        this.game = game;
        this.controller= controller;
     
        anim = new Animation(10, Textures.bullet[0],Textures.bullet[1],Textures.bullet[2]);
        //B6
       
    }

    
    
   public void tick(){//B3 a golyó sebességét adja
       y -=10;
       //collision
       //if(Physics.Collsion(this, (EntityB) game.eb)){
         //  System.out.println("x");
       //}
       for (int i = 0; i < game.eb.size(); i++) {
            EntityB tempEnt = game.eb.get(i);
            if(Physics.Collsion(this, tempEnt)){
                controller.removeEntity(tempEnt);
                 Game.SCORE +=10;     
                 game.setEnemyKilled(game.getEnemyKilled()+1);
            }
        }
      
     
      
       anim.runAnimation();
           
   }
   public void render(Graphics g){//B4
       //g.drawImage(Textures.bullet[0], (int)x, (int) y, null);//B7
       anim.drawAnimation(g, x, y, 0);
       
   }   
   //collision
   public Rectangle getBounds(){//A játékos körül csináljon négyzetet így érzékelve hogy összeütközik e
        return new Rectangle((int)x, (int)y,32,32);
   }
    //Entity miatt kell
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
