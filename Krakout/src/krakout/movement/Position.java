package krakout.movement;

/**
 * Main Object for the Movement of other objects
 */
public class Position {
    /**
     * Initialising x for the x Position
     */
    public double x;
    /**
     * Initialising y for the y Position
     */
    public double y;

    /**
     * Main Output of Position, every object that has to move interacts with this.
     *
     * @see krakout.nonplayerobject.Pinball
     * @see krakout.nonplayerobject.Item
     * @see krakout.nonplayerobject.Brick
     */
    public Position() {
        this(0, 0);
    }

    /**
     * Constructor for the x and y Coordinate for Position
     * @param x Position
     * @param y Position
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Takes input and lets the Object Move one left
     *
     */
    public void left() {
        this.x = x--;
    }
    /**
     * Takes input and lets the Object Move left with a certain pixel Speed
     *
     */
    public void left(double pixel) {
        this.x -= pixel;
    }
    /**
     * Takes input and lets the Object Move one right
     *
     */
    public void right() {
        this.x++;
    }
    /**
     * Takes input and lets the Object Move right with a certain pixel Speed
     *
     */
    public void right(double pixel) {
        this.x += pixel;
    }
    /**
     * Takes input and lets the Object Move one up
     *
     */
    public void up() {
        this.y--;
    }
    /**
     * Takes input and lets the Object Move up with a certain pixel Speed
     *
     */
    public void up(double pixel) {
        this.y -= pixel;
    }
    /**
     * Takes input and lets the Object Move one down
     *
     */
    public void down() {
        this.y++;
    }
    /**
     * Takes input and lets the Object Move down with a certain pixel Speed
     *
     */
    public void down(double pixel) {
        this.y += pixel;
    }

    @Override
    public String toString() {

        return "Position (" + (int) Math.round(this.x) + ", " + (int) Math.round(this.y) + ')';
    }
}
