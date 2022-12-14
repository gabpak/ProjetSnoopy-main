package view;

import model.*;
import controler.*;
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
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 */
public class drawMap extends JPanel {

    int[][] m_twoSpriteSheetCoords = Parameters.twoSpriteSheetCoords;
    int[][] m_timeBarSpriteSheetCoords = Parameters.timeBarSpriteSheetCoords;
    int[][] m_timeBarCoords = Parameters.timeBarCoords;
    int[][] m_snoopySpriteSheetCoords = Parameters.snoopySpriteSheetCoords;
    int[][] m_backgroundSpriteSheetCoords = Parameters.backgroundSpriteSheetCoords;
    int[][] m_birdSpirteSheetCoords = Parameters.birdSpriteSheetCoords;
    int[][] m_conveyorBeltSpriteSheetCoords = Parameters.conveyorBeltSpriteSheetCoords;
    int[][] m_ballSpriteSheetCoords = Parameters.ballSpriteSheetCoords;

    int k, l, m, n = 0;

    //Buffered image
    BufferedImage imgSnoopy; // 8
    BufferedImage imgTimeBar; 
    BufferedImage imgTimeBarCorner;
    BufferedImage imgBackground;
    BufferedImage imgUnbreakable;
    BufferedImage imgBreakable;
    BufferedImage imgPushable;
    BufferedImage imgTrapped;
    BufferedImage imgBall;
    BufferedImage imgBird;
    BufferedImage imgCBU;
    BufferedImage imgCBL;
    BufferedImage imgCBR;
    BufferedImage imgCBD;


    Map m_map;
    Snoopy m_snoopy;
    Controler m_controler;
    Double m_time;

    Timer timer;

    double m_angle;

    //Sprite bar
    Image subSpriteTimeBarEmpty;
    Image subSpriteTimeBarFull;
    Image subSpriteTimeBarParted;

    //Snoopy
    Image subSpriteSnoopyUP;
    Image subSpriteSnoopyDOWN;
    Image subSpriteSnoopyLEFT;
    Image subSpriteSnoopyRIGHT;

    //Background
    Image subSpriteBackground;

    //Blocks
    Image subSpriteBreakable; // 1
    Image subSpritePushable; // 2
    Image subSpriteTrapped; // 3
    Image subSpriteUnbreakable; // 4
    Image subSpriteConveyorBelt; // 6
    Image subSpriteBall; // 7
    Image subSpriteBird; // 9
    
    /**
     * The Actions Listener 
     */
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            m_time = m_controler.getTime();
            k++;
            l++;
            m++;
            n++;
            if(k == m_twoSpriteSheetCoords.length) {
                k = 0;
            }
            if(l == m_backgroundSpriteSheetCoords.length){
                l = 0;
            }
            if(m == m_birdSpirteSheetCoords.length){
                m = 0;
            }
            if(n == m_conveyorBeltSpriteSheetCoords.length){
                n = 0;
            }
            revalidate();
            repaint();
        }
    };
    /**
     * Constructor drawMap 
     * @param map
     * @param snoopy
     * @param controler
     * @see Subimage 
     * @see Parameters 
     * @see Timer 
     * @see InitialDelay
     * @see start
     * @see Time 
     */
    public drawMap(Map map, Snoopy snoopy, Controler controler) {
        m_map = map;
        m_snoopy = snoopy;
        m_controler = controler;
        m_time = controler.getTime();
        timer = new Timer(45, actionListener);
        timer.setInitialDelay(0);
        timer.start();
        setBackground(Color.yellow);
        setPreferredSize(getPreferredSize());
        
        try {
            imgSnoopy = ImageIO.read(new File(Parameters.PATH_IMAGE + "snoopy.png"));
            imgTimeBar = ImageIO.read(new File(Parameters.PATH_IMAGE + "timeBarRope.png")); //TODO: change this image
            imgTimeBarCorner = ImageIO.read(new File(Parameters.PATH_IMAGE + "timeBarCorner.png"));
            imgBackground = ImageIO.read(new File(Parameters.PATH_IMAGE + "map.png"));

            //Entities
            imgUnbreakable = ImageIO.read(new File(Parameters.PATH_IMAGE + "unbreakable.png"));
            imgPushable = ImageIO.read(new File(Parameters.PATH_IMAGE + "pushable.png"));
            imgTrapped = ImageIO.read(new File(Parameters.PATH_IMAGE + "trapped.png"));
            imgBreakable = ImageIO.read(new File(Parameters.PATH_IMAGE + "breakable.png"));
            imgBird = ImageIO.read(new File(Parameters.PATH_IMAGE + "bird.png"));
            imgBall = ImageIO.read(new File(Parameters.PATH_IMAGE + "ball.png"));
            imgCBU = ImageIO.read(new File(Parameters.PATH_IMAGE + "CBU.png"));
            imgCBL = ImageIO.read(new File(Parameters.PATH_IMAGE + "CBL.png"));
            imgCBR = ImageIO.read(new File(Parameters.PATH_IMAGE + "CBR.png"));
            imgCBD = ImageIO.read(new File(Parameters.PATH_IMAGE + "CBD.png"));

            System.out.println("Image loaded");

            subSpriteSnoopyUP = imgSnoopy.getSubimage(m_snoopySpriteSheetCoords[1][0], m_snoopySpriteSheetCoords[1][1], m_snoopySpriteSheetCoords[1][2], m_snoopySpriteSheetCoords[1][3]);
            subSpriteSnoopyDOWN = imgSnoopy.getSubimage(m_snoopySpriteSheetCoords[0][0], m_snoopySpriteSheetCoords[0][1], m_snoopySpriteSheetCoords[0][2], m_snoopySpriteSheetCoords[0][3]);
            subSpriteSnoopyLEFT = imgSnoopy.getSubimage(m_snoopySpriteSheetCoords[3][0], m_snoopySpriteSheetCoords[3][1], m_snoopySpriteSheetCoords[3][2], m_snoopySpriteSheetCoords[3][3]);
            subSpriteSnoopyRIGHT = imgSnoopy.getSubimage(m_snoopySpriteSheetCoords[2][0], m_snoopySpriteSheetCoords[2][1], m_snoopySpriteSheetCoords[2][2], m_snoopySpriteSheetCoords[2][3]);
            
            System.out.println("Snoopy created");

        } catch (IOException e) {
            System.out.println("Error when loading images");
        }
    }
    /**
     * set the map
     * @param map
     */
    public void setMap(Map map) {
        m_map = map;
    }
    /**
     * set the time 
     * @param time
     */
    public void setTime(Double time){
        m_time = time;
    }
    /**
     * Paint Time Bar 
     * @param g
     * @param time
     */
    public void paintTimeBar(Graphics g, Double time){
        try{
            int timeDecimal = (int) ((time - time.intValue())*10);
            //System.out.println(timeDecimal);
            subSpriteTimeBarEmpty = imgTimeBar.getSubimage(m_timeBarSpriteSheetCoords[0][0], m_timeBarSpriteSheetCoords[0][1], m_timeBarSpriteSheetCoords[0][2], m_timeBarSpriteSheetCoords[0][3]); //Getting Empty Sprite
            subSpriteTimeBarFull = imgTimeBar.getSubimage(m_timeBarSpriteSheetCoords[10][0], m_timeBarSpriteSheetCoords[10][1], m_timeBarSpriteSheetCoords[10][2], m_timeBarSpriteSheetCoords[10][3]); //Getting Full Sprite
            subSpriteTimeBarParted = imgTimeBar.getSubimage(m_timeBarSpriteSheetCoords[timeDecimal][0], m_timeBarSpriteSheetCoords[timeDecimal][1], m_timeBarSpriteSheetCoords[timeDecimal][2], m_timeBarSpriteSheetCoords[timeDecimal][3]); //Getting Parted Sprite
    
            //Drawing corners of the timeBar; purely aesthetic
            g.drawImage(imgTimeBarCorner,0,0,null);
            g.drawImage(imgTimeBarCorner,1300,0,null);
            g.drawImage(imgTimeBarCorner,0,660,null);
            g.drawImage(imgTimeBarCorner,1300,660,null);
        
            //Drawing the timeBar
            for (int i = 0; i < 60; i++) {
                
                if(time+1 < i){
                    //Determining whether it's the upper,lower,right or left part of the timeBar to calculate the rotation
                    if(m_timeBarCoords[i][1] == 0){
                        m_angle = 0;
                    }
                    if(m_timeBarCoords[i][1] == 660){
                        m_angle = 180;
                    }
                    if(m_timeBarCoords[i][0] == 0){
                        m_angle = 270;
                    }
                    if(m_timeBarCoords[i][0] == 1300){
                        m_angle = 90;
                    }
                    g.drawImage(rotatingImage(subSpriteTimeBarEmpty,m_angle), m_timeBarCoords[i][0], m_timeBarCoords[i][1], null);
                }
                else if(time > i){
                    if(m_timeBarCoords[i][1] == 0){
                        m_angle = 0;
                    }
                    if(m_timeBarCoords[i][1] == 660){
                        m_angle = 180;
                    }
                    if(m_timeBarCoords[i][0] == 0){
                        m_angle = 270;
                    }
                    if(m_timeBarCoords[i][0] == 1300){
                        m_angle = 90;
                    }
                    g.drawImage(rotatingImage(subSpriteTimeBarFull,m_angle), m_timeBarCoords[i][0], m_timeBarCoords[i][1], null);
                }
                else if(time.intValue()+1 == i){
                    if(m_timeBarCoords[i][1] == 0){
                        m_angle = 0;
                    }
                    if(m_timeBarCoords[i][1] == 660){
                        m_angle = 180;
                    }
                    if(m_timeBarCoords[i][0] == 0){
                        m_angle = 270;
                    }
                    if(m_timeBarCoords[i][0] == 1300){
                        m_angle = 90;
                    }
                    g.drawImage(rotatingImage(subSpriteTimeBarParted,m_angle), m_timeBarCoords[i][0], m_timeBarCoords[i][1], null);
                }
                
            }
        }
        catch(Exception e){
            System.out.println("Error in paintTimeBar");
        }
    }
    /**
     * Paint Images
     * @param g
     * @see Subimage
     * @see X
     * @see Y 
     * @see Map
     * @see Entity
     * @see Type
     */
    @Override
    public void paintComponent(Graphics g) { //TODO : Rework paintComponent to have switch on m_map.getMap()[x][y].m_type instead of if/else
        super.paintComponent(g);
        paintTimeBar(g, this.m_time);
        // Displaying the background.
        subSpriteBackground = imgBackground.getSubimage(m_backgroundSpriteSheetCoords[l][0], m_backgroundSpriteSheetCoords[l][1], m_backgroundSpriteSheetCoords[l][2], m_backgroundSpriteSheetCoords[l][3]);
        g.drawImage(subSpriteBackground, 20, 20, null);
        for (int x = 0; x < m_map.getMap().length; x++) {
            for (int y = 0; y < m_map.getMap()[x].length; y++) {
                // Displaying the Breakable on the map
                if(m_map.getMap()[x][y].m_type == Entity.Type.BLOCK_BREAKABLE) { // 1
                    subSpriteBreakable = imgBreakable.getSubimage(m_twoSpriteSheetCoords[k][0], m_twoSpriteSheetCoords[k][1], m_twoSpriteSheetCoords[k][2], m_twoSpriteSheetCoords[k][3]);
                    g.drawImage(subSpriteBreakable, x * 64+20, y * 64+20, null);
                }
                // Displaying the Pushable on the map
                if(m_map.getMap()[x][y].m_type == Entity.Type.BLOCK_PUSHABLE) { // 2
                    subSpritePushable = imgPushable.getSubimage(m_twoSpriteSheetCoords[k][0], m_twoSpriteSheetCoords[k][1], m_twoSpriteSheetCoords[k][2], m_twoSpriteSheetCoords[k][3]);
                    g.drawImage(subSpritePushable, x * 64+20, y * 64+20, null);
                }
                // Displaying the Trapped on the map
                if(m_map.getMap()[x][y].m_type == Entity.Type.BLOCK_TRAPPED){
                    subSpriteTrapped = imgTrapped.getSubimage(m_twoSpriteSheetCoords[k][0], m_twoSpriteSheetCoords[k][1], m_twoSpriteSheetCoords[k][2], m_twoSpriteSheetCoords[k][3]);
                    g.drawImage(subSpriteTrapped, x * 64+20, y * 64+20, null);
                }
                // Displaying the Unbreakble on the map
                if(m_map.getMap()[x][y].m_type == Entity.Type.BLOCK_UNBREAKABLE) { // 4
                    subSpriteUnbreakable = imgUnbreakable.getSubimage(m_twoSpriteSheetCoords[k][0], m_twoSpriteSheetCoords[k][1], m_twoSpriteSheetCoords[k][2], m_twoSpriteSheetCoords[k][3]);
                    g.drawImage(subSpriteUnbreakable, x * 64+20, y * 64+20, null);
                }

                // Displaying the ball on the map
                if(m_map.getMap()[x][y].m_type == Entity.Type.BALL) { // 7
                    //Should never happen, balls hover the map
                    subSpriteBall = imgBall.getSubimage(m_ballSpriteSheetCoords[0][0], m_ballSpriteSheetCoords[0][1], m_ballSpriteSheetCoords[0][2], m_ballSpriteSheetCoords[0][3]);
                    g.drawImage(subSpriteBall, x * 64+20, y * 64+20, null);
                }

                // Displaying the Conveyor_Belt on the map
                if(m_map.getMap()[x][y].m_type == Entity.Type.BLOCK_CONVEYOR_BELT) { // 6
                    switch(((Conveyor_Belt) m_map.getMap()[x][y]).m_direction){
                        case UP:
                            subSpriteConveyorBelt = imgCBU.getSubimage(m_conveyorBeltSpriteSheetCoords[n][0], m_conveyorBeltSpriteSheetCoords[n][1], m_conveyorBeltSpriteSheetCoords[n][2], m_conveyorBeltSpriteSheetCoords[n][3]);
                            break;
                        case DOWN:
                            subSpriteConveyorBelt = imgCBD.getSubimage(m_conveyorBeltSpriteSheetCoords[n][0], m_conveyorBeltSpriteSheetCoords[n][1], m_conveyorBeltSpriteSheetCoords[n][2], m_conveyorBeltSpriteSheetCoords[n][3]);
                            break;
                        case LEFT:
                            subSpriteConveyorBelt = imgCBL.getSubimage(m_conveyorBeltSpriteSheetCoords[n][0], m_conveyorBeltSpriteSheetCoords[n][1], m_conveyorBeltSpriteSheetCoords[n][2], m_conveyorBeltSpriteSheetCoords[n][3]);
                            break;
                        case RIGHT:
                            subSpriteConveyorBelt = imgCBR.getSubimage(m_conveyorBeltSpriteSheetCoords[n][0], m_conveyorBeltSpriteSheetCoords[n][1], m_conveyorBeltSpriteSheetCoords[n][2], m_conveyorBeltSpriteSheetCoords[n][3]);
                            break;
                        default:
                            break;
                    }
                    g.drawImage(subSpriteConveyorBelt, x * 64+20, y * 64+20, null);
                }

                // Displaying the Bird on the map
                if(m_map.getMap()[x][y].m_type == Entity.Type.BIRD) { // 9
                    subSpriteBird = imgBird.getSubimage(m_birdSpirteSheetCoords[m][0], m_birdSpirteSheetCoords[m][1], m_birdSpirteSheetCoords[m][2], m_birdSpirteSheetCoords[m][3]);
                    g.drawImage(subSpriteBird, x * 64+20, y * 64+20, null);
                }
            }
        }

        try{
            // Displaying the Snoopy on the map
            if(m_snoopy != null) {
                switch(m_snoopy.m_dir){
                    case UP:
                        g.drawImage(subSpriteSnoopyUP, m_snoopy.getX() * 64+20, m_snoopy.getY() * 64+20, null);
                        break;
                    case DOWN:
                        g.drawImage(subSpriteSnoopyDOWN, m_snoopy.getX() * 64+20, m_snoopy.getY() * 64+20, null);
                        break;
                    case LEFT:
                        g.drawImage(subSpriteSnoopyLEFT, m_snoopy.getX() * 64+20, m_snoopy.getY() * 64+20, null);
                        break;
                    case RIGHT:
                        g.drawImage(subSpriteSnoopyRIGHT, m_snoopy.getX() * 64+20, m_snoopy.getY() * 64+20, null);
                        break;
                }
            } 
        }
        catch(Exception e){
            System.out.println("Error while drawing snoopy");
        }
         

        //Drawing Balls
        try{
            if(m_map.getBalls() != null){
                for(Ball ball : m_map.getBalls()){
                    subSpriteBall = imgBall.getSubimage(m_ballSpriteSheetCoords[0][0], m_ballSpriteSheetCoords[0][1], m_ballSpriteSheetCoords[0][2], m_ballSpriteSheetCoords[0][3]);
                    g.drawImage(subSpriteBall, (int) (ball.getBallX() * 64+20), (int) (ball.getBallY()*64+20), null);
                }
            }
        }
        catch(Exception e){
            System.out.println("Error while drawing balls" + e);
        }
    }

    /**
     * Rotating Images 
     * @param image
     * @param angle
     * @see Height 
     * @see Width 
     * @return
     */
    public Image rotatingImage(Image image, double angle){
        BufferedImage bufImage = toBufferedImage(image);
        double rotationRequired = Math.toRadians (angle);
        double anchorx = image.getWidth(null) / 2;
        double anchory = image.getHeight(null) / 2;
        AffineTransform tx = new AffineTransform();

        if(angle == 0 || angle == 180){
            tx.translate(anchorx, anchory);    // S3: final translation
        tx.rotate(rotationRequired);            // S2: rotate around anchor
            tx.translate(-anchorx, -anchory);
        }
        if(angle == 90){
            tx.translate(anchory, anchorx);    // S3: final translation
            tx.rotate(rotationRequired);            // S2: rotate around anchor
            tx.translate(-anchorx, -anchory);
        }
        if(angle == 270){
            tx.translate(anchory, anchorx);    // S3: final translation
            tx.rotate(rotationRequired);            // S2: rotate around anchor
            tx.translate(-anchorx, -anchory);
        }
        

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(bufImage, null);
    }
    /**
     * create a BufferedImage
     * @param img
     * @see Width
     * @see Height
     * @return bufimage
     * 
     */
    public static BufferedImage toBufferedImage(Image img){
        //si l'image est de type BufferedImage 
        //alors on fait seulement le cast
        //parce que l'image passée est de type BufferedImage
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }
        
        BufferedImage bufimage = new BufferedImage(img.getWidth(null),
                          img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        return bufimage;
    }
    /**
     * @return Dimension 
     * @see Dimension 
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1320, 680);
    }
}