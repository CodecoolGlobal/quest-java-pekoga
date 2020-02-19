package com.codecool.quest.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    ENEMY("enemy"),
    CLOSED("closedDoor"),
    OPENED("openedDoor"),
    PISTOL("pistol"),
    WALL("wall");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
