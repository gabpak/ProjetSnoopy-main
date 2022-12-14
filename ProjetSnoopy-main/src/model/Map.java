package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import controler.Controler;
import model.Snoopy.Direction;

public class Map {
    public Entity[][] m_map;
    public Controler m_controler;
    public ArrayList<Ball> m_balls;
    /**
     * Constructor map 
     *@param controler 
     *@see Parameters 
     */
    public Map(Controler controler){
        this.m_map = new Entity[Parameters.MAP_SIZE_X][Parameters.MAP_SIZE_Y];
        this.m_controler = controler;
        this.m_balls = new ArrayList<Ball>();

        // INITIALISATION OF THE MAP WITH EMPTY CASES
        for(int i = 0; i < Parameters.MAP_SIZE_X; i++){
            for(int j = 0; j < Parameters.MAP_SIZE_Y; j++){
                this.m_map[i][j] = new Entity(Entity.Type.EMPTY, i, j, this);
            }
        }
    }
    /**
     * create a new map according to the elements
     * @param level
     * @see Parameters 
     * @see Empty 
     * @see Pushable 
     * @see Trapped 
     * @see Unbreakable 
     * @see Breakable 
     * @see Convoyor_Belt
     * @see Bird
     * @see hasNextLine 
     * @see nextLine 
     * @see startsWidth 
     * @see Snoopy
     * @see Map 
     * @see X
     * @see Y
     * @see Ball 
     * 
     */
    public void initMap(String level){
       try{
        // Le fichier du niveau/sauvegarde
        FileInputStream levelFile = new FileInputStream(level);
        
        //FileInputStream levelFile = new FileInputStream(level);   
        Scanner scanner = new Scanner(levelFile);
        
        int y = 0;
        this.m_balls = new ArrayList<Ball>();
        while(scanner.hasNextLine())
        {
            String lineFile = scanner.nextLine(); //On lit la ligne
            
            String[] lineData = null;
            String[] lineDataPackage = null;
            if(lineFile.startsWith("D")){
                lineData = lineFile.split(",");
                for(int iter = 1; iter< lineData.length ; iter++){
                    int x = iter-1;
                    switch (lineData[iter]) {
                        case "0":
                            this.m_map[x][y] = new Empty(x, y, this);
                            break;
                        case "1":
                            this.m_map[x][y] = new Breakable(x, y, this);
                            break;
                        case "2": // Pushable Active
                            this.m_map[x][y] = new Pushable(x, y, this, false);
                            break;
                        case "2I": // Pushable Inactive
                            this.m_map[x][y] = new Pushable(x, y, this, true);
                            break;
                        case "3":
                            this.m_map[x][y] = new Trapped(x, y, this);
                            break;
                        case "4":
                            this.m_map[x][y] = new Unbreakable(x, y, this);
                            break;
                        // Previously the ball element, but removed in order to implement it in a different way
                        /*
                        case "5": 
                            plateau[numeroLigne][y] = new Element(numeroLigne, i, this);
                            break;
                        */
                        case "6U":
                            this.m_map[x][y] = new Conveyor_Belt(x, y, Direction.UP, this);
                            break;
                        case "6D":
                            this.m_map[x][y] = new Conveyor_Belt(x, y, Direction.DOWN, this);
                            break;
                        case "6L":
                            this.m_map[x][y] = new Conveyor_Belt(x, y, Direction.LEFT, this);
                            break;
                        case "6R":
                            this.m_map[x][y] = new Conveyor_Belt(x, y, Direction.RIGHT, this);
                            break;
                        case "7":
                            //this.m_map[x][y] = new Ball(x, y, this);
                            break; 
                        case "8":
                            m_controler.getSnoopy().setX(x);
                            m_controler.getSnoopy().setY(y);
                            m_controler.getSnoopy().setMap(this); 
                            this.m_map[x][y] = new Empty(x, y, this);
                            break;
                        case "9":
                            this.m_map[x][y] = new Bird(x, y, this);
                            break; 
                        default:
                            break;    
                        }
                }
                y++; //On incrémente le numéro de ligne
            }
            else if(lineFile.startsWith("Balls")){
                lineDataPackage = lineFile.split("/");
                String[] data = null;
                for(int i =1; i<lineDataPackage.length; i++){
                    data = lineDataPackage[i].split(",");
                    Ball.DirectionDiag dir;
                    switch(data[2]){
                        case "TOP_LEFT":
                            dir = Ball.DirectionDiag.TOP_LEFT;
                            break;
                        case "TOP_RIGHT":
                            dir = Ball.DirectionDiag.TOP_RIGHT;
                            break;
                        case "BOTTOM_LEFT":
                            dir = Ball.DirectionDiag.BOTTOM_LEFT;
                            break;
                        case "BOTTOM_RIGHT":
                            dir = Ball.DirectionDiag.BOTTOM_RIGHT;
                            break;
                        default:
                            dir = Ball.DirectionDiag.TOP_LEFT;
                            break;
                    }
                    this.m_balls.add(new Ball(Double.parseDouble(data[0]), Double.parseDouble(data[1]), dir , this));
                }
            }

            else if(lineFile.startsWith("Snoopy")){
                lineDataPackage = lineFile.split("/");
                String[] data = null;
                data = lineDataPackage[1].split(",");
                m_controler.getSnoopy().setX(Integer.parseInt(data[0]));
                m_controler.getSnoopy().setY(Integer.parseInt(data[1]));
                m_controler.getSnoopy().setMap(this);
                
            }
        }
        scanner.close();
        
    }

        catch(IOException e){
        e.printStackTrace();
        }
    }
    /**
     * get Entity 
     * @param x
     * @param y
     * @return
     */
    public Entity getEntity(int x, int y){
        return this.m_map[x][y];
    }
    /**
     * Set Entity 
     * @param x
     * @param y
     * @param entity
     */
    public void setEntity(int x,int y,Entity entity){
        this.m_map[x][y] = entity;
    }
    /**
     * get Map 
     * @return m_map
     */
    public Entity[][] getMap(){
        return m_map;
    }
    /**
     * get Snoopy position 
     * @see Snoopy
     * @see X
     * @see Y
     * @return position 
     */
    public int[] getSnoopyPosition(){
        int[] position = new int[2];
        position[0] = m_controler.getSnoopy().getX();
        position[1] = m_controler.getSnoopy().getY();
        return position;
    }
    /**
     * get BirdsPosition 
     * @see Parameters 
     * @see Type 
     * @see Entity.Type 
     * @return birdsPositions 
     */
    public ArrayList<int[]> getBirdsPosition(){
        ArrayList<int[]> birdsPosition = new ArrayList<int[]>();
        for(int i = 0; i < Parameters.MAP_SIZE_X; i++){
            for(int j = 0; j < Parameters.MAP_SIZE_Y; j++){
                if(this.m_map[i][j].getType() == Entity.Type.BIRD){
                    birdsPosition.add(new int[]{i, j});
                }
            }
        }
        return birdsPosition;
    }
    /**
     * get balls 
     * @return m_balls 
     */
    public ArrayList<Ball> getBalls(){
        return m_balls;
    }
    /**
     * 
     * @param balls
     */
    public void setBalls(ArrayList<Ball> balls){
        m_balls = balls;
    }

    public String toString(){
        String map = "";
        for(int j = 0; j < Parameters.MAP_SIZE_Y; j++){
            map+="D,";
            for(int i = 0; i < Parameters.MAP_SIZE_X; i++){
                map += this.m_map[i][j].toString();
                map += ",";
            }
            map+="\n\r";
        }
        map+="\n\r";
        map += m_controler.getSnoopy().toString();
        map+="\n\r";
        map+="Balls/";
        for(int i = 0; i < m_balls.size(); i++){
            map += m_balls.get(i).toString();
            map += "/";
        }
        return map;
    }

    public String toString(Boolean includeBalls){
        String map = "";
        for(int j = 0; j < Parameters.MAP_SIZE_Y; j++){
            map+="D,";
            for(int i = 0; i < Parameters.MAP_SIZE_X; i++){
                map += this.m_map[i][j].toString();
                map += ",";
            }
            map+="\n\r";
        }
        map+="\n\r";
        map += m_controler.getSnoopy().toString();
        if(includeBalls){
            map+="\n\r";
            map+="Balls/";
            for(int i = 0; i < m_balls.size(); i++){
                map += m_balls.get(i).toString();
                map += "/";
            }
        }
        return map;
    }

}
