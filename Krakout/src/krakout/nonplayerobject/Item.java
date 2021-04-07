package krakout.nonplayerobject;

import krakout.movement.Position;

public class Item {
    private final boolean isHit;
    //declares what kind of item it is
    private int status;
    private double fallSpeedInPixel;
    //Ammount of items
    private int maxAmmount;
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
        position = new Position(this.x, this.y);
    }

    /**
     * The Item falls at the same speed as the ball flies.
     *
     * @param fallSpeedInPixel Speed of the Item
     */
    public void setfallSpeedInPixel(double fallSpeedInPixel) {
        this.fallSpeedInPixel = fallSpeedInPixel;
    }

    /**
     * Items will change color for a brief second when hit.
     *
     * @param color of the item
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Sets first Position, will be identical to the Pos of the brick
     *
     * @param x Position x of Brick
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets first Position, will be identical to the Pos of the brick
     *
     * @param y Position y of Brick
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * To Output the Information, what kind of item manipulates the game/player
     *
     * @return status as number, this will convert in the Output to the actual Item
     * */
    public int getStatus() {
        return status;
    }

    /**
     * To Input the Information, what kind of item manipulates the game/player
     *
     * @param status as number, this will convert in the Output to the actual Item
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Determines if the Item is Hit and calculates dmg
     *
     * @return  true if hit and false if not
     */
    public boolean isHit() {
        return isHit;
    }

    /**
     * gets the Max Ammount of Items there are in the game
     *
     * @return  Max Ammount of Items
     */
    public int getMaxAmmount() {
        return maxAmmount;
    }

    /**
     * gets the remaining Live points of the Item
     *
     * @return  Live as a Number
     */
    public int getLive() {
        return live;
    }

    /**
     * Get the Size of the Item for Hitbox calculation
     *
     * @return  Size as a Number
     */
    public double getSize() {
        return size;
    }

    /**
     * Get the Position of the Item to determine if being Hit
     *
     * @return Position of Item as String Position ("X","Y")
     */
    public Position getPosition() {
        return position;
    }

    /**
     * For determining which kind of Item it is
     *
     * @param status    takes the Status and looks for it in the switch
     * @return  kind of Item
     */
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

    /**
     * Movement of the Item
     *
     * @param direction the way the Item decides to move
     * */
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
