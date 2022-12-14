package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Dimension;
import java.awt.Color;
import java.util.Collections;

import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import model.Parameters;

import java.awt.Insets;

import controler.Controler;

public class LoadGameMenu extends JScrollPane{
    private JPanel panel;
    private MainWindows m_parent;
    private Controler m_controler;
    /**
     * Constructor LoadGame Menu 
     * @param parent
     * @param controler
     * @see addSaveButtons
     * @see loadSaves
     */
    public LoadGameMenu(MainWindows parent, Controler controler){
        panel = new JPanel();
        m_parent = parent;
        m_controler = controler;
        panel.setLayout(new GridBagLayout());
        this.add(panel);
        //this.getVerticalScrollBar().setUI(new MyScrollBarUI());
        addSaveButtons(loadSaves());
    }
    /**
     * Sort the save list in the arraylist
     * @see Name 
     * @return saves 
     */
    public ArrayList<String> loadSaves(){
        File dir = new File(Parameters.PATH_SAVE);
        File[] files = dir.listFiles();
        ArrayList<String> saves = new ArrayList<String>();
        for (File file : files) {
            if (file.isFile()) {
                saves.add(file.getName());
            }
        }
        Collections.sort(saves); // Sort the list
        return saves;
    }
    public boolean deleteSave(String save){
        File file = new File(Parameters.PATH_SAVE + save);
        return file.delete();
    }
    /**
     * Add a button for each save and labels
     * Add some space between the buttons
     * @param saves
     * @see ActionListener
     * @see Parameters 
     * 
     */
    public void addSaveButtons(ArrayList<String> saves){ 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5); 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.9;
        gbc.gridwidth = 9;

        JLabel labelTitle = new JLabel("Saves"); // TODO : Change the label
        labelTitle.setOpaque(true);
        labelTitle.setBackground(Color.RED);
        panel.add(labelTitle, gbc);

        gbc.gridx = 9;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.gridwidth = 1;
        
        JButton backButton = new JButton("Back"); // Back button
        backButton.setPreferredSize(Parameters.SIZE_BUTTON);
        backButton.addActionListener(new ActionListener(){ // Button is pressed
            /**
             * button actions 
             * @param e
             */
            public void actionPerformed(ActionEvent e){
                System.console().printf("Back button is pressed.\n");
                m_parent.putContent(new GameMenu(m_parent, m_controler));
            }
        });

        panel.add(backButton,gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        gbc.weightx = 0.8;
        //gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        for(String save : saves){
            ArrayList<String> saveInformation = saveInfo(save);
            JLabel saveLabel = new JLabel(saveInformation.get(0));
            saveLabel.setPreferredSize(new Dimension(150, 50));
            JButton trashButton = new JButton("Delete");
            trashButton.setPreferredSize(new Dimension(100, 50));
            trashButton.addActionListener(new ActionListener(){
                /**
                 * Delete the save file 
                 */
                @Override
                public void actionPerformed(ActionEvent e) { 
                    System.console().printf("Deleting game " + save);
                    if(deleteSave(save)){
                        System.console().printf("Game deleted");
                        m_parent.putContent(new LoadGameMenu(m_parent, m_controler));
                    }
                    else{
                        System.console().printf("Game not deleted");
                    }
                }
            });

            JButton playButton = new JButton("Play");
            playButton.setPreferredSize(new Dimension(100, 50));
            playButton.addActionListener(new ActionListener(){
                /**
                 * Load the save level 
                 * @param e 
                 */
                @Override
                public void actionPerformed(ActionEvent e) { 
                    System.console().printf("Load game " + save);
                    m_parent.putContent(new Level(m_parent, Integer.parseInt(saveInformation.get(1)), m_controler));
                    m_controler.setAI(false);
                    m_controler.setSettings(saveInformation);
                    m_controler.getSnoopy().getMap().initMap(Parameters.PATH_SAVE + save);
                    System.out.println("Map from save loaded");
                }
            });

            gbc.gridwidth = 5;
            panel.add(saveLabel, gbc);
            gbc.weightx = 0.1;
            gbc.gridwidth = 1;
            gbc.gridx = 6;
            panel.add(trashButton, gbc);
            gbc.gridx = 8;
            panel.add(playButton, gbc);
            gbc.gridx = 0;
            gbc.gridy++;
        }
        this.setViewportView(panel);
    }
    /**
     * Description of the save files
     * @param save
     * @see Parameters 
     * @return text
     */
    public ArrayList<String> saveInfo(String save){ // Description of the save files
        ArrayList<String> saveInfo = new ArrayList<String>();
        String text = "";
        try{
            FileInputStream saveFile = new FileInputStream(Parameters.PATH_SAVE + save);
            Scanner scanner = new Scanner(saveFile);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(!line.startsWith("//") && line.startsWith("#")){
                    String[] saveSplit = line.split(",");
                    String date = saveSplit[1];
                    String time = saveSplit[2];
                    String lives = saveSplit[3];
                    String score = saveSplit[4];
                    String level = saveSplit[5];
                    text = "Date: " + date + " - Time: " + time + " - Level: " + level + " - Score: " + score + " - Lives: " + lives;
                    saveInfo.add(text);
                    saveInfo.add(level);
                    saveInfo.add(score);
                    saveInfo.add(lives);
                    saveInfo.add(time);
                    scanner.close();
                    break;
            }
        }
        scanner.close();
        }
        
        catch(IOException e){
            System.console().printf("Error while reading the save file " + save);
        }
        return saveInfo;
    }
}
