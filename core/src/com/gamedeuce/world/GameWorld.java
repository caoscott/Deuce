package com.gamedeuce.world;

import com.gamedeuce.objects.Ball;
import com.gamedeuce.util.AssetLoader;
import com.gamedeuce.util.Constants;
import com.gamedeuce.util.ScrollHandler;

/**
 * Created by Scott Cao on 2015/5/14.
 */
public class GameWorld {

    private Ball ball1;
    private Ball ball2;

    private ScrollHandler scroller;

    private GameState currentState;

    public enum GameState {
        READY, RUNNING, GAMEOVER
    }

    public GameWorld(float gameWidth, float gameHeight) {
        currentState = GameState.READY;
        float xBoundL1 = gameWidth / 8 - Constants.BALL_RADIUS;
        float xBoundL2 = 3 * gameWidth / 8 - Constants.BALL_RADIUS;
        float xBoundR1 = 5 * gameWidth / 8 - Constants.BALL_RADIUS;
        float xBoundR2 = 7 * gameWidth / 8 - Constants.BALL_RADIUS;
        float yBall = 4 * gameHeight / 5 - Constants.BALL_RADIUS;
        ball1 = new Ball(xBoundL1, xBoundL2, yBall, Constants.BALL_RADIUS,
                Constants.BALL_LEFT);
        ball2 = new Ball(xBoundR1, xBoundR2, yBall, Constants.BALL_RADIUS,
                Constants.BALL_RIGHT);
        setBallTypes();
        xBoundL1 = gameWidth / 8 - Constants.OBS_SIZE / 2;
        xBoundL2 = 3 * gameWidth / 8 - Constants.OBS_SIZE / 2;
        xBoundR1 = 5 * gameWidth / 8 - Constants.OBS_SIZE / 2;
        xBoundR2 = 7 * gameWidth / 8 - Constants.OBS_SIZE / 2;
        scroller = new ScrollHandler(xBoundL1, xBoundL2, xBoundR1, xBoundR2, Constants.OBS_SIZE,
                gameHeight, Constants.OBS_START_Y, Constants.OBS_SET_DISTANCE,
                Constants.OBS_LEFT_RIGHT_DISTANCE, Constants.OBS_SCROLL_SPEED);
    }

    private void setBallTypes() {
        if (Math.random() < 0.5) {
            ball1.setType(Constants.TYPE_BLACK);
            ball2.setType(Constants.TYPE_WHITE);
        } else {
            ball1.setType(Constants.TYPE_WHITE);
            ball2.setType(Constants.TYPE_BLACK);
        }
    }

    public void update(float delta) {
//        switch (currentState) {
//            case READY:
//                updateReady(delta);
//                break;
//            case RUNNING:
//                updateRunning(delta);
//                break;
//            default:
//                break;
//        }
        if (currentState == GameState.RUNNING) {
            updateRunning(delta);
        }
    }

    private void updateRunning(float delta) {
        ball1.update(delta);
        ball2.update(delta);
        scroller.update(delta);

        if (scroller.gameOver(ball1, ball2)) {
            stop();
            currentState = GameState.GAMEOVER;
//            AssetLoader.gameover.play();
            int score = getScore();
            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
            }
        } else if (scroller.passThrough(ball1, ball2)) {
            scroller.incrementScore(Constants.SCORE_INCREMENT);
            if (getScore() % Constants.LEVEL_UP_SCORE == 0) {
                scroller.incrementSpeed(Constants.OBS_SCROLL_SPEED_INCREMENT);
            }
        }
    }

//    private void updateReady(float delta) {
//
//    }

    private void stop() {
        scroller.stop();
        ball1.stop();
        ball2.stop();
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        ball1.onRestart();
        ball2.onRestart();
        setBallTypes();
        scroller.onRestart();
        currentState = GameState.RUNNING;
    }

    public Ball getBall1() {
        return ball1;
    }

    public Ball getBall2() {
        return ball2;
    }

    public int getScore() {
        return scroller.getScore();
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

}

