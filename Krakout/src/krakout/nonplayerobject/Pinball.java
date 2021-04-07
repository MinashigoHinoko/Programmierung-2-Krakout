package krakout.nonplayerobject;

import krakout.movement.Position;

public class Pinball {
    private final boolean hasHit;
    private final double size;
    //Initiating Position
    private final Position position;
    //x Position of the ball
    private double x;
    //y Position of the ball
    private double y;
    private int ammount;
    private double speedInPixel;
    private String color;

    public Pinball() {
        this(false, 3, 5, "gray");
    }

    public Pinball(boolean hasHit, double speedInPixel, double size, String color) {
        this.hasHit = hasHit;
        this.speedInPixel = speedInPixel;
        this.size = size;
        this.color = color;
        position = new Position(this.x, this.y);

    }

    //Set X Position If respawn
    public void setX(double x) {
        this.x = x;
    }

    //Set Y Position if respawn
    public void setY(double y) {
        this.y = y;
    }

    //Set Speed if PowerUp or PowerDown
    public void setSpeedInPixel(double speedInPixel) {
        this.speedInPixel = speedInPixel;
    }

    //Set Color if PowerUp picked up
    public void setColor(String color) {
        this.color = color;
    }

    //Get Information if it hits something
    public boolean hasHit() {
        return hasHit;
    }

    //Get Information on the amount of balls for animation
    public int getAmmount() {
        return ammount;
    }

    //Get Size for the Hitbox of the balls
    public double getSize() {
        return size;
    }

    //Track position to check if ball hits something
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
