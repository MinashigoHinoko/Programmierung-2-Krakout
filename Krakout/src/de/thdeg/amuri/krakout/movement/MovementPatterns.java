package de.thdeg.amuri.krakout.movement;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.moving.XAxisComparator;

import java.util.*;


/**
 * Movement Patterns for {@link de.thdeg.amuri.krakout.graphics.moving.RandomBall}
 */
public class MovementPatterns {
    private final HashMap<String, ArrayList<Position>> hashMap;

    private final ArrayList<Position> square;
    private final ArrayList<Position> zigZag;
    private final ArrayList<Position> zero;
    private boolean runZero;
    private boolean runZigZag;
    private boolean runSquare;
    private boolean runXFirst;
    private boolean runYFirst;
    private boolean runCentered;
    private  XAxisComparator xAxisComparator;
    private final ArrayList<Position> xFirst;
    private final ArrayList<Position> yFirst;
    private final Comparator<Position> centeredComparator;
    private final ArrayList<Position> centered;
    private final Position middlePos;
    private final Position position;

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
        this.zero =
                new ArrayList<>(List.of(
                        new Position(30, 30),
                        new Position(930, 30),
                        new Position(930, 510),
                        new Position(30, 510),
                        new Position(300, 200),
                        new Position(400, 340),
                        new Position(500, 200),
                        new Position(600, 340),
                        new Position(700, 200),
                        new Position(800, 340)));
        this.xFirst =
                new ArrayList<>(List.of(
                        new Position(30, 30),
                        new Position(930, 30),
                        new Position(930, 510),
                        new Position(30, 510),
                        new Position(300, 200),
                        new Position(400, 340),
                        new Position(500, 200),
                        new Position(600, 340),
                        new Position(700, 200),
                        new Position(800, 340)));
        this.yFirst =
                new ArrayList<>(List.of(
                        new Position(30, 30),
                        new Position(930, 30),
                        new Position(930, 510),
                        new Position(30, 510),
                        new Position(300, 200),
                        new Position(400, 340),
                        new Position(500, 200),
                        new Position(600, 340),
                        new Position(700, 200),
                        new Position(800, 340)));
        this.centered =
                new ArrayList<>(List.of(
                        new Position(30, 30),
                        new Position(930, 30),
                        new Position(930, 510),
                        new Position(30, 510),
                        new Position(300, 200),
                        new Position(400, 340),
                        new Position(500, 200),
                        new Position(600, 340),
                        new Position(700, 200),
                        new Position(800, 340)));
        Comparator<Position> yAxisComparator = new Comparator<Position>() {
            @Override
            public int compare(Position o1, Position o2) {
                return (int) Math.signum(o1.y-o2.y);
            }
        };
        this.xAxisComparator = new XAxisComparator();
        this.position = new Position();
        this.middlePos = new Position(GameView.HEIGHT/2,GameView.WIDTH/2);
        this.centeredComparator = (Position obj1, Position obj2) -> (int) Math.signum(Math.abs(position.distance(obj1.x,obj1.y)-position.distance(middlePos.x,middlePos.y))- Math.abs(position.distance(obj2.x,obj2.y)-position.distance(middlePos.x,middlePos.y)));
        Collections.sort(zero);
        xFirst.sort(xAxisComparator);
        yFirst.sort(yAxisComparator);
        centered.sort(this.centeredComparator);
        this.hashMap.put("Zero",zero);
        this.hashMap.put("Square", square);
        this.hashMap.put("ZigZag", zigZag);
        this.hashMap.put("XFirst",xFirst);
        this.hashMap.put("YFirst",yFirst);
        this.hashMap.put("Centered",centered);
        this.random = new Random();
        this.runZero = false;
        this.runSquare =false;
        this.runZigZag = false;
        this.runXFirst = false;
        this.runYFirst = false;
        this.runCentered = true;
    }

    /**
     * @return Random Pattern
     */
    public String getRandomPattern() {
        if(this.runCentered){
            this.runCentered = false;
            this.runYFirst = true;
            this.runXFirst = true;
            this.runZero = true;
            this.runSquare= true;
            this.runZigZag = true;
            return "Centered";
        }
        if (this.runYFirst){
            this.runYFirst = false;
            this.runXFirst = true;
            this.runZero = true;
            this.runSquare = true;
            this.runZigZag = true;
            return "YFirst";
        }
        if (this.runXFirst){
            this.runXFirst = false;
            this.runZero = true;
            this.runSquare = true;
            this.runZigZag = true;
            return "XFirst";
        }
        if (this.runZero) {
            this.runZero = false;
            this.runSquare = true;
            this.runZigZag = true;
            return "Zero";
        }
        if (this.runZigZag && this.runSquare) {
            if (random.nextBoolean()) {
                this.runSquare = false;
                return "Square";
            } else {
                this.runZigZag = false;
                return "ZigZag";
            }

            }else if(this.runZigZag && !this.runSquare) {
            this.runZigZag = false;
                return "ZigZag";
        }else if (this.runSquare && this.runZigZag) {
            this.runZigZag = false;
            return "Square";
        }else{
            this.runCentered= true;
            return "Centered";
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

