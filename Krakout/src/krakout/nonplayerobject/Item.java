package krakout.nonplayerobject;

import krakout.movement.Position;

/**
 * This Object will be used to create Items, these Items can be enemies,PowerUps or PowerDowns
 */
public class Item {
    private final boolean isHit;
    private final int live;
    //initiating Position
    private final Position position;
    //declares what kind of item it is
    private int status;
    private double fallSpeedInPixel;
    //Ammount of items
    private int maxAmmount;
    private double size;
    private String color;
    //Position of the item
    private double x;
    private double y;

    /**
     * Item needs pre Constructed parameters as the live and if it already has been hit
     */
    public Item() {
        this(2, false);
    }

    /**
     * Constructer with Initialisation
     *
     * @param live  as how damaged the item is
     * @param isHit if it has been hit, true means live reduced
     */
    public Item(int live, boolean isHit) {
        this.live = live;
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
     * Sets first Position{@link Position}, will be identical to the Pos of the brick
     *
     * @param x Position x of Brick
     * @see Position
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets first Position{@link Position}, will be identical to the Pos of the brick
     *
     * @param y Position y of Brick
     * @see Position
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * To Output the Information, what kind of item manipulates the game/player
     *
     * @return status as number, this will convert in the Output to the actual Item
     */
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
     * @return true if hit and false if not
     */
    public boolean isHit() {
        return isHit;
    }

    /**
     * gets the Max Ammount of Items there are in the game
     *
     * @return Max Ammount of Items
     */
    public int getMaxAmmount() {
        return maxAmmount;
    }

    /**
     * gets the remaining Live points of the Item
     *
     * @return Live as a Number
     */
    public int getLive() {
        return live;
    }

    /**
     * Get the Size of the Item for Hitbox calculation
     *
     * @return Size as a Number
     */
    public double getSize() {
        return size;
    }

    /**
     * Get the Position of the Item to determine if being Hit
     *
     * @return Position {@link Position} of Item as String Position ("X","Y")
     * @see Position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * For determining which kind of Item it is
     *
     * @param status takes the Status and looks for it in the switch
     * @return kind of Item
     */
    private String itemStatus(int status) {
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
     *                  (this has the sole purpose of testing the movement and will be removed)
     */
    private void updatePosition(String direction) {
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
