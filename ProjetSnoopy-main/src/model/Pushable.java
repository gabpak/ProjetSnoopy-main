package model;

public class Pushable extends Entity{
    public Boolean hasBeenPushed;
    /**
     * create the pushable block
     * @param x
     * @param y
     * @param map
     */
    public Pushable(int x, int y, Map map){
        super(Type.BLOCK_PUSHABLE, x, y, map);
        hasBeenPushed = false;
    }
    /**
     * if the block was pushed
     * @param x
     * @param y
     * @param map
     * @param hasBeenPushed 
     * @see Type 
     */
    public Pushable(int x, int y, Map map, Boolean hasBeenPushed){
        super(Type.BLOCK_PUSHABLE, x, y, map);
        this.hasBeenPushed = hasBeenPushed;
    }
    /**
     * if snoopy can push the block
     * @param dir
     * @return
     * @see Parameters 
     * @see lookAt
     */
    public Boolean canBePushed(Snoopy.Direction dir){
        if(this.hasBeenPushed){
            return false;
        }
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

    public String toString(){
        if(this.hasBeenPushed){return "2I";} else{return "2";}
    }
}
