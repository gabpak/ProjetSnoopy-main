package model;


public class Snoopy extends Entity{
    public enum Direction{
        UP,
        DOWN, 
        LEFT, 
        RIGHT
    }

    private int m_health;

    public Direction m_dir;
    /**
     * 
     */
    public Snoopy(){
        super(Type.SNOOPY, -1, -1, null);
        this.m_health = 3;
        this.m_dir = Direction.UP;
    }
    /**
     * Constructor snoopy
     * @param x
     * @param y
     * @param map
     */
    public Snoopy(int x, int y, Map map){
        super(Type.SNOOPY, x, y, map);
        this.m_health = 3;
        this.m_dir = Direction.UP;
        
    }
    /**
     * if snoopy can move 
     * @param dir
     * @return
     * @see lookAt
     */
    public boolean canMove(Direction dir){
        this.m_dir = dir;
        Entity inFrontEntity = (Entity) this.lookAt(dir);
        if(inFrontEntity == null){
            return false;
        }
        Type infront = inFrontEntity.m_type;
        switch(dir){
            case UP:
                if(this.m_y > 0){
                    if(Parameters.BLOCK_PASSABLE.contains(infront)){
                        return true;
                    }
                }
                break;
            case DOWN:
                if(this.m_y < Parameters.MAP_SIZE_Y-1){
                    if(Parameters.BLOCK_PASSABLE.contains(infront)){
                        return true;
                    }
                }
                break;
            case LEFT:
                if(this.m_x > 0){
                    if(Parameters.BLOCK_PASSABLE.contains(infront)){
                        return true;
                    }
                }
                break;
            case RIGHT:
                if(this.m_x < Parameters.MAP_SIZE_X-1){
                    if(Parameters.BLOCK_PASSABLE.contains(infront)){
                        return true;
                    }
                }
                break;
        }
        return false;
    }
    /**
     * condition for snoopy to move
     * @param dir
     * @see lookAt
     */
    public boolean canBeMoved(Direction dir){
        Entity inFrontEntity = (Entity) this.lookAt(dir);
        if(inFrontEntity == null){
            return false;
        }
        Type infront = inFrontEntity.m_type;
        switch(dir){
            case UP:
                if(this.m_y > 0){
                    if(Parameters.BLOCK_PASSABLE.contains(infront)){
                        return true;
                    }
                }
                break;
            case DOWN:
                if(this.m_y < Parameters.MAP_SIZE_Y-1){
                    if(Parameters.BLOCK_PASSABLE.contains(infront)){
                        return true;
                    }
                }
                break;
            case LEFT:
                if(this.m_x > 0){
                    if(Parameters.BLOCK_PASSABLE.contains(infront)){
                        return true;
                    }
                }
                break;
            case RIGHT:
                if(this.m_x < Parameters.MAP_SIZE_X-1){
                    if(Parameters.BLOCK_PASSABLE.contains(infront)){
                        return true;
                    }
                }
                break;
        }
        return false;
    }
    /**
     * Snoopy's moves
     * @param dir
     * 
     */
    public void move(Direction dir){
        switch(dir){
            case UP:
                m_y--;
                break;
            case DOWN:
                m_y++;
                break;
            case LEFT:
                m_x--;
                break;
            case RIGHT:
                m_x++;
                break;
        }
    }
    /**
     * Snoopy life 
     * @param health
     */
    public void setHealth(int health){
        this.m_health = health;
    }
    /**
     * take a life out of snoopy
     */
    public void removeHealth(){
        this.m_health--;
    }
    /**
     * get health Snoopy 
     * @return health 
     */
    public int getHealth(){
        return this.m_health;
    }
    /**
     * get Direction Snoopy 
     * @return direction
     */
    public Direction getDirection(){
        return this.m_dir;
    }
    /**
     * Snoopy looks at the box in front of him
     * @return 
     * @see Entity 
     * @see Parameters 
     */
    public Entity lookAt(){
        switch(this.m_dir){
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
    /**
     * if Snoopy is on a convoyerbelt 
     * @return position 
     * @see Map
     * @see Type
     */
    public Boolean isOnConveyorBelt(){
        return (this.m_map.getMap()[m_x][m_y].m_type == Type.BLOCK_CONVEYOR_BELT);
    }
    /**
     * get a convoyerbelt
     *@return position convoyer belt 
     *@see Map
     * @see x
     * @see y
     * 
     */
    public Conveyor_Belt getConveyorBelt(){
        return (Conveyor_Belt) this.m_map.getMap()[m_x][m_y];
    }

    public String toString(){
        return "Snoopy/"+this.m_x+","+this.m_y;
    }
}
