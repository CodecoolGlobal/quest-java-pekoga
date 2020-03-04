package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Pedestrian;
import com.codecool.quest.logic.actors.Player;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private Pedestrian pedestrian;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        try {
            return cells[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            if (x >= width) {
                x -= 1;
            } else if (x <= 0) {
                x += 1;
            } else if (y >= height) {
                y -= 1;
            } else if (y <= 0) {
                y += 1;
            }
            return cells[x][y];
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Pedestrian getPedestrian() {
        return pedestrian;
    }

    public void setPedestrian(Pedestrian pedestrian) {
        this.pedestrian = pedestrian;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
