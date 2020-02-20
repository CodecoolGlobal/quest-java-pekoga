package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;

import java.util.ArrayList;

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
