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

    public void turnPlayer(String currentPlayerTile, String restrictedPlayerTile, String nextPlayerTile) {
        if (currentPlayerTile.equals(restrictedPlayerTile)) {
            this.playerTile = currentPlayerTile;
        } else {
            this.playerTile = nextPlayerTile;
        }
    }
}
