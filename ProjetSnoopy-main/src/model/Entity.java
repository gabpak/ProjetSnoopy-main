package model;
/**
 * number assignments for elements
 */
public class Entity {
    public enum Type{ 
        EMPTY,                              // 0
        BLOCK_BREAKABLE,                    // 1
        BLOCK_PUSHABLE,                     // 2
        BLOCK_TRAPPED,                      // 3
        BLOCK_UNBREAKABLE,                  // 4
        BLOCK_VISIBLE_UNVISIBLE_SWITCH,     // 5
        BLOCK_CONVEYOR_BELT,                // 6
        BALL,                               // 7
        SNOOPY,                             // 8
        BIRD}                               // 9

    public Type m_type;
    protected int m_x;
    protected int m_y;
    protected Map m_map;
    /**
     * Constructor Entity 
     * @param type
     * @param map
     */
    public Entity(Type type, Map map){
        this.m_type = type;
        this.m_map = map;
    }
    /**
     * Constructor Entity 
     * @param type
     * @param x
     * @param y
     * @param map
     */
    public Entity(Type type, int x, int y, Map map){
        this.m_type = type;
        this.m_x = x;
        this.m_y = y;
        this.m_map = map;
    }
    /**
     * Initial type 
     * @param type
     */
    public void setType(Type type){
        this.m_type = type;
    }
    /**
     * get the type of element 
     * @return type element 
     */
    public Type getType(){
        return this.m_type;
    }
    /**
     * Set the x initial position 
     * @param x
     */
    public void setX(int x){
        this.m_x = x;
    }
    /**
     * get the x position 
     * @return x position 
     */
    public int getX(){
        return this.m_x;
    }
    /**
     * set the y initinal position 
     * @param y
     */
    public void setY(int y){
        this.m_y = y;
    }
    /**
     * get the y position 
     * @return y position 
     */
    public int getY(){
        return this.m_y;
    }
    /**
     * Set the xy position 
     * @param x
     * @param y
     */
    public void setXY(int x, int y){
        this.m_x = x;
        this.m_y = y;
    }
    /**
     * get the xy position 
     * @return xy position 
     */
    public int[] getXY(){
        int[] xy = {this.m_x, this.m_y};
        return xy;
    }
    /**
     * set the map 
     * @param map
     */
    public void setMap(Map map){
        this.m_map = map;
    }
    /**
     * get the map
     * @return map  
     */
    public Map getMap(){
        return this.m_map;
    }

    /**
     * 
     * @param dir
     * @return
     */
    public Entity lookAt(Snoopy.Direction dir){
        switch(dir){
            case UP:
                if(this.m_y > 0){
                    return this.m_map.getEntity(this.m_x, this.m_y-1);
                }
                break;
            case DOWN:
                if(this.m_y < Parameters.MAP_SIZE_Y-1){
                    return this.m_map.getEntity(this.m_x, this.m_y+1);
                }
                break;
            case LEFT:
                if(this.m_x > 0){
                    return this.m_map.getEntity(this.m_x-1, this.m_y);
                }
                break;
            case RIGHT:
                if(this.m_x < Parameters.MAP_SIZE_X-1){
                    return this.m_map.getEntity(this.m_x+1, this.m_y);
                }
                break;
        }
        return null;
    }
    
}