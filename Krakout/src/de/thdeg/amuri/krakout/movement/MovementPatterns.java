package de.thdeg.amuri.krakout.movement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


/**
 * Movement Patterns for {@link de.thdeg.amuri.krakout.graphics.moving.RandomBall}
 */
public class MovementPatterns {
    private final HashMap<String, ArrayList<Position>> hashMap;

    private final ArrayList<Position> square;
    private final ArrayList<Position> zigZag;

    private final Random random;

    /**
     * Movement Patterns and their hasMaps
     */
    public MovementPatterns() {
        this.hashMap = new HashMap<>();
        this.square =
                new ArrayList<>(List.of(
                        new Position(30, 30),
                        new Position(930, 30),
                        new Position(930, 510),
                        new Position(30, 510)));
        this.zigZag =
                new ArrayList<>(List.of(
                        new Position(300, 200),
                        new Position(400, 340),
                        new Position(500, 200),
                        new Position(600, 340),
                        new Position(700, 200),
                        new Position(800, 340)));
        this.hashMap.put("Square", square);
        this.hashMap.put("ZigZag", zigZag);
        this.random = new Random();
    }

    /**
     * @return Random Pattern
     */
    public String getRandomPattern() {
        if (random.nextBoolean()) {
            return "Square";
        } else {
            return "ZigZag";
        }
    }

    /**
     * @param pattern
     * @return associated pattern of the hashMap
     */
    public ArrayList getPattern(String pattern) {
        return hashMap.get(pattern);
    }
}

