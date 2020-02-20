package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.Set;

public abstract class Actor implements Drawable {
    private static Cell cell;
    private int health = 10;
    private boolean hasKey = false;
    private boolean hasGun = false;
    private boolean hasPistol = false;
    private int money = 0;
    private String inventory = "";


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
            add(CellType.TRUCK2);
            add(CellType.TREE);
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

    public boolean getHasKey() {
        return hasKey;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public boolean getHasGun() {
        return hasGun;
    }

    public void setHasGun(boolean hasGun) {
        this.hasGun = hasGun;
    }

    public boolean getHasPistol() {
        return hasPistol;
    }

    public void setHasPistol(boolean hasPistol) {
        this.hasPistol = hasPistol;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory() {
        inventory = "";
        if (hasGun) {
            inventory += "GUN\n";
        }
        if (hasKey) {
            inventory += "KEY\n";
        }
        if (hasPistol) {
            inventory += "PISTOL\n";
        }
    }

    public void pickUpItem(String itemType, int dx, int dy) {
        if (getCell().getNeighbor(dx, dy).getItem() != null) {
            if (getCell().getNeighbor(dx, dy).getItem().getTileName().equals(itemType)) {
                switch (itemType) {
                    case "gun":
                        setHasGun(true);
                        break;
                    case "pistol":
                        setHasPistol(true);
                        break;
                    case "key":
                        setHasKey(true);
                        break;
                }
                setInventory();
            }
        }
    }
}
