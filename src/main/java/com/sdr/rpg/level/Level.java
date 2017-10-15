package com.sdr.rpg.level;

import com.sdr.rpg.graphics.Renderer;
import com.sdr.rpg.level.tile.Tile;

/**
 * Basic class for levels
 * <p>
 * Created by Konstantin on 24.09.2017.
 */
public abstract class Level {

    protected int width;
    protected int height;

    // index to draw specific tile
    protected int[] tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
    }

    protected abstract void loadLevel(String path);

    protected abstract void generateLevel();

    public void update() {

    }

    public void render(int xScroll, int yScroll, Renderer screenRenderer) {
        screenRenderer.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screenRenderer.getWidth() + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screenRenderer.getHeight() + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screenRenderer);
            }
        }
    }

    protected abstract Tile getTile(int x, int y);

}
