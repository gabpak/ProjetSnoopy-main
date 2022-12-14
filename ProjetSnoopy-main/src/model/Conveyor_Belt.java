package model;

import model.Snoopy.Direction;



public class Conveyor_Belt extends Entity{
    public Direction m_direction;
    /**
     * Constructor for the conveyor belt 
     * @param x
     * @param y
     * @param dir
     * @param map
     * @see Type
     */
    public Conveyor_Belt(int x, int y,Direction dir , Map map){
        super(Type.BLOCK_CONVEYOR_BELT, x, y, map);
        this.m_direction = dir;
    }
    /**
     * Get the conveyor belt direction 
     * @return conveyor belt direction 
     */
    public Direction getDirection(){
        return this.m_direction;
    }
    /**
     * convoyer belt initial direction 
     * @param dir
     */
    public void setDirection(Direction dir){
        this.m_direction = dir;
    }

    public String toString(){
        switch(this.m_direction){
            case UP:
                return "6U";
            case DOWN:
                return "6D";
            case LEFT:
                return "6L";
            case RIGHT:
                return "6R";
            default:
                return "6";
        }
    }
}
