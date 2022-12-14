package view;

import controler.Controler;

import javax.swing.*;
import java.awt.event.*;

import model.Parameters;
import java.awt.*;
import java.util.Arrays;

/*
 * Draw the Password Menu and ask the user a password in order to acces to any levels.
 */

public class PasswordMenu extends JPanel implements ActionListener {

    private JPasswordField passwordField;
    private JButton passwordButton;
    private static String TRY = "Try";

    private JButton backButton;
    private MainWindows m_parent;
    private Controler m_controler;
    /**
     * Constructor Password Menu 
     * @param parent
     * @param controler
     * @see Parameters 
     * @see Dimension 
     * @see ActionListener
     */
    public PasswordMenu(MainWindows parent, Controler controler){

        m_parent = parent;
        m_controler = controler;
        this.setLayout(new GridBagLayout());

        //LABEL PASSWORD
        JLabel labelPassword = new JLabel("Enter the password: ");
        labelPassword.setPreferredSize(new Dimension(200, 30));
        labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
        labelPassword.setVerticalAlignment(SwingConstants.CENTER);
        labelPassword.setFont(new Font("Arial", Font.BOLD, 20));

        // PASSWORD FIELD
        passwordField = new JPasswordField(10);
        passwordField.setPreferredSize(new Dimension(300, 30));
        passwordField.setActionCommand(TRY);
        passwordField.addActionListener(this);

        // TRY BUTTON
        passwordButton = new JButton(TRY);
        passwordButton.setPreferredSize(Parameters.SIZE_BUTTON);
        passwordButton.setActionCommand(TRY);
        passwordButton.addActionListener(this);

        // BACK BUTTON
        backButton = new JButton("Back");
        backButton.setPreferredSize(Parameters.SIZE_BUTTON);
        backButton.addActionListener(new ActionListener(){ // Button is pressed
            /**
             * button actions
             * @param e 
             * @see GameMenu 
             */
            public void actionPerformed(ActionEvent e){
                System.console().printf("Back button is pressed.\n");
                parent.putContent(new GameMenu(parent, m_controler));
            }
        });
        backButton.addMouseListener(new MouseAdapter(){ // Mouse is over the button
            /**
             * Mouse interaction with button 
             * @param e
             */
            public void mouseEntered(MouseEvent e){
                //System.console().printf("Mouse entered back button");
            }
            /**
             * Mouse interaction with button 
             * @param e
             */
            public void mouseExited(MouseEvent e){
                //System.console().printf("Mouse exited back button");
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(labelPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(passwordButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(backButton, gbc);
    }
    /**
     * button actions 
     * @param e
     * @see Password 
     * @see isPassewordCorrect
     * 
     */
    public void actionPerformed(ActionEvent e) { 
        String cmd = e.getActionCommand();

        if (TRY.equals(cmd)) { //Process the password.
            char[] input = passwordField.getPassword();
            boolean passwordIsCorrect = false;
            int lvlSelected = -1;
            for (int lvl = 0; lvl < 10; lvl++) {
                if (isPasswordCorrect(input, lvl)){
                    passwordIsCorrect = true;
                    lvlSelected = lvl;
                }
            }
            if (passwordIsCorrect) {
                JOptionPane.showMessageDialog(m_parent,
                    "Success! You typed the right password. You can now play the level " + lvlSelected + ".");
                m_parent.putContent(new Level(m_parent, lvlSelected, m_controler));
                m_controler.setAI(false);
            } else {
                JOptionPane.showMessageDialog(m_parent,
                    "Invalid password. Try again.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            }

            //Zero out the possible password, for security.
            Arrays.fill(input, '0');

            passwordField.selectAll();
            resetFocus();
        }
    }

    /**
     * create a different password for each level
     * @param input
     * @param level*
     * 
     * @return isCorrect 
     */
    private static boolean isPasswordCorrect(char[] input, int level) {
        boolean isCorrect = true;
        char[] correctPassword = { 'b', 'u', 'g', 'a', 'b', 'o', 'o' };
        switch(level){
            case 0:
                correctPassword = new char[]{'b', 'u', 'g', 'a', 'b', 'o', '0'};
                break;
            case 1:
                correctPassword = new char[]{'b', 'u', 'g', 'a', 'b', 'o', '1'};
                break;
            case 2:
                correctPassword = new char[]{'b', 'u', 'g', 'a', 'b', 'o', '2'};
                break;
            case 3:
                correctPassword = new char[]{'b', 'u', 'g', 'a', 'b', 'o', '3'};
                break;
            case 4:
                correctPassword = new char[]{'b', 'u', 'g', 'a', 'b', 'o', '4'};
                break;
            case 5:
                correctPassword = new char[]{'b', 'u', 'g', 'a', 'b', 'o', '5'};
                break;
            case 6:
                correctPassword = new char[]{'b', 'u', 'g', 'a', 'b', 'o', '6'};
                break;
            default:
                correctPassword = new char[]{'b', 'u', 'g', 'a', 'b', 'o', 'o'};
                break;
        }
        //TODO : change the password for each level

        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals (input, correctPassword);
        }

        //Zero out the password.
        Arrays.fill(correctPassword,'0');

        return isCorrect;
    }

     //Must be called from the event dispatch thread.
     protected void resetFocus() {
        passwordField.requestFocusInWindow();
    }
}
