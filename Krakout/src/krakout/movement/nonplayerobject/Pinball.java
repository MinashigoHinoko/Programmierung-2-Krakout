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
    Position pinball = new Position();

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
        pinball.x=this.x;
        pinball.y=this.y;

    }

    //adjusting it to the PixelSpeed
    public void updatePosition(String direction) {
        //Initating pinball movement
        switch (direction) {
            case "LEFT":
                pinball.left(this.speedInPixel);
                this.x=this.x+pinball.x;
                this.y=this.y+pinball.y;
                break;
            case "RIGHT":
                pinball.right(this.speedInPixel);
                this.x=this.x+pinball.x;
                this.y=this.y+pinball.y;
                break;
            case "UP":
                pinball.up(this.speedInPixel);
                this.x=this.x+pinball.x;
                this.y=this.y+pinball.y;
                break;
            case "DOWN":
                pinball.down(this.speedInPixel);
                this.x=this.x+pinball.x;
                this.y=this.y+pinball.y;
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
