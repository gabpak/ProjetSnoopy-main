package view;

import model.*;
import controler.Controler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

// DISPLAY THE SCORE BAR WHEN PLAYING

public class drawInGamePanel extends JPanel {

    Snoopy m_snoopy;
    Timer timer;
    Image inGamePanelBackgImage;
    Double m_time;
    Controler m_controler;

    BufferedImage imgHeart;
    BufferedImage imgFeather;
    BufferedImage imgHourglass;
    /**
     * 
     * @param ActionEvent 
     * @see Time 
     * 
     */
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            m_time = m_controler.getTime();
            revalidate();
            repaint();
        }
    };
    /**
     * 
     * @param snoopy
     * @param controler
     * @see Timer
     * @see Parameters 
     * @see start
     * 
     */
    public drawInGamePanel(Snoopy snoopy, Controler controler) {
        m_snoopy = snoopy;
        m_controler = controler;
        timer = new Timer(500, actionListener);
        timer.setInitialDelay(0);
        timer.start();
        setBackground(Color.yellow);
        setPreferredSize(getPreferredSize());
        
        try {
            imgHeart = ImageIO.read(new File(Parameters.PATH_IMAGE + "heart.png"));
            imgFeather = ImageIO.read(new File(Parameters.PATH_IMAGE + "feather.png"));
            imgHourglass = ImageIO.read(new File(Parameters.PATH_IMAGE + "hourglass.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Set the Snoopy 
     * @param snoopy
     */
    public void setSnoopy(Snoopy snoopy) {
        m_snoopy = snoopy;
    }
    /**
     * Display
     * @param g
     * @see Health 
     * @see Heart
     * 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //addPaintingHere
        paintHeart(g, m_snoopy.getHealth());
    }
    /**
     * show health and score 
     * @param g
     * @param heart
     * @see Score 
     * @see Subimage
     * @see Score 
     */
    public void paintHeart(Graphics g, int heart) {
        Image subspriteFeather = imgFeather.getSubimage(0, 0, 32, 32);

        for (int i = 0; i < heart; i++) {
            g.drawImage(imgHeart, 37 + 30 * i, 50, null);
        }

        g.drawImage(subspriteFeather, 37, 100, null);
        g.drawString(Integer.toString(m_controler.getScore()), 100, 120);
    }
    /**
     * @return The new Dimension  
     * @see Dimension 
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(158, 640 + 2*20); //More height to match the timeBar add
    }
    /**
     * 
     * Set the time 
     * @param time
     */
    public void setTime(Double time){
        m_time = time;
    }
}