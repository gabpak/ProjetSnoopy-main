package model;

public class Breakable extends Entity {
     /**
      * constructor for the block breakable 
      * @param x
      * @param y
      * @param map
      * @see Entity.Type
      */
     public Breakable(int x, int y, Map map){
        super(Entity.Type.BLOCK_BREAKABLE, x, y, map);
    }

    public String toString(){
        return "1";
    }
}
