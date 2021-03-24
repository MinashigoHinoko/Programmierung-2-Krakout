package krakout.movement.nonplayerobject;

import krakout.movement.Position;

public class Pinball extends Position{
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
    //Initiating Position
    public Position pinball;

    //Constructor without an input
    public Pinball() {
        this(0, 0, false, 0, 3, 5, "red");
    }

    //Constructor
    public Pinball(double x, double y, boolean hit, int ammount, double speedInPixel, double size, String color) {
        super(x, y);
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
        //Initating pinball movement
        switch (direction) {
            case "LEFT":
                super.left(this.speedInPixel);
                this.x=this.x+super.x;
                this.y=this.y+super.y;
                break;
            case "RIGHT":
                super.right(this.speedInPixel);
                this.x=this.x+super.x;
                this.y=this.y+super.y;
                break;
            case "UP":
                super.up(this.speedInPixel);
                this.x=this.x+super.x;
                this.y=this.y+super.y;
                break;
            case "DOWN":
                super.down(this.speedInPixel);
                this.x=this.x+super.x;
                this.y=this.y+super.y;
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
        Position pinball = new Position(this.x, this.y);
        return "Pinball: " + pinball;
    }
}
