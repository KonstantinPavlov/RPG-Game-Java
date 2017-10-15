package com.sdr.rpg.entity;

import com.sdr.rpg.graphics.Renderer;
import com.sdr.rpg.level.Level;

import java.util.Random;

/**
 * Created by Konstantin on 15.10.2017.
 */
public abstract class Entity {

    protected int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isRemoved() {
        return removed;
    }

    public abstract void update();

    public abstract void render(Renderer renderer);

    public void remove() {
        // remove from Level
        this.removed = true;
    }
}
