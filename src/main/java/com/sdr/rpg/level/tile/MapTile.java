package com.sdr.rpg.level.tile;

import com.sdr.rpg.graphics.Renderer;
import com.sdr.rpg.graphics.Sprite;

/**
 * Created by Konstantin on 16.10.2017.
 */
public class MapTile extends Tile {
    public MapTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, Renderer renderer) {
        renderer.renderTile(x << 5, y << 5, this);
    }
}
