package view;
import controler.Controler;

import javax.swing.*; 

import java.awt.event.*; // For the buttons event
import java.awt.Color; // For the color of the buttons

import model.Parameters; // For the size of the buttons
import java.awt.GridBagLayout; // For the layout of the buttons
import java.awt.Insets; // For the space between the buttons
import java.awt.GridBagConstraints; // For the layout of the buttons

import java.awt.Image; // For the background
import java.awt.Graphics; // For the background

public class GameMenu extends JPanel{
    private JButton newGameButton;
    private JButton aiButton;
    private JButton loadGameButton;
    private JButton passwordButton;
    private JButton backButton;

    private MainWindows m_parent;
    private Controler m_controler;

    private Image backgroundImg;
    /**
     * Display of the background on the main menu
     */
    private void drawBackground(){
        backgroundImg = new ImageIcon(Parameters.PATH_IMAGE + "menuBackground.png").getImage();
        ImageIcon background = new ImageIcon(backgroundImg); 
        JLabel thumb = new JLabel();
        thumb.setIcon(background);
    }
    /**
     * @param e
     */
    @Override
    public void paintComponent(Graphics g) { // Paint the background
        g.drawImage(backgroundImg, 0, 0, null);
      }
      /**
       * Create game menu buttons
       * @param parent
       * @param controler
       * @see ActionListener
       */
    public GameMenu(MainWindows parent, Controler controler){
        drawBackground();
        m_parent = parent;
        m_controler = controler;
        this.setLayout(new GridBagLayout());

        // PLAY A NEW GAME BUTTON
        newGameButton = new JButton("Play a new game");
        newGameButton.setPreferredSize(Parameters.SIZE_BUTTON);
        newGameButton.setBackground(Color.white);
        newGameButton.addActionListener(new ActionListener(){ // Button is pressed
            /**
             * Button actions
             * @param e
             * @see Level
             */
            public void actionPerformed(ActionEvent e){
                System.console().printf("Play a new game button pressed\n");
                m_parent.putContent(new Level(m_parent, 1, m_controler));
                m_controler.newGame();
                m_controler.setAI(false);
            }
        });
        newGameButton.addMouseListener(new MouseAdapter(){ // Mouse is over the button
            /**
             * Mouse interaction with button color
             * @param e
             * @see activeColorButton
             */
            public void mouseEntered(MouseEvent e){
                activeColorButton(newGameButton);
            }
            /**
             * Mouse interaction with button color
             * @param e
             * @see desactiveColorButton
             */
            public void mouseExited(MouseEvent e){
                desactiveColorButton(newGameButton);
            }
        });

        // AI Menu Button
        aiButton = new JButton("AI Menu");
        aiButton.setPreferredSize(Parameters.SIZE_BUTTON);
        aiButton.setBackground(Color.white);
        aiButton.addActionListener(new ActionListener(){ // Button is pressed
            /**
             * Button actions
             * @param e
             * @see Level
             */
            public void actionPerformed(ActionEvent e){
                System.console().printf("AI Menu button pressed\n");
                new menuAI(m_controler);
            }
        });
        aiButton.addMouseListener(new MouseAdapter(){ // Mouse is over the button
            /**
             * Mouse interaction with button color
             * @param e
             * @see activeColorButton
             */
            public void mouseEntered(MouseEvent e){
                activeColorButton(aiButton);
            }
            /**
             * Mouse interaction with button color
             * @param e
             * @see desactiveColorButton
             */
            public void mouseExited(MouseEvent e){
                desactiveColorButton(aiButton);
            }
        });

        // LOAD A GAME BUTTON
        loadGameButton = new JButton("Load a game");
        loadGameButton.setBackground(Color.white);
        loadGameButton.setPreferredSize(Parameters.SIZE_BUTTON);
        loadGameButton.addActionListener(new ActionListener(){ // Button is pressed

            /**
             * Button actions
             * @param e
             * @see LoadGameMenu
             */
            public void actionPerformed(ActionEvent e){
                System.console().printf("Load a game button is pressed.\n");
                m_parent.putContent(new LoadGameMenu(m_parent, m_controler));
            }
        });
        loadGameButton.addMouseListener(new MouseAdapter(){ // Mouse is over the button
            /**
             * Mouse interaction with button color
             * @param e
             * @see activeColorButton
             */
            public void mouseEntered(MouseEvent e){
                activeColorButton(loadGameButton);
            }
            /**
             * Mouse interaction with button color
             * @param e
             * @see desactiveColorButton
             */
            public void mouseExited(MouseEvent e){
                desactiveColorButton(loadGameButton);
            }
        });

        // PASSWORD BUTTON
        passwordButton = new JButton("Password");
        passwordButton.setBackground(Color.white);
        passwordButton.setPreferredSize(Parameters.SIZE_BUTTON);
        passwordButton.addActionListener(new ActionListener(){ // Button is pressed
            /**
             * Button actions
             * @param e
             * @see PasswordMenu
             */
            public void actionPerformed(ActionEvent e){
                System.console().printf("Password button is pressed.\n");
                m_parent.putContent(new PasswordMenu(m_parent, m_controler));
            }
        });
        passwordButton.addMouseListener(new MouseAdapter(){ // Mouse is over the button
            /**
             * Mouse interaction with button color
             * @param e
             * @see activeColorButton
             */
            public void mouseEntered(MouseEvent e){
                activeColorButton(passwordButton);
            }
            /**
             * Mouse interaction with button color
             * @param e
             * @see desactiveColorButton
             */
            public void mouseExited(MouseEvent e){
                desactiveColorButton(passwordButton);
            }
        });

        // BACK BUTTON
        backButton = new JButton("Back");
        backButton.setBackground(Color.white);
        backButton.setPreferredSize(Parameters.SIZE_BUTTON);
        backButton.addActionListener(new ActionListener(){ // Button is pressed
            /**
             * Button actions
             * @param e
             * @see MainMenu 
             */
            public void actionPerformed(ActionEvent e){
                System.console().printf("Back button is pressed.\n");
                parent.putContent(new MainMenu(parent, m_controler));
            }
        });
        backButton.addMouseListener(new MouseAdapter(){ // Mouse is over the button
            /**
             * Mouse interaction with button color
             * @param e
             * @see activeColorButton
             */
            public void mouseEntered(MouseEvent e){
                activeColorButton(backButton);
            }
            /**
             * Mouse interaction with button color
             * @param e
             * @see desactiveColorButton
             */
            public void mouseExited(MouseEvent e){
                desactiveColorButton(backButton);
            }
        });
        /**
         *Put the buttons on the window 
         */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(newGameButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(aiButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(loadGameButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(passwordButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(backButton, gbc);
    }
    /**
     * Color Button 
     * @param button
     */
    private void activeColorButton(JButton button){
        button.setBackground(Color.red);
        button.setForeground(Color.green);
    }
    /**
     * Color Button 
     * @param button
     */   
    private void desactiveColorButton(JButton button){
        button.setBackground(Color.white);
        button.setForeground(Color.black);
    }
}
