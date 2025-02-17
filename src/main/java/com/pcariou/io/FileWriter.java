package com.pcariou.io;

import com.pcariou.model.Adventurer;
import com.pcariou.model.Map;
import com.pcariou.model.tile.Treasure;
import com.pcariou.util.Position;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class FileWriter {

    private FileWriter() {}

    public static void writeOutput(String filePath, Map map, List<Adventurer> adventurers, List<String> orderedElements) throws IOException {
        StringBuilder output = new StringBuilder();

        // Write map dimensions
        output.append(map.toString()).append(System.lineSeparator());

        // Write mountains and treasures
        for (String line : orderedElements) {
            String[] parts = line.split(" - ");
            if (parts[0].equals("T")) { // Treasure
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                if (map.getTile(Position.of(x, y)) instanceof Treasure treasure) {
                    output.append("T - ").append(x).append(" - ").append(y).append(" - ").append(treasure.getCount()).append(System.lineSeparator());
                }
            } else { // Mountain
                output.append(line).append(System.lineSeparator());
            }
        }

        // Write adventurers
        for (Adventurer adventurer : adventurers) {
            output.append(adventurer).append(System.lineSeparator());
        }

        Files.writeString(Path.of(filePath), output.toString().trim());
    }
}
