package com.pcariou.service;

import com.pcariou.io.parser.FileParser;
import com.pcariou.io.FileWriter;
import com.pcariou.model.Adventurer;
import com.pcariou.model.Map;
import com.pcariou.util.Direction;
import com.pcariou.util.Position;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {

    @Test
    void testTurnBasedExecution() {
        Map map = new Map(3, 3);
        Adventurer lara = new Adventurer("Lara", Position.of(1, 1), Direction.SOUTH, "AA");
        Adventurer john = new Adventurer("John", Position.of(1, 2), Direction.NORTH, "AA");

        GameEngine engine = new GameEngine(map, List.of(lara, john));
        engine.run();

        // Both adventurers should get stuck because they are moving towards each other
        assertEquals(Position.of(1, 1), lara.getPosition());
        assertEquals(Position.of(1, 2), john.getPosition());
    }

    @Test
    void testLargeMapWithExpectedResult() throws IOException {
        String input = String.join(System.lineSeparator(),
                "C - 6 - 6",
                "M - 1 - 1",
                "M - 4 - 3",
                "T - 2 - 2 - 3",
                "T - 3 - 5 - 2",
                "A - Lara - 0 - 0 - E - AADDA",
                "A - John - 5 - 5 - N - AAA"
        );

        Path tempInputFile = Files.createTempFile("testLargeMapInput", ".txt");
        Files.writeString(tempInputFile, input);

        List<Adventurer> adventurers = new ArrayList<>();
        FileParser.ParsedMap parsedMap = FileParser.parseMap(tempInputFile.toString(), adventurers);

        GameEngine engine = new GameEngine(parsedMap.map(), adventurers);
        engine.run();

        Path tempOutputFile = Files.createTempFile("testLargeMapOutput", ".txt");
        FileWriter.writeOutput(tempOutputFile.toString(), parsedMap.map(), adventurers, parsedMap.orderedElements());

        String actualOutput = Files.readString(tempOutputFile);

        String expectedOutput = String.join(System.lineSeparator(),
                "C - 6 - 6",
                "M - 1 - 1",
                "M - 4 - 3",
                "T - 2 - 2 - 3",
                "T - 3 - 5 - 2",
                "A - Lara - 1 - 0 - O - 0",
                "A - John - 5 - 2 - N - 0"
        );

        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    @Test
    void testComplexFullScenario() throws IOException {
        String input = String.join(System.lineSeparator(),
                "C - 7 - 7",
                "M - 2 - 3",
                "M - 4 - 4",
                "T - 1 - 2 - 2",
                "T - 5 - 5 - 3",
                "A - Lara - 0 - 0 - E - AADAGAA",
                "A - John - 6 - 6 - N - AAGADAAGAA",
                "A - Bob - 3 - 3 - O - AAGADAAGAA"
        );

        Path tempInputFile = Files.createTempFile("testComplexScenarioInput", ".txt");
        Files.writeString(tempInputFile, input);

        List<Adventurer> adventurers = new ArrayList<>();
        FileParser.ParsedMap parsedMap = FileParser.parseMap(tempInputFile.toString(), adventurers);
        Map map = parsedMap.map();
        List<String> orderedElements = parsedMap.orderedElements();

        GameEngine engine = new GameEngine(map, adventurers);
        engine.run();

        Path tempOutputFile = Files.createTempFile("testComplexScenarioOutput", ".txt");
        FileWriter.writeOutput(tempOutputFile.toString(), map, adventurers, orderedElements);

        String actualOutput = Files.readString(tempOutputFile);

        String expectedOutput = String.join(System.lineSeparator(),
                "C - 7 - 7",
                "M - 2 - 3",
                "M - 4 - 4",
                "T - 1 - 2 - 2",
                "T - 5 - 5 - 3",
                "A - Lara - 4 - 1 - E - 0",
                "A - John - 3 - 2 - O - 0",
                "A - Bob - 1 - 6 - S - 0"
        );

        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}