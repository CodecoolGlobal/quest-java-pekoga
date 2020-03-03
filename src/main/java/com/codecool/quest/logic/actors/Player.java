package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Player extends Actor {
    String playerTile = "player";

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return this.playerTile;
    }

    public void setPlayerTile(String playerTile) {
        this.playerTile = playerTile;
    }
}
