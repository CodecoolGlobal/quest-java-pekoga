package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;

public class Pedestrian extends Actor {
    public Pedestrian(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "pedestrian";
    }
}
