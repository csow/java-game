
package game.main;

import game.main.newpackage.entity.EntityA;
import game.main.newpackage.entity.EntityB;
import java.util.LinkedList;


public class Physics {
    public static boolean Collsion(EntityA enta,EntityB entb){
       // for (int i = 0; i < entb.size(); i++) {
         if(enta.getBounds().intersects(entb.getBounds())){
             return true;
         }
       //}
        return false;
  }
    //A golyó semmisitse meg az ellenséget 3
    public static boolean Collsion(EntityB entb,EntityA enta){
        //for (int i = 0; i < enta.size(); i++) {
         if(entb.getBounds().intersects(enta.getBounds())){
             return true;
         }
      // }
        return false;
  }
}
