package com.gamedeuce.util;

import com.gamedeuce.objects.Ball;
import com.gamedeuce.objects.ObstaclePair;

/**
 * Created by Scott Cao on 2015/5/16.
 */
public class ScrollHandler {

    private float gameHeight;
    private float yStart;
    private float tempY;

    private int score;
    private int setDistance;

    private ObstaclePair[] obstaclePairs;

    public ScrollHandler(float xBoundL1, float xBoundL2, float xBoundR1, float xBoundR2, int obsSize,
                         float gh, float ySt, int setDist, int leftRightDist, int scrollSpeed) {
        score = 0;
        gameHeight = gh;
        setDistance = setDist;
        yStart = ySt;
        tempY = yStart;
        obstaclePairs = new ObstaclePair[3];
        for (int pos = 0; pos < obstaclePairs.length; pos++) {
            obstaclePairs[pos] = new ObstaclePair(xBoundL1, xBoundL2, xBoundR1, xBoundR2, tempY,
                    obsSize, obsSize, leftRightDist, scrollSpeed);
            tempY -= setDistance;
        }
    }

    public void update(float delta) {
        for (ObstaclePair op: obstaclePairs) {
            op.update(delta);
        }

        ObstaclePair op1 = obstaclePairs[0];
        if (op1.tooLow(gameHeight)) {
            op1.reset(yStart);
            cycle();
        }
    }

    public boolean gameOver(Ball ball1, Ball ball2) {
        return obstaclePairs[0].gameOver(ball1, ball2);
    }

    public boolean passThrough(Ball ball1, Ball ball2) {
        return obstaclePairs[0].passThrough(ball1, ball2);
    }

    public void incrementScore(int add) {
        ObstaclePair op = obstaclePairs[0];
        if (!op.isScored()) {
            score += add;
            op.scoreOff();
        }
    }

    // shift the obstacle pairs so that the lowest pair is at the front of the array
    private void cycle() {
        obstaclePairs[0].scoreOn();
        obstaclePairs[0].resetCollide();

        ObstaclePair temp = obstaclePairs[0];
        obstaclePairs[0] = obstaclePairs[1];
        obstaclePairs[1] = obstaclePairs[2];
        obstaclePairs[2] = temp;
    }

    public void stop() {
        for (ObstaclePair op: obstaclePairs) {
            op.stop();
        }
    }

    public void onRestart() {
        score = 0;
        obstaclePairs[0].scoreOn();
        obstaclePairs[0].resetCollide();
        tempY = yStart;
        for (ObstaclePair op: obstaclePairs) {
            op.reset(tempY);
            op.restart();
            tempY -= setDistance;
        }
    }

    public ObstaclePair[] getObstaclePairs() {
        return obstaclePairs;
    }

    public int getScore() {
        return score;
    }

    public void incrementSpeed(int add) {
        for (ObstaclePair op: obstaclePairs) {
            op.incrementSpeed(add);
        }
    }

}
