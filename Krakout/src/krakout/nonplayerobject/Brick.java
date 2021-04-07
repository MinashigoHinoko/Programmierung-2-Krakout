package krakout.nonplayerobject;

import krakout.movement.Position;

public class Brick {
    private final boolean isHit;
    //initiating Position
    private Position position;
    //x Position of the Brick
    private double x;
    //y Position of the Brick
    private double y;
    //Ammount of Bricks for Win condition
    private int ammount;
    private int live;
    private double size;
    private String color;
    //Has PowerUP?
    private boolean powerUp;

    public Brick() {
        this(false);

    }

    public Brick(boolean isHit) {
        this.isHit = isHit;
        position = new Position(this.x, this.y);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setHasPowerUp(boolean haspowerUp) {
        this.powerUp = haspowerUp;
    }

    //To check if being hit
    public Position getPosition() {
        return position;
    }

    //if true, remove a live
    public boolean isHit() {
        return isHit;
    }

    public int getAmmount() {
        return ammount;
    }

    public int getLive() {
        return live;
    }

    //To check if being hit
    public double getSize() {
        return size;
    }

    public boolean hasPowerUp() {
        return powerUp;
    }
}
