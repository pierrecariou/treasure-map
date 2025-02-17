package com.pcariou.model.tile;

public class Treasure implements Tile {
    private int count;

    public Treasure(int count) {
        this.count = count;
    }

    public void decreaseCount() {
        count--;
    }

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public String toString() {
        return "T(" + count + ")";
    }
}
