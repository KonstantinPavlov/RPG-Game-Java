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
    protected int[] tilesInt;
    protected int[] tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
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
                Tile tile = getTile(x, y);
                if (tile != null)
                    tile.render(x, y, screenRenderer);
            }
        }
    }

    protected Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        if (tiles[x + y * width] == 0xFF00FF00) return Tile.grass; // returning grass Tile
        if (tiles[x + y * width] == 0xFFFFFF00) return Tile.yellow_grass;
        if (tiles[x + y * width] == 0xFF9E0B0F) return Tile.mud;
        return Tile.voidTile;
    }

}
