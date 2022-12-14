package view;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import controler.Controler;
import model.Parameters;

public class GameWinMenu extends JPanel implements ActionListener {
    // Attributs
    private int m_currentScore;
    
    private JButton backButton;
    private MainWindows m_parent;
    private Controler m_controler;


    public GameWinMenu(MainWindows parent, Controler controler, int score){
        
        this.m_currentScore = score;
        this.m_parent = parent;
        this.m_controler = controler;

        this.setLayout(new GridBagLayout());

        init();
    }

    private void init(){

        //Score
        JLabel labelScore = new JLabel("Congratulation! : Your score is: " + m_currentScore);
        labelScore.setHorizontalAlignment(JLabel.CENTER);
        labelScore.setPreferredSize(new Dimension(600, 30));
        labelScore.setFont(new Font("Arial", Font.BOLD, 20));

        // BACK BUTTON
        backButton = new JButton("Exit Menu");
        backButton.setPreferredSize(Parameters.SIZE_BUTTON);
        backButton.addActionListener(new ActionListener(){ // Button is pressed
            public void actionPerformed(ActionEvent e){
                System.console().printf("Back button is pressed.\n");
                m_parent.putContent(new MainMenu(m_parent, m_controler));
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(labelScore, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(backButton, gbc);
    }

    // Methods

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
