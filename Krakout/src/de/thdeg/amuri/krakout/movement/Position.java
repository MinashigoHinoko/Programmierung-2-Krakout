package de.thdeg.amuri.krakout.movement;

import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.staticobject.Brick;

import java.util.Objects;

/**
 * Main Object for the Movement of other objects
 */
public class Position implements Cloneable, Comparable<Position> {
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
     * @see Pinball
     * @see Brick
     */
    public Position() {
        this(0, 0);
    }

    /**
     * Constructor for the x and y Coordinate for Position
     *
     * @param x Position
     * @param y Position
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Takes input and lets the Object Move left with a certain pixel Speed
     */
    public void left(double pixel) {
        this.x -= pixel;
    }

    /**
     * Takes input and lets the Object Move right with a certain pixel Speed
     */
    public void right(double pixel) {
        this.x += pixel;
    }

    /**
     * Takes input and lets the Object Move up with a certain pixel Speed
     */
    public void up(double pixel) {
        this.y -= pixel;
    }

    /**
     * Takes input and lets the Object Move down with a certain pixel Speed
     */
    public void down(double pixel) {
        this.y += pixel;
    }

    /**
     * Calculates the distance to any other position.
     *
     * @param other Position to calculate the distance to.
     * @return The distance.
     */
    public double distance(Position other) {
        return Math.sqrt(Math.pow((x - other.x), 2) + Math.pow((y - other.y), 2));
    }

    @Override
    public int compareTo(Position o) {
        Position zero = new Position(0, 0);
        return (int) Math.signum(this.distance(zero) - o.distance(zero));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Position (" + (int) Math.round(this.x) + ", " + (int) Math.round(this.y) + ")";
    }

    @Override
    public Position clone() {
        Position clone = null;
        try {
            clone = (Position) super.clone();
        } catch (CloneNotSupportedException ignored) {
        }
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
