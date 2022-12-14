package view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.GridBagConstraints;

import controler.Controler;
import model.Parameters;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainMenu extends JPanel {
    private JButton playButton;
    private JButton quitButton;
    private JButton scoreBoardButton;
    private JButton creditsButton;
    private MainWindows m_parent;
    private Controler m_controler;

    private Image backgroundImg;

    public MainMenu(MainWindows parent, Controler controler){

        m_parent = parent;
        m_controler = controler;

        Dimension size = new Dimension(Parameters.SIZE_BUTTON);
        Dimension size2 = new Dimension(Parameters.SIZE_BUTTON2);
        this.setLayout(new GridBagLayout());

        drawBackground();
       
        // PLAY BUTTON
        playButton = new JButton("Play");
        playButton.setBackground(Color.white);
        playButton.setPreferredSize(size2);
        playButton.setFont(getFont().deriveFont(40.0f));
        playButton.addActionListener(new ActionListener(){ // Button is pressed
            public void actionPerformed(ActionEvent e){
                System.console().printf("Play button pressed\n");
                parent.putContent(new GameMenu(m_parent, m_controler));
            }
        });
        playButton.addMouseListener(new MouseAdapter(){ // Mouse is over the button
            public void mouseEntered(MouseEvent e){
                activeColorButton(playButton);
            }
            public void mouseExited(MouseEvent e){
                desactiveColorButton(playButton);
            }
        });

        // SCORE BOARD BUTTON
        scoreBoardButton = new JButton("Score Board");
        scoreBoardButton.setBackground(Color.white);
        scoreBoardButton.setPreferredSize(size);
        scoreBoardButton.addActionListener(new ActionListener(){ // Button is pressed
            public void actionPerformed(ActionEvent e){
                parent.putContent(new ScoreBoardMenu(m_parent, m_controler));
            }
        });
        scoreBoardButton.addMouseListener(new MouseAdapter(){ // Mouse is over the button
            public void mouseEntered(MouseEvent e){
                activeColorButton(scoreBoardButton);
            }
            public void mouseExited(MouseEvent e){
                desactiveColorButton(scoreBoardButton);
            }
        });

        // CREDIT BUTTON
        creditsButton = new JButton("Credits");
        creditsButton.setBackground(Color.white);
        creditsButton.setPreferredSize(size);
        creditsButton.addActionListener(new ActionListener(){ // Button is pressed
            public void actionPerformed(ActionEvent e){
                System.console().printf("Credits button is pressed.\n");
                new Credits();
            }
        });
        creditsButton.addMouseListener(new MouseAdapter(){ // Mouse is over the button
            public void mouseEntered(MouseEvent e){
                activeColorButton(creditsButton);
            }
            public void mouseExited(MouseEvent e){
                desactiveColorButton(creditsButton);
            }
        });

        // QUIT BUTTON
        quitButton = new JButton("Quit");
        quitButton.setBackground(Color.white);
        quitButton.setPreferredSize(size);
        quitButton.addActionListener(new ActionListener(){ // Button is pressed
            public void actionPerformed(ActionEvent e){
                System.console().printf("Quit button is pressed. Exiting...\n");
                System.exit(0); // Quit the program
            }
        });
        quitButton.addMouseListener(new MouseAdapter(){ // Mouse is over the button
            public void mouseEntered(MouseEvent e){
                activeColorButton(quitButton);
            }
            public void mouseExited(MouseEvent e){
                desactiveColorButton(quitButton);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(playButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(scoreBoardButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(creditsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(quitButton, gbc);
    }

    private void drawBackground(){
        backgroundImg = new ImageIcon(Parameters.PATH_IMAGE + "menuBackground.png").getImage();
        ImageIcon background = new ImageIcon(backgroundImg); 
        JLabel thumb = new JLabel();
        thumb.setIcon(background);
    }

    @Override
    public void paintComponent(Graphics g) { // Paint the background
        g.drawImage(backgroundImg, 0, 0, null);
      }

    private void activeColorButton(JButton button){
        button.setBackground(Color.red);
        button.setForeground(Color.green);
    }
    
    private void desactiveColorButton(JButton button){
        button.setBackground(Color.white);
        button.setForeground(Color.black);
    }
}
