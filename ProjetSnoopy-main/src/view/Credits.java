package view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import model.Parameters;

public class Credits extends JFrame implements ActionListener , MouseListener {
    private JButton backButton;
    private JLabel creditsLabel;

    public Credits(){
        super("Credits");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(300, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        initCredits();
        this.setVisible(true);
    }
    /**
     * Constructor Credit 
     * create a window with our information 
     * @see Dimension 
     */
    public void initCredits(){

        this.setLayout(new GridBagLayout());

        // LABEL INIT
        String creditText = "<html> <center>" + 
                        "<h1>SNOOPY REVENGE</h1>" +
                        "<br /> <i>MADE BY<br /><br /> " +
                        "Raphaël LE GAC <br />" +
                        "Gabin PAQUES <br />" +
                        "Marin DUDOUET <br /> <br />" +
                        "ING 3 à l'ECE - Années 2022 - 2025 <br />" +
                        "</center></html>";

        Dimension sizeLabel = new Dimension(250, 250);
        creditsLabel = new JLabel(creditText);
        creditsLabel.setPreferredSize(sizeLabel);

        // BUTTON INIT
        backButton = new JButton("Back");
        backButton.setPreferredSize(Parameters.SIZE_BUTTON);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(creditsLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
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

