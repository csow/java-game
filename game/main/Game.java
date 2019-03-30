
package game.main;

import game.main.newpackage.entity.EntityA;
import game.main.newpackage.entity.EntityB;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public class Game extends Canvas implements Runnable {
    
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH/12*9;
    public static final int SCALE = 2;
    public static final String TITLE = "2D SPACE GAME";
    
    private Thread thread;
    private boolean running = false;
    
    private JFrame frame;
    
    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet=null;
    private BufferedImage background=null;//Back 1
    
   
    private Player p;
    private Controller c;//B11
    private Textures tex;//Tetures
    
    //spawning system 1 utána getter
    private int enemyCount = 5;
    private int enemyKilled = 0;
    
    //a lövés egyenletes legyen
    private boolean isShooting=false; //utána keypressed
    //collision
    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;
    
    //Game state utána tick és render majd keypressed
    public static enum STATE{
        MENU,
        GAME,
        OVER
    };
    public static STATE state = STATE.MENU,OVER;
   
    private Menu menu;
    
    //Healt bar
    
    public static int HEALTH = 100;
    
    //score
    public static int SCORE = 0;
    public static Integer highScore = 0;
    
    private GameOver go;
    
    //Save
    Save save;
    
    
    public void init(){
        requestFocus();//KEY 12
        
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            spriteSheet = loader.loadImage("/textures/sprite_sheet.png");
            background = loader.loadImage("/textures/background.png");//Back 2 utána render
        } catch (Exception e) {
            e.printStackTrace();
        }
        addKeyListener(new KeyInput(this));//KEY 12
        tex = new Textures(this);
        
        c = new Controller(tex,this);//B12 (A TEX CSAK AZ ENEMYNÉL JÖN BE   a this a golyó semisitse meg az ellenséget
        p = new Player(200,200,tex,this,c);
        c.createEnemy(enemyCount);//Spawning system 3 utána Enemy class
        //collision
        ea = c.getEntityA();
        eb = c.getEntityB();
        //
        menu = new Menu();
        go = new GameOver();
        save = new Save();
      
        //Mouse input
        this.addMouseListener(new MouseInput());
        
    }
    
    public void run(){
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates=0;
        int frames=0;
        long timer=System.currentTimeMillis();
        while(running){  
            long now = System.nanoTime();
            delta += (now - lastTime )/ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis()-timer > 1000){
                timer +=1000;
                //fps számláló
                frame.setTitle(TITLE+"    |    "+updates+" Ups"+ "  "+frames + " Fps");
                updates=0;
                frames=0;
            }
           
        }
        stop();
        
    }
    
    private void tick(){
        if(state == STATE.GAME){
          
            p.tick();//player class
            c.tick();//B13
           
            
            
        }
      
        //ez arra kel, hogy miután megöltem öket ne tünjenek hanem újak jöjjenek
        if(enemyKilled>=enemyCount){
            enemyCount +=2;
            enemyKilled =0;
            c.createEnemy(enemyCount);
        }
         
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        ///////////////////////////////////////////
        g.drawImage(image,0,0,getWidth(),getHeight(),this);
        g.drawImage(background, 0,0, null); //Back 3
        if(state == STATE.GAME){
        
            p.render(g);//player class
            c.render(g);//B14 Ez után vissza a Controllerbe
            
             //Health bar megrajzolása
            g.setColor(Color.red);
            g.fillRect(5, 5, 100, 25);
             
            g.setColor(Color.green);
            g.fillRect(5, 5, HEALTH, 25);
             
            g.setColor(Color.white);
            g.drawRect(5, 5, 100, 25);
            
            g.drawString("SCORE " + SCORE,300,20);
            g.drawString("High Score" + highScore ,300,30);
            g.drawString("HEALTH " + String.valueOf(HEALTH),10 ,20);
           
           
            
              
             
             //////////////////////
    } if (state == STATE.MENU){
            //Ami ide kerül az lesz a menüben
            menu.render(g);
        }
            ////////
        if(HEALTH==0){
            state=STATE.OVER;
            go.render(g);
           
        }
        /*if(state == STATE.OVER){
            save.saveHighscore();
            go.render(g);
            
        }*/
        
        ////////////////////////////////////////////////
        g.dispose();
        bs.show();
        
        
    }
    public void keyPressed(KeyEvent e){//KEY 3
        int key = e.getKeyCode();//KEY 6
        if(state == STATE.GAME){
        if(key == KeyEvent.VK_RIGHT){
            
          p.setVelX(5); //KEY 11 ez után init
        }else if(key == KeyEvent.VK_LEFT){
           p.setVelX(-5); 
       
        }else if(key == KeyEvent.VK_DOWN){
            
           p.setVelY(5); 
        }else if(key == KeyEvent.VK_UP){//key 7 player class
           p.setVelY(-5); 
         //B16   
        }else if (key == KeyEvent.VK_SPACE && ! isShooting){
            isShooting=true;
           c.addEntity(new Bullet(p.getX(),p.getY()-32,tex,this,c));
           
        }
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT){
            
          p.setVelX(0); //KEY11
        }else if(key == KeyEvent.VK_LEFT){
           p.setVelX(0); 
       
        }else if(key == KeyEvent.VK_DOWN){
            
           p.setVelY(0); 
        }else if(key == KeyEvent.VK_UP){
           p.setVelY(0); 
            
        } else if(key == KeyEvent.VK_SPACE){
            isShooting = false;
        }
        
    }
    
    public synchronized void start(){
        if(running)
                return;
        running=true;        
        thread = new Thread(this);
        thread.start();
        
        
    }
    
    public synchronized void stop(){
        if(!running)
            return;
        running=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
        System.exit(1);
        
    }
            
    

    
    public static void main(String[] args) {
        Game game = new Game();
        
        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        // vagy így csinálom vagy JFrame frame = new JFrame és akkor az elején nem kell private JFrame;
        game.frame = new JFrame(game.TITLE);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setResizable(false);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        
        game.start();
       
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }
    
    //Spawning system: 2 getter setter. Utána controller 

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int getEnemyKilled() {
        return enemyKilled;
    }

    public void setEnemyKilled(int enemyKilled) {
        this.enemyKilled = enemyKilled;
    }
    
    
    
}
