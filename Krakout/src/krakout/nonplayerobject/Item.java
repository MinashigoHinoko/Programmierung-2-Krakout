package krakout.nonplayerobject;

import krakout.gameview.GameView;
import krakout.movement.Position;

/**
 * This Object will be used to create Items, these Items can be enemies,PowerUps or PowerDowns
 */
public class Item {
    private final boolean isHit;
    private final int live;
    private final GameView gameView;
    private final int rotation;
    private final double width;
    private final double height;
    //initiating Position
    private Position position;
    //declares what kind of item it is
    private int status;
    private double fallSpeedInPixel;
    //Ammount of items
    private int maxAmmount;
    private double size;
    //Position of the item
    private double x;
    private double y;
    private boolean fallFromUptoDown;

    /**
     * Constructor for filling in parameters and Building gameView
     *
     * @param gameView
     */
    public Item(GameView gameView) {
        this.isHit = false;
        this.live = 2;
        this.status = 3;
        this.size = 3;
        this.gameView = gameView;
        this.rotation = 0;
        this.fallFromUptoDown = false;
        this.width = 6;
        this.height = 6;
        this.position = new Position(300, 100);
        this.x = position.x;
        this.y = position.y;
    }

    /**
     * Draws the Pinball to the canvas.
     */
    public void addToCanvas() {
        gameView.addImageToCanvas("Herz.png", position.x, position.y, size, rotation);
    }

    /**
     * Max Ammount of Items per game compared to {@link Brick}
     * @param brickAmmount the ammount of {@link Brick} in the Game
     */
    public void setMaxAmmount(int brickAmmount) {
        this.maxAmmount = brickAmmount / 5;
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
     * gets the remaining Live points of the Item
     *
     * @return Live as a Number
     */
    public int getLive() {
        return live;
    }

    /**
     * necessary to catch How fast the {@link Pinball} flies to adjust to its speed
     * @param fallSpeedInPixel as How fast the ball falls to the player
     */
    public void setFallSpeedInPixel(double fallSpeedInPixel) {
        this.fallSpeedInPixel = fallSpeedInPixel;
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
     * necessary to use the {@link Position} of the destroyed {@link Brick}
     * @param position as position of the {@link Brick}
     */
    public void setPosition(Position position) {
        this.position = position;
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
            case 3:
                return "Health UP";
            default:
                return "none";

        }
    }

    /**
     * Updating Visual Movement of the Item
     */
    private void updatePosition() {
        if (position.x >= 860 - width) {
            this.fallFromUptoDown = false;
        } else if (position.x <= 25 - width) {
            this.fallFromUptoDown = true;
        }
        if (this.fallFromUptoDown == true) {
            position.right(fallSpeedInPixel);
            this.x = position.x;
        } else if (this.fallFromUptoDown == false) {
            position.left(fallSpeedInPixel);
            this.x = position.x;
        }
    }

    @Override
    public String toString() {
        return "Item, " + itemStatus(this.status) + " : " + position;
    }
}
