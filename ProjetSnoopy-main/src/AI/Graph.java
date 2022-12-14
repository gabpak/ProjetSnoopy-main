package AI;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import model.*;
import model.Snoopy.Direction;
import controler.*;

import AI.Vertex.PossibleAction;

public class Graph {
    private ArrayList<Vertex> m_listVertexes = new ArrayList<Vertex>();
    private ArrayList<Integer> m_listHash = new ArrayList<Integer>();
    private int m_nbWinningVertexes = 0;
    private int m_bestDepth = 100000;
    public Vertex m_endVertex = null;
    private Boolean m_ended = false;
    private int m_level;
    private Vertex m_startVertex;
    private Controler m_controler;

    public Graph(Controler controler, int level){
        this.m_level = level;
        this.m_controler = controler;
        m_controler.getSnoopy().setMap(new Map(m_controler));
        m_controler.getSnoopy().getMap().initMap(Parameters.PATH_LEVEL + Integer.toString(level) + ".lvl");
        m_startVertex = new Vertex(m_controler.getSnoopy().getMap().toString(false),0,0);
        System.out.println("Start Vertex :\n\r " + m_startVertex.getMapState());
        m_listVertexes.add(m_startVertex);
        makeVertex(m_startVertex);
        m_listHash.add(m_startVertex.getMapState().hashCode());
    }


    public Boolean makeVertex(Vertex firstVertex){
        PossibleAction actionPerformed = PossibleAction.NONE; 
        for(Direction dir : Direction.values()){
            if(this.m_ended){
                return true;
            }
            else if(this.m_endVertex == null){
                m_controler.getSnoopy().getMap().initMap(firstVertex.saveToFile());
                //System.out.println("Direction : " + dir);
                if(m_controler.getSnoopy().lookAt(dir) != null){
                    m_controler.getSnoopy().canMove(dir);
                    switch(m_controler.getSnoopy().lookAt(dir).m_type){
                        case EMPTY:
                            m_controler.getSnoopy().move(dir);
                            actionPerformed = PossibleAction.MOVE;
                            break;
        
                        case BLOCK_CONVEYOR_BELT:
                            m_controler.getSnoopy().move(dir);
                            actionPerformed = PossibleAction.MOVE;
                            break;
        
                        case BIRD:
                            m_controler.getSnoopy().getMap().setEntity(m_controler.getSnoopy().lookAt().getX(), m_controler.getSnoopy().lookAt().getY(), new Empty(m_controler.getSnoopy().lookAt().getX(), m_controler.getSnoopy().lookAt().getY(), m_controler.getSnoopy().getMap()));
                            actionPerformed = PossibleAction.ACTION;
                            break;
        
                        case BLOCK_PUSHABLE:
                            actionPerformed = PossibleAction.ACTION;
                            if(((Pushable) m_controler.getSnoopy().lookAt()).canBePushed(m_controler.getSnoopy().getDirection())){
                                int[] SnoopyOldPosition = m_controler.getSnoopy().getMap().getSnoopyPosition();
                                switch(dir){
                                    case DOWN:
                                        m_controler.getSnoopy().move(dir);
                                        m_controler.getSnoopy().getMap().setEntity(SnoopyOldPosition[0], SnoopyOldPosition[1]+2, new Pushable(SnoopyOldPosition[0], SnoopyOldPosition[1]+2, m_controler.getSnoopy().getMap(), true));
                                        m_controler.getSnoopy().getMap().setEntity(SnoopyOldPosition[0], SnoopyOldPosition[1]+1, new Empty(SnoopyOldPosition[0], SnoopyOldPosition[1]+1, null));
                                        break;
                                    case LEFT:
                                        m_controler.getSnoopy().move(dir);
                                        m_controler.getSnoopy().getMap().setEntity(SnoopyOldPosition[0]-2, SnoopyOldPosition[1], new Pushable(SnoopyOldPosition[0]-2, SnoopyOldPosition[1], m_controler.getSnoopy().getMap(), true));
                                        m_controler.getSnoopy().getMap().setEntity(SnoopyOldPosition[0]-1, SnoopyOldPosition[1], new Empty(SnoopyOldPosition[0]-1, SnoopyOldPosition[1], null));
                                        break;
                                    case RIGHT:
                                        m_controler.getSnoopy().move(dir);
                                        m_controler.getSnoopy().getMap().setEntity(SnoopyOldPosition[0]+2, SnoopyOldPosition[1], new Pushable(SnoopyOldPosition[0]+2, SnoopyOldPosition[1], m_controler.getSnoopy().getMap(), true));
                                        m_controler.getSnoopy().getMap().setEntity(SnoopyOldPosition[0]+1, SnoopyOldPosition[1], new Empty(SnoopyOldPosition[0]+1, SnoopyOldPosition[1], null));
                                        break;
                                    case UP:
                                        m_controler.getSnoopy().move(m_controler.getSnoopy().m_dir);
                                        m_controler.getSnoopy().getMap().setEntity(SnoopyOldPosition[0], SnoopyOldPosition[1]-2, new Pushable(SnoopyOldPosition[0], SnoopyOldPosition[1]-2, m_controler.getSnoopy().getMap(), true));
                                        m_controler.getSnoopy().getMap().setEntity(SnoopyOldPosition[0], SnoopyOldPosition[1]-1, new Empty(SnoopyOldPosition[0], SnoopyOldPosition[1], null));
                                        break;
                                    default:
                                        actionPerformed = PossibleAction.NONE;
                                        break;
                                }
                            }
                            break;

                        case BLOCK_BREAKABLE:
                            actionPerformed = PossibleAction.ACTION;
                            m_controler.getSnoopy().getMap().setEntity(m_controler.getSnoopy().lookAt().getX(), m_controler.getSnoopy().lookAt().getY(), new Empty(m_controler.getSnoopy().lookAt().getX(), m_controler.getSnoopy().lookAt().getY(), m_controler.getSnoopy().getMap()));
                            break;

                        default:
                            break;
                    }
                }
                if(m_listHash.contains(m_controler.getSnoopy().getMap().toString(false).hashCode())){
                    //System.out.println("Vertex deja existant");
                    System.out.println("Level :" + m_level +" | " + m_nbWinningVertexes + " / " + m_listVertexes.size() + "|| Best Depth: " + m_bestDepth + " || Ended: " + m_ended);
                    int index = m_listHash.indexOf(m_controler.getSnoopy().getMap().toString(false).hashCode());
                    firstVertex.addNeighbour(m_listVertexes.get(index));
                }
                else{
                    Vertex vertex = new Vertex(m_controler.getSnoopy().getMap().toString(false), m_listVertexes.size(), firstVertex.getDepth()+1, firstVertex.getNumber(), actionPerformed, dir);
                    //System.out.println("New Vertex Created:\n\r " + vertex.getMapState());
                    m_listVertexes.add(vertex);
                    //System.out.println("New Vertex added");
                    //System.out.println("New Verted added to listVertexes:" + m_listVertexes.size());
                    if(m_controler.hasWin(m_controler.getSnoopy().getMap().getBirdsPosition())){
                        System.out.println("=========================WIN=========================");
                        if(m_bestDepth>vertex.getDepth()){m_bestDepth=vertex.getDepth();}
                        m_nbWinningVertexes++;
                        m_listHash.add(vertex.getMapState().hashCode());
                        firstVertex.addNeighbour(vertex);
                        m_endVertex = vertex;
                        return true;
                    }
                    else{
                        if(!this.m_ended){
                            m_listHash.add(vertex.getMapState().hashCode());
                            firstVertex.addNeighbour(vertex);
                            makeVertex(vertex);
                        }
                    }
                    System.out.println("Level :" + m_level +" | " + m_nbWinningVertexes + " / " + m_listVertexes.size() + "|| Best Depth: " + m_bestDepth + " || Ended: " + m_ended);
                }
            }
        }
        return null;

    }

    public void course(Vertex vertex){
        ArrayList<ArrayList<Object>> m_listActions = new ArrayList<ArrayList<Object>>();

        while(vertex.getNumber() != 0){
            ArrayList<Object> m_listAction = new ArrayList<Object>();
            m_listAction.add(vertex.getAction());
            m_listAction.add(vertex.getDirection());
            m_listActions.add(m_listAction);
            vertex = this.m_listVertexes.get(vertex.getFather());
        }
        Collections.reverse(m_listActions);

        try {
            FileOutputStream fos = new FileOutputStream("src/ressource/AI/solutions/" + m_level +"_solution.txt");
            for(ArrayList<Object> action : m_listActions){
                String data = (PossibleAction) action.get(0) + "," + (Direction) action.get(1) + "\n\r";
                System.out.println("TEST: " + data);
                fos.write(data.getBytes());
            }
            fos.flush();
            fos.close();
        }
        catch (Exception e) {
            System.err.println("CRITICAL ERROR: " + e.getMessage());
        }
        

    }

    public Vertex getEndVertex(){
        return this.m_endVertex;
    }
}
