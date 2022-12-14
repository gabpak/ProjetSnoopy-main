package model;
/**
 * create trapped bloc  
 */
public class Trapped extends Entity {
    /**
     * 
     * @param x
     * @param y
     * @param map
     * @see Type 
     * 
     */
    public Trapped(int x, int y, Map map){
        super(Type.BLOCK_TRAPPED, x, y, map);
    }

    public String toString(){
        return "3";
    }
}
