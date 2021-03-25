package krakout.movement.nonplayerobject;

import krakout.movement.Position;

public class Item {
    //Position of the item
    public double x;
    public double y;
    //declares what kind of item it is
    public int status;
    //When being hit;
    public boolean hit;
    //Movement speed
    public double speedInPixel;
    //Ammount of items
    public int ammount;
    //Livepoints
    public int live;
    //Size of item
    public double size;
    //Color of item
    public String color;
    //initiating Position
    Position position;

    //Constructor without an input
    public Item() {
        this(0, 0, 1, false, 2, 0, 0, 5, "green");
    }

    //Constructor
    public Item(double x, double y, int status, boolean hit, double speedInPixel, int ammount, int live, double size, String color) {
        this.x = x;
        this.y = y;
        this.status = status;
        this.hit = hit;
        this.speedInPixel = speedInPixel;
        this.ammount = ammount;
        this.live = live;
        this.size = size;
        this.color = color;
        position = new Position(this.x,this.y);
    }

    public String itemStatus(int status) {
        switch (status) {
            case 0:
                return "PowerUp";
            case 1:
                return "Wall";
            case 2:
                return "Brick";
            default:
                return "none";

        }
    }

    //adjusting it to the PixelSpeed
    public void updatePosition(String direction) {
        //Initating PowerUp movement
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

    @Override
    public String toString() {
        return "Item, " + itemStatus(this.status) + " : " + position;
    }
}
