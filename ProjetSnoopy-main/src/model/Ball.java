package model;

public class Ball extends Entity {
    Double m_x;
    Double m_y;
    DirectionDiag m_dir;
    Double m_speed = 0.2;
    /**
     * 
     * @param x
     * @param y
     * @param dir
     * @param map
     */
    public Ball(Double x, Double y,DirectionDiag dir, Map map){
        super(Type.BALL, map);
        this.m_x = x;
        this.m_y = y;
        this.m_dir = dir; // default direction is top left. To modify
    }
    /**
     *   create the movements of the ball
     * @see DirectionDiag
     */
    public void move(){

        // Verification
        if(this.m_x < 0 && this.m_dir == DirectionDiag.TOP_LEFT){
            this.m_dir = DirectionDiag.TOP_RIGHT;
        }
        if(this.m_x < 0 && this.m_dir == DirectionDiag.BOTTOM_LEFT){
            this.m_dir = DirectionDiag.BOTTOM_RIGHT;
        }
        if(this.m_x > Parameters.MAP_SIZE_X-1 && this.m_dir == DirectionDiag.TOP_RIGHT){
            this.m_dir = DirectionDiag.TOP_LEFT;
        }
        if(this.m_x > Parameters.MAP_SIZE_X-1 && this.m_dir == DirectionDiag.BOTTOM_RIGHT){
            this.m_dir = DirectionDiag.BOTTOM_LEFT;
        }
        if(this.m_y < 0 && this.m_dir == DirectionDiag.TOP_LEFT){
            this.m_dir = DirectionDiag.BOTTOM_LEFT;
        }
        if(this.m_y < 0 && this.m_dir == DirectionDiag.TOP_RIGHT){
            this.m_dir = DirectionDiag.BOTTOM_RIGHT;
        }
        if(this.m_y > Parameters.MAP_SIZE_Y-1 && this.m_dir == DirectionDiag.BOTTOM_LEFT){
            this.m_dir = DirectionDiag.TOP_LEFT;
        }
        if(this.m_y > Parameters.MAP_SIZE_Y-1 && this.m_dir == DirectionDiag.BOTTOM_RIGHT){
            this.m_dir = DirectionDiag.TOP_RIGHT;
        }

        // Move the ball

        switch(this.m_dir){
            case TOP_LEFT:
                this.m_x-=m_speed; this.m_y-=m_speed;
                break;
            case TOP_RIGHT:
                this.m_x+=m_speed; this.m_y-=m_speed;
                break;
            case BOTTOM_LEFT:
                this.m_x-=m_speed; this.m_y+=m_speed;
                break;
            case BOTTOM_RIGHT:
                this.m_x+=m_speed; this.m_y+=m_speed;
                break;
            default:
            // Do nothing
            break;
        }
    }
    /**
     * initial position of the ball in x
     * @param x
     */
    public void setX(Double x){
        this.m_x = x;
    }
    /**
     * initial position of the ball in y
     * @param y
     */
    public void setY(Double y){
        this.m_y = y;
    }
    /**
     * get ball position 
     * @return x-position 
     */
    public Double getBallX(){
        return this.m_x;
    }
    /**
     * get ball position
     * @return y-position
     */
    public Double getBallY(){
        return this.m_y;
    }
    enum DirectionDiag{
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    public String toString(){
        return this.m_x.intValue()+","+this.m_y.intValue()+","+this.m_dir;
    }
}
