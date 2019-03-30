
package game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter{
    
    Game game;//KEY 1

    public KeyInput(Game game) {//KEY 2 konstruktor
        this.game = game;
    }
    
    @Override
    public void keyPressed(KeyEvent e){//KEY 3, KEY 5 game.key... berakni. Amikor meghívom a KeyEventet az meg hívja a bennelévő
        game.keyPressed(e);            // game.key-t ami az lesz amit bemásoltunk a game class-ba
    }
    
    @Override
    public void keyReleased(KeyEvent e){//KEY 3, KEY 4 copy a két KeyEvent és bemásolni a game classba
        game.keyReleased(e);
    }
    
    

    
    
    
    
}
