package view;

import controler.Controler;
import javax.swing.*;

import AI.Graph;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import model.Parameters;

public class menuAI extends JFrame implements ActionListener , MouseListener {
    private JButton backButton;
    private JButton dfsButton;
    private JButton playDfsButton;
    private Controler m_controler;
    private ArrayList<Graph> m_graphs = new ArrayList<Graph>();
    
    /**
     * Constructor menuAI
     * create a window with AI options 
     * @see Dimension 
     */
    public menuAI(Controler controler){
        super("AI Menu");
        this.m_controler = controler;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(300, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        initMenuAI();
        this.setVisible(true);
    }
    
    public void initMenuAI(){

        this.setLayout(new GridBagLayout());

        dfsButton = new JButton("Generate Solutions");
        dfsButton.setPreferredSize(Parameters.SIZE_BUTTON);
        dfsButton.addActionListener(new ActionListener(){ // Button is pressed
            /**
             * Button actions
             * @param e
             * @see PasswordMenu
             */
            public void actionPerformed(ActionEvent e){
                System.console().printf("dfsButton is pressed\n");
                for(int i = 1; i <= Parameters.nb_level; i++){
                    m_graphs.add(new Graph(m_controler, i));
                    m_graphs.get(i-1).course(m_graphs.get(i-1).getEndVertex());
                }
            }
        });

        playDfsButton = new JButton("Play DFS solutions");
        playDfsButton.setPreferredSize(Parameters.SIZE_BUTTON);
        playDfsButton.addActionListener(new ActionListener(){ // Button is pressed
            /**
             * Button actions
             * @param e
             * @see PasswordMenu
             */
            public void actionPerformed(ActionEvent e){
                System.console().printf("playDfsButton is pressed\n");
                m_controler.getMainWindows().putContent(new Level(m_controler.getMainWindows(), 1, m_controler));
                m_controler.newGame();
                m_controler.initAI(1);
                m_controler.setAI(true);
                
            }
        });


        // BUTTON INIT
        backButton = new JButton("Back");
        backButton.setPreferredSize(Parameters.SIZE_BUTTON);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(dfsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(playDfsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(backButton, gbc);
    }
    /**
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    /**
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        
    }
    /**
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    /**
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    /**
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    /**
     * @param e
     * @see Source
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == backButton){
            this.dispose();
        }
        
    }
}

