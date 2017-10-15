package com.sdr.rpg.entity.mob;

import com.sdr.rpg.entity.Entity;
import com.sdr.rpg.graphics.Sprite;

/**
 * Abstract Mob
 * <p>
 * Created by Konstantin on 15.10.2017.
 */
public abstract class Mob extends Entity {

    protected int movementSpeed;

    protected Sprite sprite;
    protected int direction = 2; //
    protected boolean moving = false;

    public void move(int xDirection, int yDirection) {
        // determine direction
        if (xDirection > 0) direction = 1;  // right or east
        if (xDirection < 0) direction = 3;  // left or west

        if (yDirection > 0) direction = 2;  // down or south
        if (yDirection < 0) direction = 0;  // up or north

        if (!collision()) {
            x += xDirection;
            y += yDirection;
        }
    }

    public boolean collision() {
        return false;
    }

    public int getDirection() {
        return direction;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
