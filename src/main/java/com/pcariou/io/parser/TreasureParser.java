package com.pcariou.io.parser;

import com.pcariou.model.Adventurer;
import com.pcariou.model.Map;
import com.pcariou.model.tile.Treasure;
import com.pcariou.util.Position;

import java.util.List;
import java.util.Objects;

public class TreasureParser implements LineParserStrategy {

    @Override
    public Map parse(String[] parts, Map map, List<Adventurer> adventurers) {
        Objects.requireNonNull(map, "Map size must be defined before adding elements.");
        int x = ParserHelper.parseInt(parts[1], "Invalid treasure x-coordinate.");
        int y = ParserHelper.parseInt(parts[2], "Invalid treasure y-coordinate.");
        int count = ParserHelper.parsePositiveInt(parts[3], "Treasure count must be positive.");
        ParserHelper.validateCoordinates(x, y, map);
        map.setTile(Position.of(x, y), new Treasure(count));
        return map;
    }
}
