package krakout.nonplayerobject;

import krakout.movement.Position;

public class Item {
    private final boolean isHit;
    //declares what kind of item it is
    private int status;
    private double fallSpeedInPixel;
    //Ammount of items
    private int ammount;
    private int live;
    private double size;
    private String color;
    //initiating Position
    private Position position;
    //Position of the item
    private double x;
    private double y;

    public Item() {
        this(false);
    }

    public Item(boolean isHit) {
        this.isHit = isHit;
    }

    public void setfallSpeedInPixel(double fallSpeedInPixel) {
        this.fallSpeedInPixel = fallSpeedInPixel;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //Set first Position x equal to Brick Position
    public void setX(double x) {
        this.x = x;
    }

    //Set first Position y equal to Brick Position
    public void setY(double y) {
        this.y = y;
    }

    //Get the kind of Item to manipulate the player
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isHit() {
        return isHit;
    }

    public int getAmmount() {
        return ammount;
    }

    public int getLive() {
        return live;
    }

    public double getSize() {
        return size;
    }

    public Position getPosition() {
        return position;
    }

    public String itemStatus(int status) {
        switch (status) {
            case 0:
                return "PowerUp";
            case 1:
                return "PowerDown";
            case 2:
                return "Enemy";
            default:
                return "none";

        }
    }

    //adjusting it to the PixelSpeed
    public void updatePosition(String direction) {
        //Initating PowerUp movement
        switch (direction) {
            case "LEFT":
                position.left(this.fallSpeedInPixel);
                this.x = this.x + position.x;
                this.y = this.y + position.y;
                break;
            case "RIGHT":
                position.right(this.fallSpeedInPixel);
                this.x = this.x + position.x;
                this.y = this.y + position.y;
                break;
            case "UP":
                position.up(this.fallSpeedInPixel);
                this.x = this.x + position.x;
                this.y = this.y + position.y;
                break;
            case "DOWN":
                position.down(this.fallSpeedInPixel);
                this.x = this.x + position.x;
                this.y = this.y + position.y;
                break;
        }
    }

    @Override
    public String toString() {
        return "Item, " + itemStatus(this.status) + " : " + position;
    }
}
