package com.pcariou.model;

import com.pcariou.util.Direction;
import com.pcariou.util.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Adventurer {
    private final String name;
    private final Queue<Character> movements;

    private Position position;
    private Direction direction;
    private int collectedTreasures = 0;

    public Adventurer(String name, Position position, Direction direction, String movementsSequence) {
        this.name = name;
        this.position = position;
        this.direction = direction;
        this.movements = movementsSequence.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public void addTreasure() {
        collectedTreasures++;
    }

    public boolean hasMoreMoves() {
        return !movements.isEmpty();
    }

    public static boolean hasMoreMoves(List<Adventurer> adventurers) {
        return adventurers.stream().anyMatch(Adventurer::hasMoreMoves);
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Queue<Character> getMovements() {
        return movements;
    }

    public String toCell() {
        return "A(" + name.charAt(0) + ")";
    }

    @Override
    public String toString() {
        return String.format("A - %s - %d - %d - %s - %d", name, position.x(), position.y(), direction, collectedTreasures);
    }
}
