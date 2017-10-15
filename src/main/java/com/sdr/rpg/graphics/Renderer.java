package com.sdr.rpg.graphics;

import com.sdr.rpg.entity.mob.Player;
import com.sdr.rpg.level.tile.Tile;

/**
 * Created by Konstantin on 24.09.2017.
 */
public interface Renderer {

    int getWidth();

    void setWidth(int width);

    int getHeight();

    void setHeight(int height);

    int[] getPixels();

    void clear();
    void setOffset (int xOffset, int yOffset);
    void renderTile(int xPosition, int yPosition, Tile tile);
    void renderPlayer(int xPosition, int yPosition, Sprite sprite);
}
