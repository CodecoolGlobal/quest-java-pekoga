package com.codecool.quest;

import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.actors.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 64;

    private static Image tileset = new Image("/tiles.png", 543 * 4, 543 * 4, true, false);
    private static Image tileset_horizontal = new Image("/tiles_horizontal.png", 543 * 4, 543 * 4, true, false);
    private static Image tileset_rotated = new Image("/tiles_rotated.png", 543 * 4, 543 * 4, true, false);
    private static Image tileset_rotated_vertical = new Image("/tiles_rotated_vertical.png", 543 * 4, 543 * 4, true, false);
    private static Image tileset_vertical = new Image("/tiles_vertical.png", 543 * 4, 543 * 4, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;
        public final Image source;
        Tile(int i, int j, Image sourceFile) {
            x = i * (TILE_WIDTH + 4);
            y = j * (TILE_WIDTH + 4);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
            source = sourceFile;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0, tileset));
        tileMap.put("road", new Tile(0, 0, tileset));
        tileMap.put("wall", new Tile(8, 0, tileset));
        tileMap.put("floor", new Tile(2, 0, tileset));
        tileMap.put("player", new Tile(11, 22, tileset));
        tileMap.put("skeleton", new Tile(29, 6, tileset));
        tileMap.put("pistol", new Tile(5, 31, tileset));
        tileMap.put("closedDoor", new Tile(6, 16, tileset));
        tileMap.put("openedDoor", new Tile(9, 16, tileset));
        tileMap.put("house1", new Tile(1, 20, tileset));
        tileMap.put("house2", new Tile(1, 21, tileset));
        tileMap.put("house3", new Tile(3, 21, tileset));
        tileMap.put("house4", new Tile(8, 20, tileset));
        tileMap.put("pavement1", new Tile(10, 17, tileset));
        tileMap.put("pavement2", new Tile(16, 0, tileset));
        tileMap.put("tree", new Tile(0, 1, tileset));
        tileMap.put("truck1", new Tile(12, 20, tileset));
        tileMap.put("truck2", new Tile(13, 20, tileset));
        tileMap.put("carh1", new Tile(11, 23, tileset));
        tileMap.put("carh2", new Tile(12, 23, tileset));
        tileMap.put("carv1", new Tile(14, 22, tileset));
        tileMap.put("carv2", new Tile(14, 23, tileset));
        tileMap.put("building1", new Tile(0, 16, tileset));
        tileMap.put("building2", new Tile(1, 16, tileset));
        tileMap.put("building3", new Tile(2, 16, tileset));
        tileMap.put("building4", new Tile(0, 17, tileset));
        tileMap.put("building5", new Tile(1, 17, tileset));
        tileMap.put("building6", new Tile(2, 17, tileset));
        tileMap.put("building7", new Tile(0, 18, tileset));
        tileMap.put("building8", new Tile(1, 18, tileset));
        tileMap.put("building9", new Tile(2, 18, tileset));
        tileMap.put("money", new Tile(11, 26, tileset));
        tileMap.put("enemy", new Tile(11, 20, tileset));
        tileMap.put("gun", new Tile(8, 31, tileset));
        tileMap.put("flower", new Tile(15, 6, tileset));
        tileMap.put("water", new Tile(8, 5, tileset));
        tileMap.put("ground", new Tile(2, 0, tileset));
        tileMap.put("duck", new Tile(25, 7, tileset));
        tileMap.put("dog", new Tile(31, 7, tileset));
        tileMap.put("pedestrian", new Tile(30,3, tileset));
        // Rotated tiles from different source
        tileMap.put("carh1_horizontal", new Tile(20, 23, tileset_horizontal));
        tileMap.put("carv1_vertical", new Tile(14, 9, tileset_vertical));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tile.source, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH - Player.getX() * TILE_WIDTH / 1.4, y * TILE_WIDTH - Player.getY() * TILE_WIDTH / 2.0, TILE_WIDTH, TILE_WIDTH);
    }
}
