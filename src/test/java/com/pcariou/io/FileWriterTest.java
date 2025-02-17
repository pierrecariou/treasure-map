package com.pcariou.io;

import com.pcariou.model.Adventurer;
import com.pcariou.model.Map;
import com.pcariou.util.Direction;
import com.pcariou.util.Position;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileWriterTest {

    @Test
    void testWriteOutput() throws IOException {
        Map map = new Map(3, 3);
        Adventurer lara = new Adventurer("Lara", Position.of(1, 1), Direction.NORTH, "AAD");
        Path tempFile = Files.createTempFile("testOutput", ".txt");

        FileWriter.writeOutput(tempFile.toString(), map, List.of(lara), List.of());

        String content = Files.readString(tempFile);
        assertTrue(content.contains("C - 3 - 3"));
        assertTrue(content.contains("A - Lara - 1 - 1 - N - 0"));
    }
}