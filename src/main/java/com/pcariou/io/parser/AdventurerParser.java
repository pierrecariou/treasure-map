package com.pcariou.io.parser;

import com.pcariou.model.Adventurer;
import com.pcariou.model.Map;
import com.pcariou.util.Direction;
import com.pcariou.util.Position;

import java.util.List;
import java.util.Objects;

public class AdventurerParser implements LineParserStrategy {

    @Override
    public Map parse(String[] parts, Map map, List<Adventurer> adventurers) {
        Objects.requireNonNull(map, "Map size must be defined before adding elements.");
        assert parts.length == 6 : "Invalid adventurer format.";
        String name = parts[1];
        int x = ParserHelper.parseInt(parts[2], "Invalid adventurer x-coordinate.");
        int y = ParserHelper.parseInt(parts[3], "Invalid adventurer y-coordinate.");
        Direction direction = Direction.fromCode(parts[4]);
        String movements = parts[5];
        ParserHelper.validateCoordinates(x, y, map);
        adventurers.add(new Adventurer(name, Position.of(x, y), direction, movements));
        return map;
    }
}
