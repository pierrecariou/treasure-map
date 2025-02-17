package com.pcariou.io.parser;

import com.pcariou.model.Adventurer;
import com.pcariou.model.Map;

import java.util.List;

public class MapParser implements LineParserStrategy {

    @Override
    public Map parse(String[] parts, Map map, List<Adventurer> adventurers) {
        assert parts.length == 3 : "Invalid map size format.";
        int width = ParserHelper.parsePositiveInt(parts[1], "Invalid map width.");
        int height = ParserHelper.parsePositiveInt(parts[2], "Invalid map height.");
        return new Map(width, height);
    }

}
