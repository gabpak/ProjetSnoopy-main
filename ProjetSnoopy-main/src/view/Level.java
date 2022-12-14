package view;

import model.*;
import controler.*;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Level extends JPanel implements KeyListener{
    private MainWindows m_parent;
    private int m_currentLevel;
    private Controler m_controler;

    public Map m_map;
    public drawMap m_drawMap;
    public JPanel m_drawInGamePanel;
    public Snoopy m_snoopy;
    /**
     * Create and add elements to JPanels
     * @param parent
     * @param currentLevel
     * @param controler
     * @see Map
     * @see Level
     * @see Snoopy
     */
    
    public Level(MainWindows parent, int currentLevel, Controler controler){
        this.m_parent = parent;
        this.m_controler = controler;
        this.m_currentLevel = currentLevel;
        init();
    }

    private void init(){
        this.m_map = new Map(m_controler);
        this.m_controler.setLevel(this);
        this.m_map.initMap(Parameters.PATH_LEVEL + Integer.toString(this.m_currentLevel) + ".lvl");
        this.m_snoopy = m_controler.getSnoopy();

        this.setLayout(new GridBagLayout());

        System.out.println("Level CREATED !!");

        //Création des éléments du JPanel
        this.m_drawMap = new drawMap(m_map, m_snoopy, m_controler);
        this.m_drawMap.setPreferredSize(m_drawMap.getPreferredSize());

        this.m_drawInGamePanel = new drawInGamePanel(m_snoopy,m_controler);
        this.m_drawInGamePanel.setLayout(new GridLayout(4,1));
        this.m_drawInGamePanel.setBackground(Color.WHITE);

        //Ajout des éléments au JPanel Level
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        this.add(this.m_drawInGamePanel,gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.9;
        gbc.gridwidth = 20;
        this.add(m_drawMap,gbc);
        
        m_parent.addKeyListener(this);
        m_controler.startTimer();
    }
    /**
     * get the level 
     * @return level 
     */
    public Level getLevel(){
        return this;
    }
    /**
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent evt) { 

    }
    /**
     * Send the typed to the controler block 
     * @param evt
     * @see KEY_LEFT
     * @see KEY_UP
     * @see KEY_RIGHT
     * @see KEY_DOWN
     * @see KEY_P
     * @see KEY_S
     * @see KEY_ESPACE
     * @see Parameters 
     */
    @Override
    public void keyPressed(KeyEvent evt) { 
        if(!m_controler.getAI()){
            switch(evt.getKeyCode()){
                case Parameters.KEY_LEFT:
                    System.out.println("LEFT KEY");
                    m_controler.KEY_LEFT();
                    break;
                case Parameters.KEY_UP:
                    System.out.println("UP KEY");
                    m_controler.KEY_UP();
                    break;
                case Parameters.KEY_RIGHT:
                    System.out.println("RIGHT KEY");
                    m_controler.KEY_RIGHT();
                    break;
                case Parameters.KEY_DOWN:
                    System.out.println("DOWN KEY");
                    m_controler.KEY_DOWN();
                    break;
                case Parameters.KEY_S:
                    System.out.println("S : SAVE");
                    m_controler.KEY_S();
                    break;
                case Parameters.KEY_P:
                    System.out.println("P : PAUSE");
                    m_controler.KEY_P();
                    break;
                case Parameters.KEY_ESCAPE:
                    System.out.println("ESCAPE : QUIT MENU");
                    m_controler.KEY_ESCAPE();
                    break;
                case Parameters.KEY_SPACE:
                    System.out.println("SPACE : ACTION BUTTON");
                    m_controler.KEY_SPACE();
                    break;
            } 
        }
        else{
            switch(evt.getKeyCode()){
                case Parameters.KEY_P:
                    System.out.println("P : PAUSE");
                    m_controler.KEY_P();
                    break;
                case Parameters.KEY_ESCAPE:
                    System.out.println("ESCAPE : QUIT MENU");
                    m_controler.KEY_ESCAPE();
                    break;
                default:
                    break;
            }
        }
    }
    /**
     * key released 
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
    /**
     * Restart 
     */
    public void restart(){
        m_controler.restart();
    }
    /**
     * Get the map 
     * @return
     */
    public Map getMap(){
        return this.m_map;
    }
    /**
     * get the current level 
     * @return m.currentLevel
     */
    public int getCurrentLevel(){
        return this.m_currentLevel;
    }
    /**
     * set the current level 
     * @param currentLevel
     */
    public void setCurrentLevel(int currentLevel){
        this.m_currentLevel = currentLevel;
    }
    
}