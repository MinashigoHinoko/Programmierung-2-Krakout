package de.thdeg.amuri.krakout.graphics;


import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.movement.Position;

/**
 * This Object will be used to create Items, these Items can be enemies,PowerUps or PowerDowns
 */
public class Item extends GameObject {
    private final boolean isHit;
    private final int live;
    //declares what kind of item it is
    private int status;
    //Ammount of items
    private int maxAmmount;

    /**
     * Constructor for filling in parameters and Building gameView
     *
     * @param gameView
     */
    public Item(GameView gameView) {
        super(gameView);
        this.isHit = false;
        this.live = 2;
        this.status = 3;
        this.size = 3;
        this.width = 6;
        this.height = 6;
    }


    /**
     * Max Ammount of Items per game compared to {@link Brick}
     *
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
     *
     * @param speedInPixel as How fast the ball falls to the player
     */
    public void setSpeedInPixel(double speedInPixel) {
        this.speedInPixel = speedInPixel * 0.8;
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
     * necessary to use the {@link Position} of the destroyed {@link Brick}
     *
     * @param position as position of the {@link Brick}
     */
    public void setPosition(Position position) {
        this.position.x = position.x;
        this.position.y = position.y;
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

    private void giveItem(int status) {
    }

    @Override
    public void updatePosition() {
        position.left(speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("Herz.png", position.x, position.y, size, rotation);
    }

    @Override
    public String toString() {
        return "Item, " + itemStatus(this.status) + " : " + position;
    }
}
