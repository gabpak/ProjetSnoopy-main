package model;


public class Unbreakable extends Entity {

    /**
     * Create bloc unbreakable 
     * @param x
     * @param y
     * @param map
     * @see Entity.Type
     */
    public Unbreakable(int x, int y, Map map){
        super(Entity.Type.BLOCK_UNBREAKABLE, x, y, map);
    }

    public String toString(){
        return "4";
    }
}