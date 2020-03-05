package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

import java.util.*;


public class Enemy extends Actor {
    String name;
    public Enemy(Cell cell) {
        super(cell);
    }

    @Override
        public String getTileName() {
        return "enemy";
    }

    public void randomMove() {
        List<Integer> fullCoordinateList = Arrays.asList(-1, 0, 1);
        Random rand = new Random();
        int coordinateY = 0;
        int coordinateX = fullCoordinateList.get(rand.nextInt(fullCoordinateList.size()));
        if (coordinateX == 0) {
            List<Integer> reducedCoordinateList = Arrays.asList(-1, 1);
            coordinateY = reducedCoordinateList.get(rand.nextInt(reducedCoordinateList.size()));
        }
        this.move(coordinateX, coordinateY);
    }

    public void setName(String name) {
        this.name = name;
    }
}
