package com.sdr.rpg.level;

import com.sdr.rpg.level.tile.Tile;

import java.util.Random;

/**
 * Simple random Level
 * <p>
 * Created by Konstantin on 25.09.2017.
 */
public class RandomLevel extends Level {

    private final static Random random = new Random();

    public RandomLevel(int width, int height) {
        super(width, height);
    }

    @Override
    protected void loadLevel(String path) {

    }

    @Override
    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x + y * width] = random.nextInt(4);
            }
        }
    }

    @Override
    protected Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        if (tiles[x + y * width] == 0) return Tile.grass; // returning grass Tile
        if (tiles[x + y * width] == 1) return Tile.yellow_grass; // returning grass Tile
        if (tiles[x + y * width] == 2) return Tile.mud; // returning grass Tile
        if (tiles[x + y * width] == 3) return Tile.grass; // returning grass Tile
        return Tile.voidTile;
    }
}
