package com.gamedeuce.objects;

/**
 * Created by Scott Cao on 2015/5/16.
 */
public class ObstaclePair {

    private Obstacle obs1;
    private Obstacle obs2;

    private boolean scored;

    private int distance;

    public ObstaclePair(float xL1, float xL2, float xR1, float xR2, float y,
                        int width, int height, int d, int scrollSpeed) {
        scoreOn();
        distance = d;
        obs1 = new Obstacle(xL1, xL2, y, width, height, scrollSpeed);
        obs2 = new Obstacle(xR1, xR2, y + distance, width, height, scrollSpeed);
    }

    public void update(float delta) {
        obs1.update(delta);
        obs2.update(delta);
    }

    public void reset(float yStart) {
        obs1.reset(yStart);
        obs2.reset(yStart + distance);
    }

    public boolean tooLow(float gameHeight) {
        return obs1.getY() > gameHeight && obs2.getY() > gameHeight;
    }

    public boolean tooHigh() {
        return obs1.getY() + obs1.getHeight() < 0 && obs2.getY() + obs2.getHeight() < 0;
    }

    public boolean passThrough(Ball ball1, Ball ball2) {
        return obs1.passThrough(ball1) && obs2.passThrough(ball2);
    }

    public boolean gameOver(Ball ball1, Ball ball2) {
        return obs1.gameOver(ball1) || obs2.gameOver(ball2);
    }

    public void stop() {
        obs1.stop();
        obs2.stop();
    }

    public Obstacle getObstacle1() {
        return obs1;
    }

    public Obstacle getObstacle2() {
        return obs2;
    }

    public boolean isScored() {
        return scored;
    }

    public void resetCollide() {
        obs1.resetCollided();
        obs2.resetCollided();
    }

    public void scoreOff() {
        scored = true;
    }

    public void scoreOn() {
        scored = false;
    }

    public void incrementSpeed(int add) {
        obs1.incrementSpeed(add);
        obs2.incrementSpeed(add);
    }

    public void restart() {
        obs1.restart();
        obs2.restart();
    }
}

