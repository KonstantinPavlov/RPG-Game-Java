package com.sdr.rpg.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Sprite sheet holder
 *
 * Created by Konstantin on 24.09.2017.
 */
public class SpriteSheet {

    private String path;
    private final int wight;
    private final int height;
    private int[] pixels;

    public static SpriteSheet world = new SpriteSheet("/textures/tiles/world.png",640,576);
    public static SpriteSheet world_32 = new SpriteSheet("/textures/tiles/world_32.png",1280,1152);
    public static SpriteSheet actors = new SpriteSheet("/textures/tiles/actors.png",384,384);

    /**
     * @param path - path to resource with grid-like images
     * @param wight - size of the sheet in pixels
     */
    public SpriteSheet(String path, int wight, int height) {
        this.path = path;
        this.wight = wight;
        this.height = height;

        pixels = new int[this.wight * this.height];
        loadSprite();
    }

    /**
     * Fill the pixels from spriteSheet
     */
    private void loadSprite(){
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int width = image.getWidth();
            int height = image.getHeight();

            image.getRGB(0,0,width,height,pixels,0,width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getWight() {
        return wight;
    }

    public int getHeight() {
        return height;
    }
}
