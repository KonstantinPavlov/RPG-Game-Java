package com.sdr.rpg;

/**
 * Class that stores main settings of the game
 * Created by Konstantin on 24.09.2017.
 */
public class Settings {
    /**
     * Size of tiles
     */
    public static final int TILE_SIZE = 64;
    public static final int TILE_SIZE_MASK = TILE_SIZE-1;
    public static final int TILE_RENDERING = 8;
    public static final int TILE_RENDERING_MASK = TILE_RENDERING-1;
    /**
     * Main title
     */
    public static final String GAME_TITLE = "Java game";

    public static final int BUFFER_STRATEGY_NUMBER = 3;

    public static final int WHITE_COLOR = 0xFFFFFF;

}
