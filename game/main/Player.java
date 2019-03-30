
package game.main;

import game.main.lib.Animation;
import game.main.newpackage.entity.EntityA;
import game.main.newpackage.entity.EntityB;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Player extends GameObject implements EntityA{
    
    
    //KEY8
    private double velX=0;
    private double velY=0;
    
    private BufferedImage player;
    private Textures tex;
    Game game;
    Animation anim;
    Controller controller;
        
    

    public Player(double x, double y, Textures tex,Game game,Controller controller) {
        super(x,y);
        this.tex = tex;
        this.game = game;
        this.controller= controller;
        anim = new Animation(10, Textures.player[0],Textures.player[1],Textures.player[2]);//Az első szám az niméció sebessége
       
    }
    public void tick(){
      x+=velX;//KEY 9 setter ezekre
      y+=velY;
      
      
      //Basic Collision
      if(x <= 0)
          x = 0;
      if(x >= 640 -20)
         x = 640-20;
      if(y <= 0)
          y=0;
      if(y >=480 - 32)
          y=480 - 32;
      
      //Health bar
        for (int i = 0; i < game.eb.size(); i++) {
            EntityB tempEnt = game.eb.get(i);
            if(Physics.Collsion(this, tempEnt)){
                controller.removeEntity(tempEnt);
                 Game.HEALTH -=10;     
                 game.setEnemyKilled(game.getEnemyKilled()+1);
            }
        }
      //Animációt inditja
      anim.runAnimation();
    }
    public void render(Graphics g){
       // g.drawImage(Textures.player [0],(int) x, (int) y, null);//drawImage intet vár de mi doubleként határoztuk meg az x,y.Ezért kell elé int
        anim.drawAnimation(g, x, y, 0);//Animáció
    }
    public Rectangle getBounds(){//A játékos körül csináljon négyzetet így érzékelve hogy összeütközik e
        return new Rectangle((int)x, (int)y,32,32);
    }
    //Bullethez adok getter.

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
   
    
    //KEY 10 getter setter velx,y-ra, Így elérem a game classban és ott folytatom a key Released key press

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }
}
