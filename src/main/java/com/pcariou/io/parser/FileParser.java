package com.pcariou.io.parser;

import com.pcariou.model.Adventurer;
import com.pcariou.model.Map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class FileParser {

    private enum LineType {
        MAP("C", new MapParser()),
        MOUNTAIN("M", new MountainParser(), true),
        TREASURE("T", new TreasureParser(), true),
        ADVENTURER("A", new AdventurerParser());

        private final String code;
        private final LineParserStrategy parser;
        private final boolean storeOrder;

        LineType(String code, LineParserStrategy parser) {
            this.code = code;
            this.parser = parser;
            this.storeOrder = false;
        }

        LineType(String code, LineParserStrategy parser, boolean storeOrder) {
            this.code = code;
            this.parser = parser;
            this.storeOrder = storeOrder;
        }

        public static LineType fromCode(String code) {
            for (LineType type : values()) {
                if (type.code.equals(code)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown line type: " + code);
        }

        public Map parse(String[] parts, Map map, List<Adventurer> adventurers) {
            return parser.parse(parts, map, adventurers);
        }
    }

    private FileParser() {}

    public static ParsedMap parseMap(String filePath, List<Adventurer> adventurers) throws IOException {
        Map map = null;
        List<String> orderedElements = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            String[] parts = line.split(" - ");
            LineType type = LineType.fromCode(parts[0]);
            map = type.parse(parts, map, adventurers);
            if (type.storeOrder) {
                orderedElements.add(line);
            }
        }
        Objects.requireNonNull(map, "Map size must be defined.");
        return new ParsedMap(map, orderedElements);
    }

    public record ParsedMap(Map map, List<String> orderedElements) {
        public ParsedMap(Map map, List<String> orderedElements) {
            this.map = map;
            this.orderedElements = List.copyOf(orderedElements);
        }
    }
}