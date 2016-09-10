package com.gamedeuce.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Scott Cao on 5/15/2015.
 */
public class AssetLoader {

    public static Texture blackBall;
    public static Texture whiteBall;
    public static Texture blackObstacle;
    public static Texture whiteObstacle;
    public static Texture logoText;
    public static TextureRegion logo;
    public static Texture ready;
    public static Texture tapToStart;
    public static Texture gameOver;
    public static Texture highScore;
    public static Texture retry;
    public static Texture background;

    public static BitmapFont font;
    public static BitmapFont shadow;

    public static Preferences prefs;

    public static Sound gameover;

    public static void load() {
        blackBall = new Texture(Gdx.files.internal(Constants.BLACK_BALL_PATH));
        whiteBall = new Texture(Gdx.files.internal(Constants.WHITE_BALL_PATH));
        blackObstacle = new Texture(Gdx.files.internal(Constants.BLACK_OBSTACLE_PATH));
        whiteObstacle = new Texture(Gdx.files.internal(Constants.WHITE_OBSTACLE_PATH));
        logoText = new Texture(Gdx.files.internal(Constants.LOGO_PATH));
        logo = new TextureRegion(logoText,
                0, 0, logoText.getWidth(), logoText.getHeight());
        logo.flip(false, true);
        ready = new Texture(Gdx.files.internal(Constants.READY_PATH));
        tapToStart = new Texture(Gdx.files.internal(Constants.TAP_TO_START_PATH));
        gameOver = new Texture(Gdx.files.internal(Constants.GAME_OVER_PATH));
        highScore = new Texture(Gdx.files.internal(Constants.SCORE_PATH));
        retry = new Texture(Gdx.files.internal(Constants.RETRY_PATH));
        background = new Texture(Gdx.files.internal(Constants.BACKGROUND_PATH));
        font = new BitmapFont(Gdx.files.internal(Constants.FONT_PATH));
        font.getData().setScale(.25f,-.25f);
        shadow = new BitmapFont(Gdx.files.internal(Constants.SHADOW_FONT_PATH));
        shadow.getData().setScale(.25f,-.25f);
        prefs = Gdx.app.getPreferences("Deuce");
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
//        gameover = Gdx.audio.newSound(Gdx.files.internal(Constants.GAME_OVER_MUSIC_PATH));
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    // Must dispose all textures
    public static void dispose() {
        blackBall.dispose();
        whiteBall.dispose();
        whiteObstacle.dispose();
        blackObstacle.dispose();
        logoText.dispose();
        ready.dispose();
        tapToStart.dispose();
        gameOver.dispose();
        highScore.dispose();
        retry.dispose();
        background.dispose();
        font.dispose();
        shadow.dispose();
//        gameover.dispose();
    }
}
