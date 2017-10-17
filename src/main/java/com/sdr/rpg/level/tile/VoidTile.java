package com.sdr.rpg.level.tile;

import com.sdr.rpg.graphics.Renderer;
import com.sdr.rpg.graphics.Sprite;

/**
 * Created by Konstantin on 15.10.2017.
 */
public class VoidTile extends Tile {

    public VoidTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, Renderer renderer) {
        renderer.renderTile(x << 5, y << 5, this);
    }
}
