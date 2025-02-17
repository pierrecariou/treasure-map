package com.pcariou.util;

import java.util.Arrays;

public enum Direction {
    NORTH("N", 0, -1),
    SOUTH("S", 0, 1),
    EAST("E", 1, 0),
    WEST("O", -1, 0);

    private final String code;
    private final int dx, dy;

    Direction(String code, int dx, int dy) {
        this.code = code;
        this.dx = dx;
        this.dy = dy;
    }

    public Position moveForward(Position position) {
        return position.move(dx, dy);
    }

    public Direction turnLeft() {
        return switch (this) {
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case EAST -> NORTH;
        };
    }

    public Direction turnRight() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }

    public static Direction fromCode(String code) {
        return Arrays.stream(values())
                .filter(d -> d.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid direction code: " + code + ". Should be one of N, S, E, O"));
    }

    @Override
    public String toString() {
        return code;
    }
}
