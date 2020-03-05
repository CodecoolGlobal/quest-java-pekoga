package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;

import java.util.ArrayList;
import java.util.Arrays;

public class Player extends Actor {
    String playerTile = "player_down";
    String vehicleEntered = "";

    ArrayList<String> carTileNames = new ArrayList<String>(Arrays.asList("carv1", "carv1_vertical", "carh1", "carh1_horizontal"));

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return this.playerTile;
    }

    public void turnPlayersCar(String currentPlayerTile, String restrictedPlayerTile, String nextPlayerTile) {
        if (currentPlayerTile.equals(restrictedPlayerTile)) {
            this.playerTile = currentPlayerTile;
        } else {
            this.playerTile = nextPlayerTile;
        }
    }

    public boolean isInACar() {
        return carTileNames.contains(this.getTileName());
    }

    public void turnPlayer(String nextLook) {
        this.playerTile = nextLook;
    }

    public void enterExitVehicle(boolean isInCar) {
        if (isInCar) {
            this.playerTile = "player_down";
        } else {
            this.playerTile = "carv1";
        }
    }
}
