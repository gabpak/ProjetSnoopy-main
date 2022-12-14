package model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * all game settings
 * @see Dimension
 */
public class Parameters {

    public static final int MAP_SIZE_X = 20;
    public static final int MAP_SIZE_Y = 10;

    public static final int WINDOW_WIDTH = 64 * (MAP_SIZE_X + 2)+90; // 64 * 22 = 1408 (2 lines for the menu)
    public static final int WINDOW_HEIGHT = 64 * MAP_SIZE_Y + 2*20 +40; // 64 * 10 = 640 + 2*20 (2 lines for the timebar) + 40 (for comfort)
    public static final Dimension SIZE_BUTTON = new Dimension(150, 50);
    public static final Dimension SIZE_BUTTON2 = new Dimension(250, 100);

    public static final String PATH_SAVE = "src/ressource/save/";
    public static final String PATH_IMAGE = "src/ressource/image/";
    public static final String PATH_LEVEL = "src/ressource/level/";
    public static final String PATH_SCORE = "src/ressource/score/";
    public static final String PATH_SOUND = "src/ressource/music/";
    
    public static final int[][] twoSpriteSheetCoords = {{0, 0, 64, 64},{64,0,64,64}};
    public static final int[][] snoopySpriteSheetCoords = {{0, 0, 64, 64},{64,0,64,64},{128, 0, 64, 64},{192,0,64,64}};
    public static final int[][] birdSpriteSheetCoords = {{0, 0, 64, 64},{64,0,64,64},{0, 64, 64, 64},{64,64,64,64},{0,128,64,64},{64,128,64,64}};
    public static final int[][] conveyorBeltSpriteSheetCoords = {{0, 0, 64, 64},{0,64,64,64},{64, 0, 64, 64},{64,64,64,64}};
    public static final int[][] ballSpriteSheetCoords = {{0, 0, 64, 64}};

    public static final int[][] timeBarSpriteSheetCoords = {{0,0,64,20},{64,0,64,20},{128,0,64,20},{192,0,64,20},
    {0,20,64,20},{64,20,64,20},{128,20,64,20},{192,20,64,20},
    {0,40,64,20},{64,40,64,20},{128,40,64,20}};


    public static final int[][] timeBarCoords = {{20,0},{84,0},{148,0},{212,0},{276,0},{340,0},{404,0},{468,0},{532,0},{596,0},{660,0},{724,0},{788,0},{852,0},{916,0},{980,0},{1044,0},{1108,0},{1172,0},{1236,0},
    {1300,20},{1300,84},{1300,148},{1300,212},{1300,276},{1300,340},{1300,404},{1300,468},{1300,532},{1300,596},
    {1236,660},{1172,660},{1108,660},{1044,660},{980,660},{916,660},{852,660},{788,660},{724,660},{660,660},{596,660},{532,660},{468,660},{404,660},{340,660},{276,660},{212,660},{148,660},{84,660},{20,660},
    {0,596},{0,532},{0,468},{0,404},{0,340},{0,276},{0,212},{0,148},{0,84},{0,20}};

    public static final int[][] backgroundSpriteSheetCoords = {{0,0,1280,640},{0,640,1280,640},{0,1280,1280,640}};

    public static final int KEY_ESCAPE = 27;
    public static final int KEY_SPACE = 32;

    public static final int KEY_LEFT = 37;
    public static final int KEY_UP = 38;
    public static final int KEY_RIGHT = 39;
    public static final int KEY_DOWN = 40;
    
    public static final int KEY_P = 80;
    public static final int KEY_S = 83;
    public static final int nb_level = 6;

    public static final ArrayList<Entity.Type> BLOCK_PASSABLE = new ArrayList<Entity.Type>(Arrays.asList(Entity.Type.EMPTY,Entity.Type.BLOCK_CONVEYOR_BELT,Entity.Type.BLOCK_VISIBLE_UNVISIBLE_SWITCH,Entity.Type.BLOCK_TRAPPED,Entity.Type.BALL)); 
}
