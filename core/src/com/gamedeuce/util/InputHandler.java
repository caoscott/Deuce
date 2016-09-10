package com.gamedeuce.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.gamedeuce.objects.Ball;
import com.gamedeuce.world.GameWorld;

/**
 * Created by Scott Cao on 2015/5/14.
 */
public class InputHandler implements InputProcessor {

    private float screenWidth;
    private float screenHeight;

    private GameWorld world;

    private Ball ball1;
    private Ball ball2;

    public InputHandler(float sw, float sh, GameWorld w) {
        screenWidth = sw;
        screenHeight = sh;
        world = w;
        ball1 = world.getBall1();
        ball2 = world.getBall2();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        Gdx.app.log("Input", screenX+" "+screenY);
//        Gdx.app.log("sw", ""+screenWidth);
        if (world.isReady()) {
            world.start();
        } else if (world.isGameOver()) {
            // Reset all variables, go to GameState.READ
            world.restart();
        } else if (screenX < Gdx.graphics.getWidth() / 2) {
            ball1.onTouch();
        } else {
            ball2.onTouch();
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

