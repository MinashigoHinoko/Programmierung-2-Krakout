package krakout.movement.nonPlayerObject;

import krakout.movement.Position;

public class Pinball {
    //Position of the ball
    public double x;
    public double y;
    //When hit destroy
    public boolean hit;
    //Ammount of balls
    public int ammount;
    //Movement Speed
    public double speedInPixel;
    //Size of ball
    public double size;
    //Color of ball
    public String color;
    //Initating pinball movement
    Position pinball = new Position(x, y);

    //Constructor without an input
    public Pinball() {
        this(0, 0, false, 0, 3, 5, "red");
    }

    //Constructor
    public Pinball(double x, double y, boolean hit, int ammount, double speedInPixel, double size, String color) {
        this.x = x;
        this.y = y;
        this.hit = hit;
        this.ammount = ammount;
        this.speedInPixel = speedInPixel;
        this.size = size;
        this.color = color;

    }

    //adjusting it to the PixelSpeed
    public void updatePosition(String direction) {
        switch (direction) {
            case "LEFT":
                pinball.left(this.speedInPixel);
                break;
            case "RIGHT":
                pinball.right(this.speedInPixel);
                break;
            case "UP":
                pinball.up(this.speedInPixel);
                break;
            case "DOWN":
                pinball.down(this.speedInPixel);
                break;
        }
    }

    /*
        //Damaging Objects
        public void damage(boolean hit){
            if(hit=true){
                bounce(hit);
            }
        }

        //Bouncing of walls or Objects
        public void bounce(boolean hit){
            if(hit=true){
            }
    }

     */
    @Override
    public String toString() {
        return "Pinball: " + pinball;
    }
}
