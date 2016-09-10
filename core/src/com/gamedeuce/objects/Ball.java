package com.gamedeuce.objects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.gamedeuce.util.Constants;

/**
 * Created by Scott Cao on 2015/5/14.
 */
public class Ball {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    // stores the location of the ball. false = left. true = right.
    private boolean location;
    private boolean canMove;

    private boolean type;

    private int radius;
    private float xBound1;
    private float xBound2;

    // creates a circle that bounds the ball. Used to check for collision.
    private Circle circle;

    // stores the initial location of the ball for the onRestart method
    private boolean initialLocation;

    public Ball(float x1, float x2, float y, int r, boolean al) {
        radius = r;
        location = initialLocation = al;
        xBound1 = x1;
        xBound2 = x2;
        position = new Vector2(0, y);
        setPositionX();
//        Gdx.app.log("Ball", position.x + radius + "");
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        canMove = true;
        circle = new Circle();
    }

    public void update(float delta) {
        // updates velocity of ball based on its acceleration
        velocity.add(acceleration.cpy().scl(delta));
        // updates position of ball based on its velocity
        float newX = position.x + velocity.x * delta;
        if (newX < xBound1) {
            velocity.x = (xBound1 - position.x) / delta;
        } else if (newX > xBound2) {
            velocity.x = (xBound2 - position.x) / delta;
        }
        position.add(velocity.cpy().scl(delta));

        // set the circle's center and radius
        circle.set(position.x + radius, position.y + radius, radius);

        if (location == Constants.BALL_LEFT && position.x <= xBound1 ||
                location == Constants.BALL_RIGHT && position.x >= xBound2) {
            acceleration.x = 0;
            velocity.x = 0;
        }
    }

    public void onTouch() {
        if (canMove) {
            // changes the velocity of the ball to zero so that it instantly changes direction
            // whenever the user taps
            velocity.x = 0;
            if (location == Constants.BALL_RIGHT) {
                // if ball is right, accelerate left
                acceleration.x = -Constants.BALL_ACCELERATION;
//                velocity.x = -Constants.BALL_VELOCITY;
                // Switches the location of the ball from right to left
                location = Constants.BALL_LEFT;
            } else {
                // if ball is left, accelerate right;
                acceleration.x = Constants.BALL_ACCELERATION;
//                velocity.x = Constants.BALL_VELOCITY;
                // Switches the location of the ball from left to right
                location = Constants.BALL_RIGHT;
            }
        }
    }

    public void stop() {
        canMove = false;
        velocity.x = 0;
        acceleration.x = 0;
    }

    private void setPositionX() {
        if (location == Constants.BALL_LEFT) {
            position.x = xBound1;
        } else {
            position.x = xBound2;
        }
    }

    public float getX() {
//        Gdx.app.log("Ball", ""+position.x);
        return position.x;
    }

    public float getY() {
//        Gdx.app.log("Ball", ""+position.y);
        return position.y;
    }

    public float getTailY() {
        return position.y + radius * 2;
    }

    public float getRadius() {
        return radius;
    }

    public boolean getType() {
        return type;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setType(boolean t) {
        type = t;
    }

    public void onRestart() {
        location = initialLocation;
        setPositionX();
        velocity.x = acceleration.x = 0;
        canMove = true;
    }

    public void reverse() {
        type = !type;
    }

}
