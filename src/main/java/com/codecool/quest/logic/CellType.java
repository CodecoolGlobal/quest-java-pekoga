package com.codecool.quest.logic;

public enum CellType {
    EMPTY("empty"),
    HOUSE1("house1"),
    HOUSE2("house2"),
    HOUSE3("house3"),
    HOUSE4("house4"),
    ROAD("road"),
    PAVEMENT1("pavement1"),
    PAVEMENT2("pavement2"),
    CLOSED("closed"),
    OPENED("opened"),
    TREE("tree"),
    TRUCK1("truck1"),
    TRUCK2("truck2"),
    CARH1("carh1"),
    CARH2("carh2"),
    CARV1("carv1"),
    CARV2("carv2"),
    BUILDING1("building1"),
    BUILDING2("building2"),
    BUILDING3("building3"),
    BUILDING4("building4"),
    BUILDING5("building5"),
    BUILDING6("building6"),
    BUILDING7("building7"),
    BUILDING8("building8"),
    BUILDING9("building9");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
