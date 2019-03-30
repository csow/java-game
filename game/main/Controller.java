
package game.main;

import static game.main.Game.HEALTH;
import game.main.newpackage.entity.EntityA;
import game.main.newpackage.entity.EntityB;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;


public class Controller {//Bullet-hez kell B8
    private LinkedList<EntityA> ea = new LinkedList<EntityA>();
    private LinkedList<EntityB> eb = new LinkedList<EntityB>();
    
    
        EntityA enta;
        EntityB entb;
        private Textures tex;
        
        Random r = new Random();
        //A golyó semmisitse meg az ellenséget 4 utána konstruktor
        private Game game;
        
       public Controller(Textures tex, Game game){
           this.tex=tex;
           this.game = game;
           for (int i = 0; i < 20; i++) {
               
           }
           addEntity(new Enemy(r.nextInt(640),10,tex,this,game));
       }
       
       //Spawning system 3
       public void createEnemy(int enemyCount){
           if(HEALTH >=0){
           for (int i = 0; i < enemyCount; i++) {
               addEntity(new Enemy(r.nextInt(640),-10,tex, this, game));
           }
          // }else if(HEALTH <=0){
          //     removeEntity((EntityB) entb);
          }
       }
    
      
    
    
   
    
    public void tick(){
        //EntityA
        for (int i = 0; i < ea.size(); i++) {
            enta = ea.get(i);
            enta.tick();
        }
          //EntityB
        for (int i = 0; i < eb.size(); i++) {
            entb = eb.get(i);
            entb.tick();
        }
    }
    
    public void render(Graphics g){
        //EntityA
         for (int i = 0; i < ea.size(); i++) {
            enta = ea.get(i);
            enta.render(g);
        }
         //EntityB
         for (int i = 0; i < eb.size(); i++) {
            entb = eb.get(i);
            entb.render(g);
        }
    }
    
    public void addEntity(EntityA block){
        ea.add(block);
    }
     public void removeEntity(EntityA block){
        ea.remove(block);
    }
     public void addEntity(EntityB block){
        eb.add(block);
    }
     public void removeEntity(EntityB block){
        eb.remove(block);
    }
    //collison
     public LinkedList<EntityA> getEntityA(){
         return ea;
     }
     public LinkedList<EntityB> getEntityB(){
         return eb;
     }
}
