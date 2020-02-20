package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;

import java.util.HashSet;
import java.util.Set;

public abstract class Actor implements Drawable {
    private static Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Set<Enum> barrierSet = new HashSet<Enum>() {{
            add(CellType.HOUSE1);
            add(CellType.HOUSE2);
            add(CellType.HOUSE3);
            add(CellType.HOUSE4);
            add(CellType.ENEMY);
            add(CellType.CLOSED);
            add(CellType.CARH1);
            add(CellType.CARV1);
            add(CellType.CARV2);
            add(CellType.CARH2);
            add(CellType.TRUCK1);
            add(CellType.BUILDING1);
            add(CellType.BUILDING2);
            add(CellType.BUILDING3);
            add(CellType.BUILDING4);
            add(CellType.BUILDING6);
            add(CellType.BUILDING7);
            add(CellType.BUILDING8);
            add(CellType.BUILDING9);

        }};
        Cell nextCell = cell.getNeighbor(dx, dy);
        CellType typeOfCell = nextCell.getType();
        if (!barrierSet.contains(typeOfCell)) {
            cell.setActor(null);
            cell.setItem(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public static int getX() {
        return cell.getX();
    }

    public static int getY() {
        return cell.getY();
    }
}
