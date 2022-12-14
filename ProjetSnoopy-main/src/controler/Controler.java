package controler;
import view.*;
import model.*;
import model.Parameters;
import model.Snoopy.Direction;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Timer;

import AI.Vertex.PossibleAction;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Controler{

    private Snoopy m_snoopy;
    private Level m_level;

    private int m_score;
    private Boolean paused;

    private Double m_time;
    private Timer main_timer;

    private Boolean AI = false;  
    private Scanner scannerAI;

    public MainWindows mainWindows;

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        new Controler();
    }
    /**
     * Constructeur controller 
     * @see Snoopy
     * @see MainWindows
     */
    public Controler(){
        paused = false;
        m_score = 0;
        m_time = 60.0;
        m_snoopy = new Snoopy();
        mainWindows = new MainWindows(); // JFrame
        mainWindows.putContent(new MainMenu(mainWindows, this)); // JPanel
    }
    
    /**
     * Set the level
     * @param level
     */
    public void setLevel(Level level){
        m_level = level;
    }

    /**
     * Get the level
     * @return Level
     */
    public Level getLevel(){
        return m_level;
    }

    /**
     * Get the snoopy 
     * @return Snoopy 
     */
    public Snoopy getSnoopy(){
        return m_snoopy;
    }


    /**
     * if you press key up, snoopy move up on the map if he can
     * @see canMove
     * @see lookAt
     * @see gameoVER
     * @see Snoopy.Direction
     * @see move
     */
    public void KEY_UP(){
        if(!paused){
            if(m_snoopy.canMove(Snoopy.Direction.UP)){
                switch(m_snoopy.lookAt().m_type){
                    case BLOCK_TRAPPED:
                        changeStateLevel(0);
                        break;
                    default:
                        m_snoopy.move(Snoopy.Direction.UP);
                        break;
                }
                
            }
        }
    }
    /**
     * if you press key Down, snoopy move Down on the map if he can
     * @see canMove
     * @see lookAt
     * @see gameoVER
     * @see Snoopy.Direction
     * @see move
     */
    public void KEY_DOWN(){
        if(!paused){
            if(m_snoopy.canMove(Snoopy.Direction.DOWN)){
                switch(m_snoopy.lookAt().m_type){
                    case BLOCK_TRAPPED:
                        changeStateLevel(0);
                        break;
                    default:
                        m_snoopy.move(Snoopy.Direction.DOWN);
                        break;
                }
                
                
            }
        }
    }
    /**
     * if you press key Left, snoopy move left on the map if he can
     * @see canMove
     * @see lookAt
     * @see gameoVER
     * @see Snoopy.Direction
     * @see move
     */
    public void KEY_LEFT(){
        if(!paused){
            if(m_snoopy.canMove(Snoopy.Direction.LEFT)){
                switch(m_snoopy.lookAt().m_type){
                    case BLOCK_TRAPPED:
                        changeStateLevel(0);
                        break;
                    default:
                        m_snoopy.move(Snoopy.Direction.LEFT);
                        break;
                }
                
            }
        }
    }
    /**
     * if you press key Right, snoopy right on the map if he can
     * @see canMove
     * @see lookAt
     * @see gameoVER
     * @see Snoopy.Direction
     * @see move
     */
    public void KEY_RIGHT(){
        if(!paused){
            if(m_snoopy.canMove(Snoopy.Direction.RIGHT)){
                switch(m_snoopy.lookAt().m_type){
                    case BLOCK_TRAPPED:
                        changeStateLevel(0);
                        break;
                    default:
                        m_snoopy.move(Snoopy.Direction.RIGHT);
                        break;
                }
                
            }
        }
    }

    public void KEY_MOVE(Direction dir){
        switch(dir){
            case UP:
                KEY_UP();
                break;
            case DOWN:
                KEY_DOWN();
                break;
            case LEFT:
                KEY_LEFT();
                break;
            case RIGHT:
                KEY_RIGHT();
                break;
        }
    }

    /**
     * if you press the S key, you save
     * @see saveGame
     */
    public void KEY_S(){
        saveGame();
    }
    /**
     * if you press the P key, you pause the game 
     * @see managePause 
     */
    public void KEY_P(){
        managePause();
    }
    /**
     * if you press the ESPACE key
     * @see gameOver 
     */
    public void KEY_ESCAPE(){
        changeStateLevel(4); // 4 = Menu
    }
    /**
     * space key interaction with the game,
     * to move a pushable block
     * to break a breakable block
     * to catch a bird
     * @see lookAt
     * @see Map
     * @see Entity 
     * @see canBePushed 
     * @see move
     * @see Empty
     * @see X
     * @see Y
     * @see Pushable 
     */
    public void KEY_SPACE(){
        //System.out.println(this.m_snoopy.getMap());
        if(!paused && this.m_snoopy.lookAt() != null){
            switch(this.m_snoopy.lookAt().m_type){
                case BLOCK_BREAKABLE:
                    this.m_snoopy.getMap().setEntity(this.m_snoopy.lookAt().getX(), this.m_snoopy.lookAt().getY(), new Empty(this.m_snoopy.lookAt().getX(), this.m_snoopy.lookAt().getY(), m_snoopy.getMap()));
                    break;
                case BLOCK_PUSHABLE:
                    if(((Pushable)this.m_snoopy.lookAt()).canBePushed(m_snoopy.getDirection())){
                        int[] SnoopyOldPosition = m_snoopy.getMap().getSnoopyPosition();
                        switch(this.m_snoopy.m_dir){
                            case DOWN:
                                m_snoopy.move(m_snoopy.m_dir);
                                m_snoopy.getMap().setEntity(SnoopyOldPosition[0], SnoopyOldPosition[1]+2, new Pushable(SnoopyOldPosition[0], SnoopyOldPosition[1]+2, m_snoopy.getMap(), true));
                                m_snoopy.getMap().setEntity(SnoopyOldPosition[0], SnoopyOldPosition[1]+1, new Empty(SnoopyOldPosition[0], SnoopyOldPosition[1]+1, null));
                                break;
                            case LEFT:
                                m_snoopy.move(m_snoopy.m_dir);
                                m_snoopy.getMap().setEntity(SnoopyOldPosition[0]-2, SnoopyOldPosition[1], new Pushable(SnoopyOldPosition[0]-2, SnoopyOldPosition[1], m_snoopy.getMap(), true));
                                m_snoopy.getMap().setEntity(SnoopyOldPosition[0]-1, SnoopyOldPosition[1], new Empty(SnoopyOldPosition[0]-1, SnoopyOldPosition[1], null));
                                break;
                            case RIGHT:
                                m_snoopy.move(m_snoopy.m_dir);
                                m_snoopy.getMap().setEntity(SnoopyOldPosition[0]+2, SnoopyOldPosition[1], new Pushable(SnoopyOldPosition[0]+2, SnoopyOldPosition[1], m_snoopy.getMap(), true));
                                m_snoopy.getMap().setEntity(SnoopyOldPosition[0]+1, SnoopyOldPosition[1], new Empty(SnoopyOldPosition[0]+1, SnoopyOldPosition[1], null));
                                break;
                            case UP:
                                m_snoopy.move(m_snoopy.m_dir);
                                m_snoopy.getMap().setEntity(SnoopyOldPosition[0], SnoopyOldPosition[1]-2, new Pushable(SnoopyOldPosition[0], SnoopyOldPosition[1]-2, m_snoopy.getMap(), true));
                                m_snoopy.getMap().setEntity(SnoopyOldPosition[0], SnoopyOldPosition[1]-1, new Empty(SnoopyOldPosition[0], SnoopyOldPosition[1], null));
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case BIRD:
                    //TODO Pause the game
                    //TODO Launch Animation
                    this.m_snoopy.getMap().setEntity(this.m_snoopy.lookAt().getX(), this.m_snoopy.lookAt().getY(), new Empty(this.m_snoopy.lookAt().getX(), this.m_snoopy.lookAt().getY(), m_snoopy.getMap()));
                    break;
                    
                default:
                    break;
            }
        }
    }
    /**
     * Create score 
     * @param score
     */
    public void setScore(int score){
        this.m_score = score;
    }
    /**
     * get the score 
     * @return score 
     */
    public int getScore(){
        return this.m_score;
    }
    /**
     * add value to score
     * @param score
     */
    public void addScore(int score){
        this.m_score += score;
    }
    /**
     * Set the time 
     * @param time
     */
    public void setTime(Double time){
        this.m_time = time;
    }
    /**
     * get the time 
     * @return
     */
    public Double getTime(){
        return this.m_time;
    }
    /**
     * Start the timer
     */
    public void startTimer(){
        main_timer = new Timer(10, actionListener);
        main_timer.setInitialDelay(0);
        main_timer.start();
    }

    /**
     * Timer while the game is running (tick)
     */
    private ActionListener actionListener = new ActionListener() {
        /**
         * snoopy's interaction with balls and conveyor belts
         * @param e
         * @see gameOver
         * @see balls
         * @see Move 
         * @see Map
         * @see X
         * @see Y
         * @see canBeMove 
         * @see isOnConveyorBelt
         * @see Conveyor_Belt
         * @see Direction 
         * @see Move
         * @see Parameters 
         * @see Ball
         * @see nextLevel
         * @see BirdsPosition
         * @see CurrentLevel
         * @see size
         * @see Direction 
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!AI){m_time -= 0.1;}
            if(m_snoopy.getHealth() <= 0){
                changeStateLevel(1);
            }
            if(m_time <= 0){
                changeStateLevel(0);
            }
            if(hasWin(m_snoopy.getMap().getBirdsPosition())){
                if(m_level.getCurrentLevel() == Parameters.nb_level){
                    changeStateLevel(2); 
                }
                else{
                    nextLevel();
                }
                
            }

            //Ball movement
            try{
                if(m_snoopy.getMap().getBalls() != null){
                    for(int i = 0; i < m_snoopy.getMap().getBalls().size(); i++){
                        m_snoopy.getMap().getBalls().get(i).move();
                        if(Math.abs(m_snoopy.getMap().getBalls().get(i).getBallX() - m_snoopy.getX())<0.5 && Math.abs(m_snoopy.getMap().getBalls().get(i).getBallY() - m_snoopy.getY()) <0.5 && !AI){
                            changeStateLevel(3);
                        }
                    }
                }
            }
            catch(Exception ex){
                System.out.println("Error while moving the ball");
            }
            try{
                //Conveyor belt movement
                while(m_snoopy.isOnConveyorBelt()){
                    Conveyor_Belt cB =m_snoopy.getConveyorBelt();
                    if(m_snoopy.canBeMoved(cB.getDirection())){
                        m_snoopy.move(cB.getDirection());
                    }
                    break;
                }
            }
            catch(Exception ex){
                System.out.println("Error while moving Snoopy on cB");
            }
            
            if(AI){
                try{
                    playAI();
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            
        }
    };

    public Boolean getAI(){
        return AI;
    }

    /**
     * add the score in the file scores.txt
     * @param score
     * @throws IOException
     * @see Parameters 
     * @see createNewFile
     * 
     */
    private void addScoreInFile(Integer score) throws IOException{
        try{
            File file = new File(Parameters.PATH_SCORE + "scores.txt");
            if(!file.exists()){
                file.createNewFile(); 
            }

            // Read the file
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            String[] scores = new String[3];
            int i = 0;
            while((line = br.readLine()) != null){
                scores[i] = line;
                i++;
            }
            br.close();
            Arrays.sort(scores);

            if(score > Integer.parseInt(scores[0])){
                    // Write the score only if he is higher than the lowest score
                String fileMetaData = score.toString();
                fileMetaData += "\n";
                fileMetaData += scores[2];
                fileMetaData += "\n";
                fileMetaData += scores[1];

                FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(fileMetaData);
                
                bw.flush();
                bw.close();
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setAI(Boolean AI){
        this.AI = AI;
    }
    /**
     * game over function, if snoopy still has lives he removes a life
     *  otherwise he calculates the score and adds it to the file
     * @param reason
     * @see removeHealth
     * @see start
     * @see restart
     * @see stop
     * @see addScoreInFile 
     * @see Health 
     * @see MainMenu
     * @see GameOverMenu
     * @see removeKeyListener
     * @see Snoopy
     * @see GameOverMenu
     * @see MainMenu
     * @see Snoopy 
     */
    public void changeStateLevel(int reason){
        switch(reason){
            case 0: // LOSE : Time is over
                main_timer.stop();
                m_snoopy.removeHealth();
                try{ // Adding the score
                    addScoreInFile(this.m_score);
                }
                catch(IOException e){
                    e.printStackTrace();
                }

                restart();
                main_timer.start();
                break;

            case 1: // LOSE : No more lives
                main_timer.stop();
                m_score += 100*(60-m_time);
                // adding the score
                try{
                    addScoreInFile(this.m_score);
                }
                catch(IOException e){
                    e.printStackTrace();
                }
                m_snoopy.setHealth(3);
                mainWindows.removeKeyListener(m_level); // Kill the keyListener of the current level
                mainWindows.putContent(new GameOverMenu(mainWindows, this, this.m_score)); // Test

                this.m_score = 0;
                break;

            case 2: // WIN : All birds are dead and last lvl reached
                main_timer.stop();
                m_score += 100*(60-m_time);
                System.out.println("Fin de La Partie");
                mainWindows.removeKeyListener(m_level); // Kill the keyListener of the current level

                // Adding the score
                try{
                    addScoreInFile(this.m_score);
                }
                catch(IOException e){
                    e.printStackTrace();
                }

                mainWindows.putContent(new GameWinMenu(mainWindows, this, this.m_score)); // Draw the GameWinMenu

                this.m_score = 0;
                m_snoopy = new Snoopy();
                break;
            case 3: // Hit by a ball
                main_timer.stop();
                m_snoopy.removeHealth();
                restart();
                main_timer.start();
                break;

            case 4: // Exit on escape key
                main_timer.stop();
                mainWindows.removeKeyListener(m_level); // Kill the keyListener of the current level
                mainWindows.putContent(new GameOverMenu(mainWindows, this, 0));
                break;
        }
    }
    /**
     * 
     * @param birdsPositionArray
     * @return birdsposition 
     * @see size 
     */
    public Boolean hasWin(ArrayList<int[]> birdsPositionArray){
        return (birdsPositionArray.size() == 0);
    }
    /**
     * function to restart the timer
     * @see Map
     * @see CurrentLevel
     */
    public void restart(){
        m_time = 60.0;
        m_level.getMap().initMap(Parameters.PATH_LEVEL + Integer.toString(m_level.getCurrentLevel()) + ".lvl");
    }
    /**
     * whether or not to pause the timer
     * @see start
     * @see stop 
     */
    public void managePause(){
        if(paused){
            main_timer.start();
            paused = false;
        }
        else{
            main_timer.stop();
            paused = true;
        }
    }
    /**
     * Load next level
     * @see map
     * @see CurrentLevel
     * @see Time
     */
    public void nextLevel(){ 
        this.m_score += 100*(60-m_time);
        setTime(60.0);
        m_level.setCurrentLevel(m_level.getCurrentLevel() + 1);
        m_level.getMap().initMap(Parameters.PATH_LEVEL + Integer.toString(m_level.getCurrentLevel()) + ".lvl");
    }

    public void newGame(){
        setTime(60.0);
        m_snoopy.setHealth(3);
        m_score = 0;
    }

    public void setSettings(ArrayList<String> settings){
        setScore(Integer.parseInt(settings.get(2)));
        getSnoopy().setHealth(Integer.parseInt(settings.get(3)));
        setTime(Double.parseDouble(settings.get(4)));
    }

    /**
     * Save a level to a file
     */
    public void saveGame(){
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            Date date = new Date();
            String fileMetaData = "#,"+java.time.LocalDate.now()+","+ m_time.intValue()+","+m_snoopy.getHealth()+","+m_score+","+m_level.getCurrentLevel() + "\n\r";
            String fileData = m_level.getMap().toString();
            System.out.println(fileData);

            FileOutputStream fos = new FileOutputStream("src/ressource/save/"+format.format(date)+"-"+m_level.getCurrentLevel()+".save");
            fos.write(fileMetaData.getBytes());
            fos.write(fileData.getBytes());
            fos.flush();
            fos.close();
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    public MainWindows getMainWindows(){
        return mainWindows;
    }

    public void initAI(int level){
        try{
            scannerAI = new Scanner(new FileInputStream("src/ressource/AI/solutions/"+level+"_solution.txt"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void playAI(){
        try{
            if(scannerAI.hasNextLine()){
                String line = scannerAI.nextLine();
                if(!line.equals("")){
                    String[] moves = line.split(",");
                    System.out.println(moves.length);
                    System.out.println(moves[0]);
                    System.out.println(moves[1]);
                    Direction direction;
                    PossibleAction action;
                    switch(moves[1]){
                        case "UP":
                            direction = Direction.UP;
                            break;
                        case "DOWN":
                            direction = Direction.DOWN;
                            break;
                        case "LEFT":
                            direction = Direction.LEFT;
                            break;
                        case "RIGHT":
                            direction = Direction.RIGHT;
                            break;
                        default:
                            direction = Direction.UP;
                            break;
                    }
                    switch(moves[0]){
                        case "MOVE":
                            action = PossibleAction.MOVE;
                            break;
                        case "ACTION":
                            action = PossibleAction.ACTION;
                            break;
                        default:
                            action = PossibleAction.NONE;
                            break;
                    }
                    m_snoopy.canMove(direction);
                    if(action == PossibleAction.ACTION){
                        KEY_SPACE();
                        System.out.println("AI ACTION");
                    }
                    else if(action == PossibleAction.MOVE){
                        KEY_MOVE(direction);
                        System.out.println("AI MOVE");
                    }
                    else{
                        System.out.println("AI NONE");
                    }
                }
            }
            else{
                initAI(m_level.getCurrentLevel());
            }
        }
        catch(Exception ec){
            System.console().printf("Error while reading solucefile\n" + ec.getMessage());
        }
    }
}
