package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.items.Money;

import java.util.*;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 100;
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
        Set<Enum> barrierSet = new HashSet<>() {{
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

        if (getCell().getNeighbor(dx, dy).getActor() != null) {
            return;
        }

        if (!barrierSet.contains(typeOfCell)) {
            cell.setActor(null);
            if (cell.getItem() != null && cell.getItem().getTileName().equals("money")) {
            } else if (cell.getItem() != null && cell.getItem().getTileName().equals("pistol")) {
            } else if (cell.getItem() != null && cell.getItem().getTileName().equals("gun")) {
            } else {
                cell.setItem(null);
            }
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

    public int getX() {
        return cell.getX();
    }

    public int getY() {
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
        this.money += money;
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
                        getCell().getNeighbor(dx, dy).setItem(null);
                        break;
                    case "pistol":
                        setHasPistol(true);
                        getCell().getNeighbor(dx, dy).setItem(null);
                        break;
                    case "key":
                        setHasKey(true);
                        getCell().getNeighbor(dx, dy).setItem(null);
                        break;
                }
                setInventory();
            }
        }
    }

    public void pickUpMoney(int dx, int dy) {
        if (getCell().getNeighbor(dx, dy).getItem() != null) {
            if (getCell().getNeighbor(dx, dy).getItem().getTileName().equals("money")) {
                setMoney(100);
                getCell().getNeighbor(dx, dy).setItem(null);
            }
        }
    }

    public void hitPedestrian(int dx, int dy) {
        if (getCell().getNeighbor(dx, dy).getActor() != null) {
            if (getCell().getNeighbor(dx, dy).getActor().getTileName().equals("pedestrian")) {
                getCell().getNeighbor(dx, dy).setActor(null);
                Money money = new Money(getCell().getNeighbor(dx, dy));
                getCell().getNeighbor(dx, dy).setItem(money);
            }
        }
    }

    public void shootPedestrian(int dx, int dy) {
        if (dx > 0) {
            if (getCell().getNeighbor(dx + 1, dy).getActor() != null) {
                getCell().getNeighbor(dx + 1, dy).setActor(null);
                Money money = new Money(getCell().getNeighbor(dx + 1, dy));
                getCell().getNeighbor(dx + 1, dy).setItem(money);
            }
        } else if (dx < 0) {
            if (getCell().getNeighbor(dx - 1, dy).getActor() != null) {
                getCell().getNeighbor(dx - 1, dy).setActor(null);
                Money money = new Money(getCell().getNeighbor(dx - 1, dy));
                getCell().getNeighbor(dx - 1, dy).setItem(money);
            }
        } else if (dy > 0) {
            if (getCell().getNeighbor(dx, dy + 1).getActor() != null) {
                getCell().getNeighbor(dx, dy + 1).setActor(null);
                Money money = new Money(getCell().getNeighbor(dx, dy + 1));
                getCell().getNeighbor(dx, dy + 1).setItem(money);
            }
        } else if (dy < 0) {
            if (getCell().getNeighbor(dx, dy - 1).getActor() != null) {
                getCell().getNeighbor(dx, dy - 1).setActor(null);
                Money money = new Money(getCell().getNeighbor(dx, dy - 1));
                getCell().getNeighbor(dx, dy - 1).setItem(money);
            }
        }
    }
}
