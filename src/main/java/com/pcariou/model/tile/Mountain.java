package com.pcariou.model.tile;

public class Mountain implements Tile {

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public String toString() {
        return "M";
    }
}
