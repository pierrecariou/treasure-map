package com.pcariou.io;

import com.pcariou.io.parser.FileParser;
import com.pcariou.model.Adventurer;
import com.pcariou.model.Map;
import com.pcariou.util.Position;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileParserTest {

    @Test
    void testParseMap() throws IOException {
        String input = """
            C - 3 - 3
            M - 1 - 1
            T - 2 - 2 - 2
            A - Lara - 0 - 0 - N - AAD
            """;

        Path tempFile = Files.createTempFile("testMap", ".txt");
        Files.writeString(tempFile, input);

        List<Adventurer> adventurers = new ArrayList<>();
        Map map = FileParser.parseMap(tempFile.toString(), adventurers).map();

        assertEquals(3, map.getWidth());
        assertEquals(3, map.getHeight());
        assertFalse(map.getTile(Position.of(1, 1)).isWalkable()); // Mountain check
        assertEquals(1, adventurers.size());
        assertEquals("Lara", adventurers.getFirst().getName());
    }

    @Test
    void testParseInvalidMap_MissingSize() throws IOException {
        String input = """
            M - 1 - 1
            T - 2 - 2 - 2
            A - Lara - 0 - 0 - N - AAD
            """;

        Path tempFile = Files.createTempFile("testInvalidMap", ".txt");
        Files.writeString(tempFile, input);

        List<Adventurer> adventurers = new ArrayList<>();

        Exception exception = assertThrows(NullPointerException.class, () -> {
            FileParser.parseMap(tempFile.toString(), adventurers);
        });

        assertTrue(exception.getMessage().contains("Map size must be defined"));
    }

    @Test
    void testParseInvalidMap_UnknownLine() throws IOException {
        String input = """
            C - 3 - 3
            X - 1 - 1 - unknown
            A - Lara - 0 - 0 - N - AAD
            """;

        Path tempFile = Files.createTempFile("testInvalidMap", ".txt");
        Files.writeString(tempFile, input);

        List<Adventurer> adventurers = new ArrayList<>();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FileParser.parseMap(tempFile.toString(), adventurers);
        });

        assertTrue(exception.getMessage().contains("Unknown line type"));
    }

    @Test
    void testParseInvalidMap_InvalidCoordinates() throws IOException {
        String input = """
            C - 3 - 3
            M - -1 - 1
            A - Lara - 0 - 0 - N - AAD
            """;

        Path tempFile = Files.createTempFile("testInvalidMap", ".txt");
        Files.writeString(tempFile, input);

        List<Adventurer> adventurers = new ArrayList<>();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FileParser.parseMap(tempFile.toString(), adventurers);
        });

        assertTrue(exception.getMessage().contains("Invalid coordinates"));
    }

}