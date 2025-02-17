package com.pcariou.io.parser;

import com.pcariou.model.Adventurer;
import com.pcariou.model.Map;

import java.util.List;

public interface LineParserStrategy {
    Map parse(String[] parts, Map map, List<Adventurer> adventurers);
}
