package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Enemy;
import com.codecool.quest.logic.actors.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Player player = map.getPlayer();
    private static List<Actor> enemies = new ArrayList<>();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH / 3.3,
            map.getHeight() * Tiles.TILE_WIDTH / 0.1);
    GraphicsContext context = canvas.getGraphicsContext2D();

    Label healthValue = new Label();
    Label moneyLabel = new Label();
    Label inventoryLabel = new Label();

    public static void addToEnemies(Actor actor) {
        enemies.add(actor);
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(150);
        ui.setPadding(new Insets(0));

        Label healthText = new Label("Health: ");
        ui.add(healthText, 0, 0);
        healthText.setFont(new Font("Arial", 20));
        ui.add(healthValue, 1, 0);
        healthValue.setFont(new Font("Arial", 20));
        Label moneyText = new Label("Money: ");
        ui.add(moneyText, 0, 2);
        moneyText.setFont(new Font("Arial", 20));
        ui.add(moneyLabel, 1, 2);
        moneyLabel.setFont(new Font("Arial", 20));
        Label inventoryText = new Label("Inventory: ");
        ui.add(inventoryText, 0, 3);
        inventoryText.setFont(new Font("Arial", 20));
        ui.add(inventoryLabel, 0, 4);
        inventoryLabel.setFont(new Font("Arial", 20));

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh(player);

        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Codecool GTA");
        primaryStage.show();

        randomMovingEnemy();

    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                player.pickUpItem("gun", 0, -1);
                player.pickUpItem("key", 0, -1);
                player.pickUpItem("pistol", 0, -1);
                //player.pickUpMoney(0, -1);
                player.hitPedestrian(0, -1);
                player.move(0, -1);
                if (player.isInACar()) {
                    player.turnPlayersCar(map.getPlayer().getTileName(), "carv1", "carv1_vertical");
                } else {
                    player.turnPlayer("player_up");
                }
                refresh(player);
                break;
            case DOWN:
                player.pickUpItem("gun", 0, 1);
                player.pickUpItem("key", 0, 1);
                player.pickUpItem("pistol", 0, 1);
                //player.pickUpMoney(0, 1);
                player.hitPedestrian(0, 1);
                player.move(0, 1);
                if (player.isInACar()) {
                    player.turnPlayersCar(map.getPlayer().getTileName(), "carv1_vertical", "carv1");
                } else {
                    player.turnPlayer("player_down");
                }
                refresh(player);
                break;
            case LEFT:
                player.pickUpItem("gun", -1, 0);
                player.pickUpItem("key", -1, 0);
                player.pickUpItem("pistol", -1, 0);
                //player.pickUpMoney(-1, 0);
                player.hitPedestrian(-1, 0);
                player.move(-1, 0);
                if (player.isInACar()) {
                    player.turnPlayersCar(map.getPlayer().getTileName(), "carh1", "carh1_horizontal");
                } else {
                    player.turnPlayer("player_left");
                }
                refresh(player);
                break;
            case RIGHT:
                player.pickUpItem("gun", 1, 0);
                player.pickUpItem("key", 1, 0);
                player.pickUpItem("pistol", 1, 0);
                //player.pickUpMoney(1, 0);
                player.hitPedestrian(1, 0);
                player.move(1, 0);
                if (player.isInACar()) {
                    player.turnPlayersCar(map.getPlayer().getTileName(), "carh1_horizontal", "carh1");
                } else {
                    player.turnPlayer("player_right");
                }
                refresh(player);
                break;
            case F:
                player.enterExitVehicle(player.isInACar());
                refresh(player);
                break;
        }
    }

    private void refresh(Player player) {
        context.setFill(Color.rgb(71, 45, 60)); // Empty cell's color
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y, player);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y, player);
                } else {
                    Tiles.drawTile(context, cell, x, y, player);
                }
            }
        }
        healthValue.setText("" + map.getPlayer().getHealth());
        moneyLabel.setText("" + map.getPlayer().getMoney());
        inventoryLabel.setText("" + map.getPlayer().getInventory());
    }

    private void randomMovingEnemy() {
        Timer randomMoveTimer = new Timer();
        randomMoveTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (Actor enemy : enemies) {
                    if (enemy instanceof Enemy) {
                        ((Enemy) enemy).randomMove();
                    }
                }
                refresh(player);
            }
        }, 0, 500);
    }
}
