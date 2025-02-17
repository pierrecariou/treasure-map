package com.pcariou.util;

public record Position(int x, int y) {

    public static Position of(int x, int y) {
        return new Position(x, y);
    }

    public Position move(int dx, int dy) {
        return Position.of(x + dx, y + dy);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
