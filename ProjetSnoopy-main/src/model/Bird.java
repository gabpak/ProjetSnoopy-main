package model;


public class Bird extends Entity{
    /**
     * constructor for birds
     * @param x
     * @param y
     * @param map
     * @see Entity.Type 
     */
    public Bird(int x, int y, Map map){
        super(Entity.Type.BIRD, x, y, map);
    }

    public String toString(){
        return "9";
    }
}
