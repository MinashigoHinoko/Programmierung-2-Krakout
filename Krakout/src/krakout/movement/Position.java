package krakout.movement;

public class Position {
    public double x;
    public double y;

    public Position() {
        this(0, 0);
    }

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Takes input and lets the Object Move into that direction with x and y as coordinates.
     * This also exist with an addition of the pixel to determine a movementspeed.
     */
    public void left() {
        this.x = x--;
    }

    public void left(double pixel) {
        this.x += pixel;
    }

    public void right() {
        this.x++;
    }

    public void right(double pixel) {
        this.x += pixel;
    }

    public void up() {
        this.y--;
    }

    public void up(double pixel) {
        this.y -= pixel;
    }

    public void down() {
        this.y++;
    }

    public void down(double pixel) {
        this.y += pixel;
    }

    @Override
    public String toString() {

        return "Position (" + (int) Math.round(this.x) + ", " + (int) Math.round(this.y) + ')';
    }
}
