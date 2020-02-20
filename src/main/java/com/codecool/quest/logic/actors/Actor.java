package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;

public abstract class Actor implements Drawable {
    private static Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        CellType typeOfCell = nextCell.getType();
        if (typeOfCell != CellType.HOUSE1 && typeOfCell != CellType.CARH1 && typeOfCell != CellType.CLOSED && typeOfCell != CellType.ENEMY) {
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
