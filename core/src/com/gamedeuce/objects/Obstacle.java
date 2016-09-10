package com.gamedeuce.objects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Scott Cao on 2015/5/16.
 */
public class Obstacle extends Scrollable {

    private float xBound1;
    private float xBound2;

    private boolean type;
    // used for the gameOver method
    private boolean collided;
    private boolean gameOver;

    private double chance = 0.5;

    private Rectangle rect1;
    private Rectangle rect2;

    public Obstacle(float x1, float x2, float y, int w, int h, float scrollSpeed) {
        super(w, h, scrollSpeed);
        xBound1 = x1;
        xBound2 = x2;
        setX();
        setY(y);
        setType();
        rect1 = new Rectangle(x1, y, w, h);
        rect2 = new Rectangle(x2, y, w, h);
        collided = false;
//        Gdx.app.log("Obstacle", getX()+w/2+"");
    }

    @Override
    public void reset(float newY) {
        setX();
        setY(newY);
        setType();
        resetSpeed();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        updateRectangles();
    }

    private void updateRectangles() {
        float y = getY();
        rect1.setY(y);
        rect2.setY(y);
    }

    private void setX() {
        if (Math.random() < 0.5) {
            super.setX(xBound1);
        } else {
            super.setX(xBound2);
        }
    }

    private void setType() {
        if (Math.random() > chance) {
            type = !type;
            chance = 0.5;
        } else {
            chance *= 0.5;
        }
    }

    public boolean getType() {
        return type;
    }

    public boolean passThrough(Ball ball) {
        return ball.getY() + 2 * ball.getRadius() < getY();
    }

    public boolean gameOver(Ball ball) {
        if (ball.getY() > getTailY()) {
            gameOver = false;
        }
        else if (Intersector.overlaps(ball.getCircle(), getRect())) {
//            Gdx.app.log("Game Over 1", getRect().getX()+" "+getRect().getY()
//                    +", "+(type == Constants.TYPE_BLACK? "black" : "white")
//                    +"; "+getInvisibleRect().getX()+" "+getInvisibleRect().getY()
//                    +"; "+ball.getCircle().x+" "+ball.getCircle().y
//                    +", "+(ball.getType() == Constants.TYPE_BLACK? "black" : "white"));
            collided = true;
            if (ball.getType() != type) {
                gameOver = true;
            }
        }
        else if (passThrough(ball)) {
            if(!collided && ball.getType() == type) {
                gameOver = true;
            }
        }
        return gameOver;
    }

    private Rectangle getRect() {
        if (getX() == xBound1) {
            return rect1;
        }
        return rect2;
    }

    public void resetCollided() { collided = false; }

    // checked by GameRenderer. If the ball collided with square of same color, the square would
    // disappear instead of passing through
    public boolean drawable(Ball ball) {
        return !(collided && getTailY() >= ball.getY()) || gameOver;
    }

}

