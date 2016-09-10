package com.gamedeuce.objects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Scott Cao on 2015/5/15.
 */
public abstract class Scrollable {

    private Vector2 position;
    private Vector2 velocity;
    private int width;
    private int height;
    private float scrollSpeed;
    private int add;

    public Scrollable(int w, int h, float spd) {
        position = new Vector2(0, 0);
        velocity = new Vector2(0, spd);
        width = w;
        height = h;
        scrollSpeed = spd;
        add = 0;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
    }

    // Reset: Should Override in subclass for more specific behavior.
    public abstract void reset(float newY);

    public void stop() { velocity.y = 0; }

    public void resetSpeed() { velocity.y = scrollSpeed + add; }

    public void restart() {
        add = 0;
        resetSpeed();
    }

    public void incrementSpeed(int a) {
        add = a;
        resetSpeed();
    }

    public float getTailY() { return position.y + height; }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    protected void setX(float a) { position.x = a; }

    protected void setY(float b) { position.y = b; }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
