package com.pcariou.model.tile;

public class PlainTile implements Tile {

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public String toString() {
        return ".";
    }
}
