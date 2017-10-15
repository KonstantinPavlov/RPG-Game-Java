package com.sdr.rpg.level.tile;

import com.sdr.rpg.graphics.Renderer;
import com.sdr.rpg.graphics.ScreenRenderer;
import com.sdr.rpg.graphics.Sprite;

/**
 *
 * Created by Konstantin on 25.09.2017.
 */
public abstract class Tile {

    private int x, y;
    private Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile yellow_grass = new GrassTile(Sprite.yellow_grass);
    public static Tile mud = new GrassTile(Sprite.mud);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

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

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Renderer renderer) {
    }

    /**
     * Collision marker
     *
     * @return collideable or not
     */
    public boolean solid() {
        return false;
    }
}
