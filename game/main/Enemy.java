
package game.main;

import game.main.lib.Animation;
import game.main.newpackage.entity.EntityA;
import game.main.newpackage.entity.EntityB;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Enemy extends GameObject implements EntityB{
   
    private Textures tex;
    Animation anim;
    //A golyó semmisitse meg az ellenséget 1 turána berakni a konstruktorba
    private Game game;
    private Controller c;
    
    Random r = new Random();//Amikor az ellenség le ér az aljára és újból jön a teteján máshol jelenjen meg=CE
    //Ha azt akarom, hogy mindegyik hajónak más legyen a sebbesége
    private int speed = r.nextInt(3)+1;
    
    public Enemy(double x, double y, Textures tex, Controller c, Game game) {//E2 megcsinálom a konstruktorát
        super(x,y);
        this.tex = tex;
        this.c = c;
        this.game = game;
        anim = new Animation(10, Textures.enemy[0],Textures.enemy[1],Textures.enemy[2]);
    }

    
    public void tick(){// E3 megcsinálom a tick és rendert
        y+=speed;
       // y +=3;//sebesége EZTUÁN MEGYEK A CONTROLLERBE
        if(y > (Game.WIDTH*Game.SCALE)){//Controling enemy amikor leér az aljára ujra jöjön a tetejéről
            y=-10;
            x=r.nextInt(640);//CE máshol jelenek meg
           
        }
       //ez arra kel, hogy miután megöltem öket ne tünjenek hanem újak jöjjenek
        for (int i = 0; i < game.ea.size(); i++) {
            
            EntityA tempEnt = game.ea.get(i);
            if(Physics.Collsion(this, tempEnt)){
            c.removeEntity(tempEnt); //miután eltalátam az ellenséget eltünik a golyó   
            c.removeEntity(this);
            
            game.setEnemyKilled(game.getEnemyKilled()+1);
        }
        }
       
        
        anim.runAnimation();
    }
    public void render(Graphics g){
       // g.drawImage(Textures.enemy[0], (int)x,(int) y, null);
       anim.drawAnimation(g, x, y, 0);
    }
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
