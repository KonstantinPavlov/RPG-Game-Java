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
            tiles = new int[width * height];
            image.getRGB(0, 0, width, height, tiles, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not load level file on path : " + path);
        }

    }

    @Override
    protected void generateLevel() {

    }

}
