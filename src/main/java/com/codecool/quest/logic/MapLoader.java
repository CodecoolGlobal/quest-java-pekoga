package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Pedestrian;
import com.codecool.quest.logic.actors.Enemy;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.items.Money;
import com.codecool.quest.logic.items.Weapon;
import com.codecool.quest.logic.items.Weapon2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        var enemies = new ArrayList<Enemy>();
        int numberOfEnemies = 0;
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case 'a':
                            cell.setType(CellType.HOUSE1);
                            break;
                        case 'b':
                            cell.setType(CellType.HOUSE2);
                            break;
                        case 'c':
                            cell.setType(CellType.HOUSE3);
                            break;
                        case 'd':
                            cell.setType(CellType.HOUSE4);
                            break;
                        case 'x':
                            cell.setType(CellType.TRUCK1);
                            break;
                        case 'y':
                            cell.setType(CellType.TRUCK2);
                            break;
                        case 'p':
                            cell.setType(CellType.CARH1);
                            break;
                        case 'q':
                            cell.setType(CellType.CARH2);
                            break;
                        case 'v':
                            cell.setType(CellType.CARV1);
                            break;
                        case 'V':
                            cell.setType(CellType.CARV2);
                            break;
                        case '1':
                            cell.setType(CellType.BUILDING1);
                            break;
                        case '2':
                            cell.setType(CellType.BUILDING2);
                            break;
                        case '3':
                            cell.setType(CellType.BUILDING3);
                            break;
                        case '4':
                            cell.setType(CellType.BUILDING4);
                            break;
                        case '5':
                            cell.setType(CellType.BUILDING5);
                            break;
                        case '6':
                            cell.setType(CellType.BUILDING6);
                            break;
                        case '7':
                            cell.setType(CellType.BUILDING7);
                            break;
                        case '8':
                            cell.setType(CellType.BUILDING8);
                            break;
                        case '9':
                            cell.setType(CellType.BUILDING9);
                            break;
                        case '#':
                            cell.setType(CellType.ROAD);
                            break;
                        case '.':
                            cell.setType(CellType.PAVEMENT1);
                            break;
                        case ',':
                            cell.setType(CellType.PAVEMENT2);
                            break;
                        case '@':
                            cell.setType(CellType.EMPTY);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'W':
                            cell.setType(CellType.EMPTY);
                            new Weapon(cell);
                            break;
                        case 'C':
                            cell.setType(CellType.CLOSED);
                            break;
                        case 'O':
                            cell.setType(CellType.OPENED);
                            break;
                        case 't':
                            cell.setType(CellType.TREE);
                            break;
                        case 'f':
                            cell.setType(CellType.FLOWER);
                            break;
                        case 'n':
                            cell.setType(CellType.DUCK);
                            break;
                        case 'g':
                            cell.setType(CellType.GROUND);
                            break;
                        case 'w':
                            cell.setType(CellType.WATER);
                            break;
                        case 'M':
                            cell.setType(CellType.EMPTY);
                            new Money(cell);
                            break;
                        case 'E':
                            cell.setType(CellType.EMPTY);
                            Enemy enemy = new Enemy(cell);
                            enemy.setName(String.valueOf(numberOfEnemies));
                            enemies.add(enemy);
                            map.setEnemy(enemy);
                            break;
                        case 'Q':
                            cell.setType(CellType.EMPTY);
                            new Weapon2(cell);
                            break;
                        case 'P':
                            cell.setType(CellType.PAVEMENT1);
                            map.setPedestrian(new Pedestrian(cell));
                            break;
                        case 'o':
                            cell.setType(CellType.DOG);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
