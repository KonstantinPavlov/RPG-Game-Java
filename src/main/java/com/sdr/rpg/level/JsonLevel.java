package com.sdr.rpg.level;

import com.sdr.rpg.graphics.Renderer;
import com.sdr.rpg.graphics.Sprite;
import com.sdr.rpg.graphics.SpriteSheet;
import com.sdr.rpg.level.tile.MapTile;
import com.sdr.rpg.level.tile.Tile;
import com.sdr.rpg.utils.JSONArray;
import com.sdr.rpg.utils.JSONFile;
import com.sdr.rpg.utils.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Konstantin on 16.10.2017.
 */
public class JsonLevel extends Level {
    // fileds from json
    private static final String LAYER = "layers";
    private static final String LAYER_DATA = "data";
    private static final String LAYER_TYPE = "type";

    private static final String HEIGHT = "height";
    private static final String WIDTH = "width";
    private static final String TILE_HEIGHT = "tileheight";
    private static final String TILE_WIDTH = "tilewidth";

    private static final String TILESET = "tilesets";
    private static final String TILESET_COLUMNS = "columns";
    private static final String TILESET_TILE_COUNT = "tilecount";

    // have ints
    private int[] layer1;
    private int[] layer2;

    private int tileHeight;
    private int tileWidth;

    // row and columns of tileset
    private int columns;
    private int row;

    /**
     * Map for tiles
     */
    private Map<Integer, Tile> tileMap = new HashMap<>();

    public JsonLevel(String path) {
        super(path);
    }

    public JsonLevel(String path, SpriteSheet spriteSheet) {
        super(path);
        loadSprites(spriteSheet);
    }

    private void loadSprites(SpriteSheet spriteSheet) {
        for (int h = 0; h < row; h++) {
            for (int w = 0; w < columns; w++) {
                Sprite sprite = new Sprite(tileWidth, tileHeight, w, h, 0, spriteSheet);
                Tile tile = new MapTile(sprite);
                tileMap.put((w + 1) + (h * 40), tile);
            }
        }

    }

    @Override
    protected void loadLevel(String path) { // "/maps/level_alpha.json"
        JSONObject jsonObject = JSONFile.parseFromResource(path);
        // get layers
        JSONArray layers = jsonObject.getArray(LAYER);
        // for simply 1 layer
        JSONObject layer = layers.getObject(0);
        JSONArray data = layer.getArray(LAYER_DATA);
        layer1 = new int[data.length()];

        for (int j = 0; j < data.length(); j++) {
            layer1[j] = data.getInteger(j);
        }

        JSONObject layer02 = layers.getObject(1);
        data = layer02.getArray(LAYER_DATA);
        layer2 = new int[data.length()];

        for (int j = 0; j < data.length(); j++) {
            layer2[j] = data.getInteger(j);
        }

        tileHeight = jsonObject.getInteger(TILE_HEIGHT);
        tileWidth = jsonObject.getInteger(TILE_WIDTH);

        // widht and height in tiles
        this.width = jsonObject.getInteger(WIDTH);
        this.height = jsonObject.getInteger(HEIGHT);

        JSONArray tilesets = jsonObject.getArray(TILESET);
        JSONObject tileset = tilesets.getObject(0);

        columns = tileset.getInteger(TILESET_COLUMNS);
        row = tileset.getInteger(TILESET_TILE_COUNT)/ columns;

    }

    @Override
    protected void generateLevel() {

    }

    public void render(int xScroll, int yScroll, Renderer screenRenderer) {
        screenRenderer.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screenRenderer.getWidth() + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screenRenderer.getHeight() + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                Tile tile = getTile(x, y);
                if (tile != null)
                    tile.render(x, y, screenRenderer);
            }
        }

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                Tile tile = getTile2(x, y);
                if (tile != null)
                    tile.render(x, y, screenRenderer);
            }
        }

    }

    protected Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        int index = layer1[x  + y * width ];
        if (index == 0) {
            return null;
        }
        return tileMap.get(index);
    }

    protected Tile getTile2(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        int index = layer2[x  + y * width ];
        if (index == 0) {
            return null;
        }
        return tileMap.get(index);
    }


}
