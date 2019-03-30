
package game.main;

import java.awt.Rectangle;


public class GameObject {//It létre hozom az ellenség player bullet pozicioját és örököltetem a megfelelő osztályokba
    public double x,y;

    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Rectangle getBounds(){//A játékos körül csináljon négyzetet így érzékelve hogy összeütközik e
        return new Rectangle((int)x, (int)y,32,32);
    }

    
}
