package krakout.movement;

public class Position {
    //Formating x and y
    public double x;
    public double y;

    //Constructor without an input
    public Position() {
        this(0, 0);
    }

    //Constructor
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Moving LEFT method standard
    public void left() {
        this.x = x--;
    }

    //Moving LEFT method with input
    public void left(double pixel) {
        this.x += pixel;
    }

    //Moving RIGHT method standard
    public void right() {
        this.x++;
    }

    //Moving RIGHT method with input
    public void right(double pixel) {
        this.x += pixel;
    }

    //Moving UP method standard
    public void up() {
        this.y--;
    }

    //Moving UP method with input
    public void up(double pixel) {
        this.y -= pixel;
    }

    //Moving DOWN method standard
    public void down() {
        this.y++;
    }

    //Moving Down method with input
    public void down(double pixel) {
        this.y += pixel;
    }

    @Override
    public String toString() {

        return "Position (" + (int) Math.round(this.x) + ", " + (int) Math.round(this.y) + ')';
    }
}
