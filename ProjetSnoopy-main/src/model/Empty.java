package model;

public class Empty extends Entity {
    /**
     * Constructor for an empty box
     * @param x
     * @param y
     * @param map
     */
    public Empty(int x, int y, Map map){
        super(Type.EMPTY, x, y, map);
    }

    public String toString(){
        return "0";
    }
}
