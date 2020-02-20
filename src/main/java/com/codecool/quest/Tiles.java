package com.codecool.quest;

import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.actors.Player;
import javafx.scene.Camera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 64;

    private static Image tileset = new Image("/tiles.png", 543 * 4, 543 * 4, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 4);
            y = j * (TILE_WIDTH + 4);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("road", new Tile(0, 0));
        tileMap.put("wall", new Tile(8, 0));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(11, 22));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("pistol", new Tile(5, 31));
        tileMap.put("closedDoor", new Tile(6, 16));
        tileMap.put("openedDoor", new Tile(9, 16));
        tileMap.put("house1", new Tile(1, 20));
        tileMap.put("house2", new Tile(1, 21));
        tileMap.put("house3", new Tile(3, 21));
        tileMap.put("house4", new Tile(8, 20));
        tileMap.put("pavement1", new Tile(10, 17));
        tileMap.put("pavement2", new Tile(16, 0));
        tileMap.put("tree", new Tile(0, 1));
        tileMap.put("truck1", new Tile(12, 20));
        tileMap.put("truck2", new Tile(13, 20));
        tileMap.put("carh1", new Tile(11, 23));
        tileMap.put("carh2", new Tile(12, 23));
        tileMap.put("carv1", new Tile(14, 22));
        tileMap.put("carv2", new Tile(14, 23));
        tileMap.put("building1", new Tile(0, 16));
        tileMap.put("building2", new Tile(1, 16));
        tileMap.put("building3", new Tile(2, 16));
        tileMap.put("building4", new Tile(0, 17));
        tileMap.put("building5", new Tile(1, 17));
        tileMap.put("building6", new Tile(2, 17));
        tileMap.put("building7", new Tile(0, 18));
        tileMap.put("building8", new Tile(1, 18));
        tileMap.put("building9", new Tile(2, 18));
        tileMap.put("money", new Tile(11, 26));
        tileMap.put("enemy", new Tile(11, 20));
        tileMap.put("gun", new Tile(8, 31));
        tileMap.put("flower", new Tile(15, 6));
        tileMap.put("water", new Tile(8, 5));
        tileMap.put("ground", new Tile(2, 0));
        tileMap.put("duck", new Tile(25, 7));


    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH - Player.getX() * TILE_WIDTH / 1.4, y * TILE_WIDTH - Player.getY() * TILE_WIDTH / 2.0, TILE_WIDTH, TILE_WIDTH);
    }
}
