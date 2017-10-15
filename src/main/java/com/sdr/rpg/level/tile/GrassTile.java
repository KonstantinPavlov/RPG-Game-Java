package com.sdr.rpg.level.tile;

import com.sdr.rpg.graphics.Renderer;
import com.sdr.rpg.graphics.Sprite;

/**
 * Created by Konstantin on 25.09.2017.
 */
public class GrassTile extends Tile {

    public GrassTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, Renderer renderer) {
        renderer.renderTile(x << 4, y << 4, this);
    }
}
