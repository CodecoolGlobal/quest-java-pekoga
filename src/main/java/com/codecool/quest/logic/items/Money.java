package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.items.Item;

public class Money extends Item {
    public Money(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "money";
    }
}