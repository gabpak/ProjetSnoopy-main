package AI;

import java.io.FileOutputStream;
import java.util.ArrayList;

import model.Snoopy.Direction;


public class Vertex {
    public enum PossibleAction{ 
        MOVE,ACTION,NONE}
    private Direction m_direction;
    private final int m_number;
    private final int m_depth;
    private int m_father;
    private final String m_mapState;
    private ArrayList<Vertex> m_neighbours = new ArrayList<>();
    private PossibleAction m_action;

    public Vertex(String map, int number, int depth) {
        this.m_mapState = map;
        this.m_number = number;
        this.m_depth = depth;
    }

    public Vertex(String map, int number, int depth, int father, PossibleAction action, Direction direction) {
        this.m_mapState = map;
        this.m_number = number;
        this.m_father = father;
        this.m_depth = depth;
        this.m_action = action;
        this.m_direction = direction;
    }

    public String getMapState(){
        return m_mapState;
    }

    public void addNeighbour(Vertex neighbour) {
        m_neighbours.add(neighbour);
    }

    /**
     * Save a mapState as a text file
     */
    public String saveToFile(){
        try {
            FileOutputStream fos = new FileOutputStream("src/ressource/AI/tmp.ai");
            fos.write(this.m_mapState.getBytes());
            fos.flush();
            fos.close();
        }
        catch (Exception e) {
            System.err.println("CRITICAL ERROR: " + e.getMessage());
        }
        return "src/ressource/AI/tmp.ai";
    }

    public int getDepth() {
        return m_depth;
    }

    public int getNumber() {
        return m_number;
    }

    public int getFather() {
        return m_father;
    }

    public PossibleAction getAction() {
        return m_action;
    }

    public Direction getDirection() {
        return m_direction;
    }

}
