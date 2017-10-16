package com.sdr.rpg.graphics;

import com.sdr.rpg.Settings;
import com.sdr.rpg.level.tile.Tile;

import java.awt.*;
import java.util.Random;

/**
 * Renderer class
 * Created by Konstantin on 24.09.2017.
 */
public class ScreenRenderer implements Renderer {

    private int width, height;
    private int[] pixels;

    public int xOffset, yOffset;

    public int[] tiles = new int[Settings.TILE_SIZE * Settings.TILE_SIZE];

    private Random random = new Random();

    public ScreenRenderer(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = random.nextInt(Settings.WHITE_COLOR);
        }

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getPixels() {
        return pixels;
    }


    /**
     * Clear the screen
     * Filling pixels[] by Color.black
     *
     * @see Color
     */
    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = Color.black.getRGB();
        }
    }

    /**
     * Method for rendering Tiles
     *
     * @param xPosition - x position
     * @param yPosition - y position
     */
    public void renderTile(int xPosition, int yPosition, Tile tile) {

        xPosition -= xOffset;
        yPosition -= yOffset;

        for (int y = 0; y < tile.getSprite().getSize(); y++) {
            int yAbsolute = y + yPosition;
            for (int x = 0; x < tile.getSprite().getSize(); x++) {
                int xAbsolute = x + xPosition;
                // do not render tile if it out of screen
                if (xAbsolute < -tile.getSprite().getSize() || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height)
                    break;
                if (xAbsolute < 0) xAbsolute = 0;
                // add mask
                int colour = tile.getSprite().getPixels()[x + y * tile.getSprite().getSize()];
                if (colour != tile.getSprite().getMask())
                    pixels[xAbsolute + yAbsolute * width] = colour;
            }

        }
    }

    @Override
    public void renderPlayer(int xPosition, int yPosition, Sprite sprite) {
        xPosition -= xOffset;
        yPosition -= yOffset;

        for (int y = 0; y < sprite.getYSize(); y++) {
            int yAbsolute = y + yPosition;
            for (int x = 0; x < sprite.getXSize(); x++) {
                int xAbsolute = x + xPosition;
                // do not render tile if it out of screen
                if (xAbsolute < -sprite.getXSize() || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;
                if (xAbsolute < 0) xAbsolute = 0;
                int col = sprite.getPixels()[x + y * sprite.getSize()];
                if (col != sprite.getMask()) // alpha chanel for sprite
                    pixels[xAbsolute + yAbsolute * width] = col;
            }

        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
