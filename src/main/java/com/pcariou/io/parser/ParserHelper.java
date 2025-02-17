package com.pcariou.io.parser;

import com.pcariou.model.Map;

class ParserHelper {

    private ParserHelper() {}

    public static int parseInt(String value, String errorMessage) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static int parsePositiveInt(String value, String errorMessage) {
        int num = parseInt(value, errorMessage);
        if (num <= 0) throw new IllegalArgumentException(errorMessage);
        return num;
    }

    public static void validateCoordinates(int x, int y, Map map) {
        if (x < 0 || y < 0 || x >= map.getWidth() || y >= map.getHeight()) {
            throw new IllegalArgumentException("Invalid coordinates: (" + x + ", " + y + ")");
        }
    }
}
