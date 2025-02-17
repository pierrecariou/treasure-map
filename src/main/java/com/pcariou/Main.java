package com.pcariou;

import com.pcariou.io.parser.FileParser;
import com.pcariou.io.FileWriter;
import com.pcariou.model.Adventurer;
import com.pcariou.service.GameEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.err.println("Usage: java -jar treasure-map.jar <inputFile> <outputFile> --verbose (optional)");
            return;
        }
        String inputFilePath = args[0];
        String outputFilePath = args[1];
        boolean verbose = args.length == 3 && args[2].equals("--verbose");

        try {
            List<Adventurer> adventurers = new ArrayList<>();
            FileParser.ParsedMap parsedMap = FileParser.parseMap(inputFilePath, adventurers);

            GameEngine engine = new GameEngine(parsedMap.map(), adventurers).verbose(verbose);
            engine.run();

            FileWriter.writeOutput(outputFilePath, parsedMap.map(), adventurers, parsedMap.orderedElements());
            System.out.println("Simulation completed. Results saved to: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error reading/writing files: " + e.getMessage());
        }
    }
}