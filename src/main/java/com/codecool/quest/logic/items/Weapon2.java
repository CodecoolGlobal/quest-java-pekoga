package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.items.Item;

public class Weapon2 extends Item {
    public Weapon2(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "weapon2";
    }
}

