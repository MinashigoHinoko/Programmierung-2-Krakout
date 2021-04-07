package krakout.nonplayerobject;

import krakout.movement.Position;

public class Pinball {
    private final boolean hasHit;
    //Initiating Position
    private final Position position;
    private double size;
    //x Position of the ball
    private double x;
    //y Position of the ball
    private double y;
    private int ammount;
    private double speedInPixel;
    private String color;

    public Pinball() {
        this(false);
    }

    public Pinball(boolean hasHit) {
        this.hasHit = hasHit;
        position = new Position(this.x, this.y);

    }

    /**
     * Takes Input to determine where the ball gets respawned
     *
     * @param x as Position x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Takes Input to determine where the ball gets respawned
     *
     * @param y as Position y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Declares the Speed of the Ball if it gets manipulated by an item
     *
     * @param speedInPixel How fast the ball moves
     */
    public void setSpeedInPixel(double speedInPixel) {
        this.speedInPixel = speedInPixel;
    }

    /**
     * The ball will change color for a brief second to signalise the picked up item
     *
     * @param color what color the ball has
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * If the Ball hits something, this turns to true and triggers the Hit
     *
     * @return if the Ball has Hit anything
     */
    public boolean hasHit() {
        return hasHit;
    }

    /**
     * to track the Ammount of balls is necessary to run the game as smooth as possible
     *
     * @return how many balls there are at the same time
     */
    public int getAmmount() {
        return ammount;
    }

    /**
     * We need to know the size of the balls to track the Hitbox correctly
     *
     * @return size of ball
     */
    public double getSize() {
        return size;
    }

    /**
     * We need the Position of the ball to determine if it hits something
     *
     * @return position of ball as String Position ("X","Y")
     */
    public Position getPosition() {
        return position;
    }

    //adjusting it to the PixelSpeed
    public void updatePosition(String direction) {
        //Initating pinball movement
        switch (direction) {
            case "LEFT":
                position.left(this.speedInPixel);
                this.x = this.x + position.x;
                this.y = this.y + position.y;
                break;
            case "RIGHT":
                position.right(this.speedInPixel);
                this.x = this.x + position.x;
                this.y = this.y + position.y;
                break;
            case "UP":
                position.up(this.speedInPixel);
                this.x = this.x + position.x;
                this.y = this.y + position.y;
                break;
            case "DOWN":
                position.down(this.speedInPixel);
                this.x = this.x + position.x;
                this.y = this.y + position.y;
                break;
        }
    }


    //Damaging Objects
    public void damage(boolean hit) {
        if (hit = true) {
            bounce(hit);
        }
    }

    //Bouncing of walls or Objects
    public void bounce(boolean hit) {
        if (hit = true) {
        }
    }


    @Override

    public String toString() {
        return "Pinball: " + position;
    }
}
