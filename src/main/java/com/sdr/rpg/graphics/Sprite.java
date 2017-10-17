package com.sdr.rpg.graphics;

/**
 * Created by Konstantin on 24.09.2017.
 */
public class Sprite {

    private final int SIZE;
    private final int x_SIZE;
    private final int y_SIZE;
    // coordinates of sprite in sheet like 0,0 or 1,2  starting from 0
    private int x, y;
    private int[] pixels;

    private int mask = 0; // mask for images

    private SpriteSheet spriteSheet;
    /**
     * World sprites
     */
    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.world);
    public static Sprite yellow_grass = new Sprite(16, 1, 4, SpriteSheet.world);
    public static Sprite mud = new Sprite(16, 12, 13, SpriteSheet.world);

    public static Sprite voidSprite = new Sprite(16, 0x1B87E0);
    public static Sprite voidSprite32 = new Sprite(32, 0x1B87E0);

    /**
     * Player sprites
     */
    public static Sprite player_oldman_down = new Sprite(32,48,10,4,0,SpriteSheet.actors);
    public static Sprite player_oldman_down_1 = new Sprite(32,48,9,4,0,SpriteSheet.actors);
    public static Sprite player_oldman_down_2 = new Sprite(32,48,11,4,0,SpriteSheet.actors);

    public static Sprite player_oldman_left = new Sprite(32,48,10,5,0,SpriteSheet.actors);
    public static Sprite player_oldman_left_1 = new Sprite(32,48,9,5,0,SpriteSheet.actors);
    public static Sprite player_oldman_left_2 = new Sprite(32,48,11,5,0,SpriteSheet.actors);

    public static Sprite player_oldman_right = new Sprite(32,48,10,6,0,SpriteSheet.actors);
    public static Sprite player_oldman_right_1 = new Sprite(32,48,9,6,0,SpriteSheet.actors);
    public static Sprite player_oldman_right_2 = new Sprite(32,48,11,6,0,SpriteSheet.actors);

    public static Sprite player_oldman_up = new Sprite(32,48,10,7,0,SpriteSheet.actors);
    public static Sprite player_oldman_up_1 = new Sprite(32,48,9,7,0,SpriteSheet.actors);
    public static Sprite player_oldman_up_2 = new Sprite(32,48,11,7,0,SpriteSheet.actors);


    /**
     * Create sprite from spriteSheet
     *
     * @param size        - size of sprite  example 16x16
     * @param x           - x coordinate of sprite in sheet starts from 0
     * @param y           - y coordinate of sprite in sheet starts from 0
     * @param spriteSheet - @see SpriteSheet
     */
    public Sprite(int size, int x, int y, SpriteSheet spriteSheet) {
        this.SIZE = size;
        this.x_SIZE = SIZE;
        this.y_SIZE = SIZE;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.spriteSheet = spriteSheet;
        loadSprite();
    }

    public Sprite(int size, int x, int y, int mask ,SpriteSheet spriteSheet) {
        this.SIZE = size;
        this.x_SIZE = SIZE;
        this.y_SIZE = SIZE;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.spriteSheet = spriteSheet;
        this.mask = mask;
        loadSprite();
    }

    public Sprite(int xSize, int ySize, int x, int y, int mask ,SpriteSheet spriteSheet) {
        this.SIZE = xSize;
        this.x_SIZE = xSize;
        this.y_SIZE = ySize;
        pixels = new int[x_SIZE * y_SIZE];
        this.x = x * xSize;
        this.y = y * ySize;
        this.spriteSheet = spriteSheet;
        this.mask = mask;
        loadSprite();
    }


    public Sprite(int size, int colour) {
        this.SIZE = size;
        this.x_SIZE = SIZE;
        this.y_SIZE = SIZE;
        pixels = new int[SIZE * SIZE];
        setColour(colour);
    }

    private void setColour(int colour) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            pixels[i] = colour;
        }
    }

    private void loadSprite() {
        for (int y = 0; y < y_SIZE; y++) {
            for (int x = 0; x < x_SIZE; x++) {
                try {
                    pixels[x + y * x_SIZE] = spriteSheet.getPixels()[(x + this.x) + (y + this.y) * spriteSheet.getWight()];
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("opps");
                }
            }
        }
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getSize() {
        return SIZE;
    }

    public int getMask() {
        return mask;
    }

    public int getXSize() {
        return x_SIZE;
    }

    public int getYSize() {
        return y_SIZE;
    }
}


