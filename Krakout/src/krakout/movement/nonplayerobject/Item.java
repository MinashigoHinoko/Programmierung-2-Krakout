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
    //Initating PowerUp movement
    Position item = new Position(x, y);

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
        switch (direction) {
            case "LEFT":
                item.left(this.speedInPixel);
                break;
            case "RIGHT":
                item.right(this.speedInPixel);
                break;
            case "UP":
                item.up(this.speedInPixel);
                break;
            case "DOWN":
                item.down(this.speedInPixel);
                break;
        }
    }
    public void Position(){

    }

    @Override
    public String toString() {
        return "Item, " + itemStatus(this.status) + " : " + this.item;
    }
}
