package view;
import controler.Controler;

import javax.swing.*;

import java.awt.*;
import java.awt.Color; // For the color of the buttons

import model.Parameters; // For the size of the buttons
import java.awt.GridBagLayout; // For the layout of the buttons
import java.awt.Insets; // For the space between the buttons
import java.awt.GridBagConstraints; // For the layout of the buttons
import java.awt.event.*; // For the buttons event

import java.awt.Image; // For the background
import java.awt.Graphics; // For the background
import java.io.BufferedReader;
import java.io.File; // Files lecture
import java.io.FileReader;
import java.util.Arrays;


/*
 * Draw the 3 best scores of the game
 */

public class ScoreBoardMenu extends JPanel implements ActionListener{
    // Attributes
    private MainWindows m_parent;
    private Controler m_controler;

    private Image backgroundImg;

    private File m_file;
    private FileReader m_fileReader;
    BufferedReader m_bufferedReader;

    private JLabel labelScore;

    private JLabel m_labelScore1;
    private int m_score1;

    private JLabel m_labelScore2;
    private int m_score2;

    private JLabel m_labelScore3;
    private int m_score3;
    /**
     * Constructor ScoreBoard
     * @param parent
     * @param controler
     * @see Parameters 
     */
    public ScoreBoardMenu(MainWindows parent, Controler controler) {
        m_file = new File(Parameters.PATH_SCORE + "scores.txt");
        m_parent = parent;
        m_controler = controler;
        drawBackground();
        init();
    }
    /**
     * Create the score display on the main menu and add a back button
     * @see parseInt
     * @see Dimention 
     * @see ActionListener
     * @see MainMenu
     * @see MouseAdapter
     */
    private void init(){
        this.setLayout(new GridBagLayout());

        // Read the file
        try {
            m_fileReader = new FileReader(m_file);
            m_bufferedReader = new BufferedReader(m_fileReader);
            m_score1 = Integer.parseInt(m_bufferedReader.readLine());
            m_score2 = Integer.parseInt(m_bufferedReader.readLine());
            m_score3 = Integer.parseInt(m_bufferedReader.readLine());
            m_bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        int[] scoresSorted = sortValues(m_score1, m_score2, m_score3);
        m_score1 = scoresSorted[2];
        m_score2 = scoresSorted[1];
        m_score3 = scoresSorted[0];

        // Compare the value

        // Score label
        labelScore = new JLabel("The 3 best scores are:");
        labelScore.setPreferredSize(new Dimension(600, 30));
        labelScore.setHorizontalAlignment(SwingConstants.CENTER);
        labelScore.setVerticalAlignment(SwingConstants.CENTER);
        labelScore.setFont(new Font("Arial", Font.BOLD, 30));

        m_labelScore1 = new JLabel("Score 1: " + m_score1 + " pts");
        m_labelScore1.setPreferredSize(new Dimension(600, 30));
        m_labelScore1.setHorizontalAlignment(SwingConstants.CENTER);
        m_labelScore1.setVerticalAlignment(SwingConstants.CENTER);
        m_labelScore1.setFont(new Font("Arial", Font.BOLD, 28));

        m_labelScore2 = new JLabel("Score 2: " + m_score2 + " pts");
        m_labelScore2.setPreferredSize(new Dimension(600, 30));
        m_labelScore2.setHorizontalAlignment(SwingConstants.CENTER);
        m_labelScore2.setVerticalAlignment(SwingConstants.CENTER);
        m_labelScore2.setFont(new Font("Arial", Font.BOLD, 26));

        m_labelScore3 = new JLabel("Score 3: " + m_score3 + " pts");
        m_labelScore3.setPreferredSize(new Dimension(600, 30));
        m_labelScore3.setHorizontalAlignment(SwingConstants.CENTER);
        m_labelScore3.setVerticalAlignment(SwingConstants.CENTER);
        m_labelScore3.setFont(new Font("Arial", Font.BOLD, 24));

        // BACK BUTTON
        JButton backButton = new JButton("Exit Menu");
        backButton.setPreferredSize(Parameters.SIZE_BUTTON);
        backButton.addActionListener(new ActionListener(){ // Button is pressed
            /**
             * button actions
             * @param e
             * @see MainMenu
             */
            public void actionPerformed(ActionEvent e){
                m_parent.putContent(new MainMenu(m_parent, m_controler));
            }
        });
        backButton.addMouseListener(new MouseAdapter(){ // Mouse is over the button
            /**
             * Mouse interaction with button color
             * @param e
             */
            public void mouseEntered(MouseEvent e){
                activeColorButton(backButton);
            }
            /**
             * Mouse interaction with button color
             * @param e
             */
            public void mouseExited(MouseEvent e){
                desactiveColorButton(backButton);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(labelScore, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(m_labelScore1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(m_labelScore2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(m_labelScore3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(backButton, gbc);

    }

    
    /**
     * return the higher int between a and b and c
     * @param a
     * @param b
     * @param c
     * @return array 
     */
    private int[] sortValues(int a, int b, int c){
        int[] array = new int[3];
        array[0] = a;
        array[1] = b;
        array[2] = c;
        Arrays.sort(array);
        return array;
    }
    /**
     * displays the background image
     */
    private void drawBackground(){
        backgroundImg = new ImageIcon(Parameters.PATH_IMAGE + "menuBackground.png").getImage();
        ImageIcon background = new ImageIcon(backgroundImg); 
        JLabel thumb = new JLabel();
        thumb.setIcon(background);
    }
    /**
     * display 
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) { // Paint the background
        g.drawImage(backgroundImg, 0, 0, null);
      }
    /**
    * active Color buttons
    * @param button
    */
    private void activeColorButton(JButton button){
        button.setBackground(Color.red);
        button.setForeground(Color.green);
    }
    /**
     * Desactive color button 
     * @param button
     */
    private void desactiveColorButton(JButton button){
        button.setBackground(Color.white);
        button.setForeground(Color.black);
    }
    /**
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}




