package com.gamedeuce.util;

/**
 * Created by Scott Cao on 12/15/2015.
 */
public class Constants {

    /*
    *  APP
    */
    public static final String APP_TITLE = "CYCLE";
    public static final int APP_WIDTH = 400;
    public static final int APP_HEIGHT = 600;


    /*
    *  BALL
    */
    public static final int BALL_RADIUS = 30;
    public static final boolean BALL_LEFT = false;
    public static final boolean BALL_RIGHT = true;
    public static final int BALL_ACCELERATION = 5000;
//    public static final int BALL_VELOCITY = 500;


    /*
    *  OBSTACLES
    */
    public static final int OBS_SIZE = 30;
    public static final int OBS_START_Y = -150 - OBS_SIZE;
    public static final int OBS_SCROLL_SPEED = 300;
    public static final int OBS_SCROLL_SPEED_INCREMENT = 1;
    // the distance between each set of obstacles (between 200 and 220)
    public static final int OBS_SET_DISTANCE = APP_HEIGHT / 3 + (int) (Math.random() * 50 + 50);
    // the distance between the left and right obstacle in each set
    // the value is either -20 (where right obstacle is in front of the left obstacle),
    // 0 (where two obstacles are equally far apart),
    // or 20 (where left obstacle is in front of the right obstacle)
    public static final int OBS_LEFT_RIGHT_DISTANCE = (Math.random() > 0.5 ? -50 : 50);


    /*
    * TYPES/COLORS
    */
    public static final boolean TYPE_BLACK = false;
    public static final boolean TYPE_WHITE = true;


    /*
    *  PATHS
    */
    public static final String BLACK_BALL_PATH = "Black Ball.png";
    public static final String WHITE_BALL_PATH = "White Ball.png";
    public static final String BLACK_OBSTACLE_PATH = "Black Square.png";
    public static final String WHITE_OBSTACLE_PATH = "White Square.png";
    public static final String FONT_PATH = "text.fnt";
    public static final String SHADOW_FONT_PATH = "shadow.fnt";
    public static final String LOGO_PATH = "deuce.png";
    public static final String READY_PATH = "ready.png";
    public static final String TAP_TO_START_PATH = "tap to start.png";
    public static final String GAME_OVER_PATH = "gameover.png";
    public static final String SCORE_PATH = "score.png";
    public static final String RETRY_PATH = "retry.png";
    public static final String GAME_OVER_MUSIC_PATH = "gameover.wav";
    public static final String BACKGROUND_PATH = "background2.png";

    /*
    *  SCORING
    */
    public static final int SCORE_INCREMENT = 1;
    // increment the speed of the obstacles once the player reaches a multiple of this score
    public static final int LEVEL_UP_SCORE = 20;

}
