package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
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

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH / 3.3,
            map.getHeight() * Tiles.TILE_WIDTH / 0.1);
    GraphicsContext context = canvas.getGraphicsContext2D();

    Label healthLabel = new Label();
    Label moneyLabel = new Label();
    Label inventoryLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(150);
        ui.setPadding(new Insets(0));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        healthLabel.setFont(new Font("Arial", 20));
        ui.add(new Label("Money: "), 0, 2);
        ui.add(moneyLabel, 1, 2);
        moneyLabel.setFont(new Font("Arial", 20));
        ui.add(new Label("\nInventory: \n"), 0, 3);
        ui.add(inventoryLabel, 0, 4);
        inventoryLabel.setFont(new Font("Arial", 20));

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Codecool GTA");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().pickUpItem("gun", 0, -1);
                map.getPlayer().pickUpItem("key", 0, -1);
                map.getPlayer().pickUpItem("pistol", 0, -1);
                map.getPlayer().pickUpMoney(0, -1);
                map.getPlayer().move(0, -1);
                map.getPlayer().turnPlayer(map.getPlayer().getTileName(), "carv1", "carv1_vertical");
                refresh();
                break;
            case DOWN:
                map.getPlayer().pickUpItem("gun", 0, 1);
                map.getPlayer().pickUpItem("key", 0, 1);
                map.getPlayer().pickUpItem("pistol", 0, 1);
                map.getPlayer().pickUpMoney(0, 1);
                map.getPlayer().move(0, 1);
                map.getPlayer().turnPlayer(map.getPlayer().getTileName(), "carv1_vertical", "carv1");
                refresh();
                break;
            case LEFT:
                map.getPlayer().pickUpItem("gun", -1, 0);
                map.getPlayer().pickUpItem("key", -1, 0);
                map.getPlayer().pickUpItem("pistol", -1, 0);
                map.getPlayer().pickUpMoney(-1, 0);
                map.getPlayer().move(-1, 0);
                map.getPlayer().turnPlayer(map.getPlayer().getTileName(), "carh1", "carh1_horizontal");
                refresh();
                break;
            case RIGHT:
                map.getPlayer().pickUpItem("gun", 1, 0);
                map.getPlayer().pickUpItem("key", 1, 0);
                map.getPlayer().pickUpItem("pistol", 1, 0);
                map.getPlayer().pickUpMoney(1, 0);
                map.getPlayer().move(1, 0);
                map.getPlayer().turnPlayer(map.getPlayer().getTileName(), "carh1_horizontal", "carh1");
                refresh();
                break;
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        moneyLabel.setText("" + map.getPlayer().getMoney());
        inventoryLabel.setText("" + map.getPlayer().getInventory());
    }
}
