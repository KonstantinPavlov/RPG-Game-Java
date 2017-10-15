package com.sdr.rpg.level;

import com.sdr.rpg.level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Level
 * <p>
 * Created by Konstantin on 15.10.2017.
 */
public class SpawnLevel extends Level {

    private Tile[] tiles;
    private int[] levelPixels;

    public SpawnLevel(int width, int height) {
        super(width, height);
    }

    public SpawnLevel(String path) {
        super(path);
        generateLevel();
    }

    @Override
    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            width = image.getWidth();
            height = image.getHeight();
            tiles = new Tile[width * height];
            levelPixels = new int[width * height];
            image.getRGB(0, 0, width, height, levelPixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not load level file on path : " + path);
        }

    }

    /**
     * Grass - 00FF00
     * Yellow Grass - FFFF00
     * Mud - 9E0B0F
     */
    @Override
    protected void generateLevel() {
        for (int i = 0; i < levelPixels.length; i++) {
            if (levelPixels[i] == 0xFF00FF00)
                tiles[i] = Tile.grass;
            if (levelPixels[i] == 0xFFFFFF00)
                tiles[i] = Tile.yellow_grass;
            if (levelPixels[i] == 0xFF9E0B0F)
                tiles[i] = Tile.mud;
        }
    }

    @Override
    protected Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.voidTile;
        if (x + y * width > tiles.length) {
            return Tile.voidTile;
        }
        return tiles[x + y * width];
    }
}
