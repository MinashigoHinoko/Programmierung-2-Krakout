package krakout.nonplayerobject;

import krakout.gameview.GameView;
import krakout.movement.Position;

/**
 * This is the Brick the player has to hit with his {@link Pinball}.
 * Every Brick has to be destroyed to finish the game
 */
public class Brick {
    private final boolean isHit;
    private final int live;
    private final double size;
    private final double rotation;
    private final double width;
    private final double height;
    private final GameView gameView;
    //initiating Position
    private Position position;
    //x Position of the Brick
    private double x;
    //y Position of the Brick
    private double y;
    //Ammount of Bricks for Win condition
    private int ammount;
    private String color;
    //Has PowerUP?
    private boolean powerUp;

    /**
     * Creates a new Brick
     *
     * @param gameView this is for Initialising the Brick
     * @see GameView
     */
    public Brick(GameView gameView) {
        this.isHit = false;
        this.position = new Position(860, 100);
        this.live = 2;
        this.x = position.x;
        this.y = position.y;
        this.ammount = 1;
        this.size = 2;
        this.color = "RED";
        this.powerUp = false;
        this.rotation = 90;
        this.width = 5;
        this.height = 2;
        this.gameView = gameView;
    }

    /**
     * This is for accessing the Position in {@link Item} and in {@link Pinball}
     *
     * @return position of Brick
     */
    public Position getPosition() {
        return position;
    }

    /**
     * This is for generating new Bricks as Map Generation.
     *
     * @param position as  Position of the Brick
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * drawing to Canvas
     */
    public void addToCanvas() {
        switch (this.color) {
            case "RED":
                gameView.addImageToCanvas("RED.png", position.x, position.y, size, rotation);
                break;
        }
    }

    /**
     * Determines if the Brick has a PowerUp Stored
     *
     * @param haspowerUp if true, has PowerUp
     */
    public void setHasPowerUp(boolean haspowerUp) {
        this.powerUp = haspowerUp;
    }

    /**
     * If being Hit, the Live of the Brick will be reduced according to the Damage
     *
     * @return if being Hit, true = Damage
     */
    public boolean isHit() {
        return isHit;
    }

    /**
     * Get Ammount of Bricks to determine how many have to be destroyed to finish game
     *
     * @return Number of Bricks
     */
    public int getAmmount() {
        return ammount;
    }

    /**
     * Get Live of Brick to determine how often it has to be hit, to be destroyed
     *
     * @return remaining Live of Brick
     */
    public int getLive() {
        return live;
    }

    /**
     * Get the PowerUp Status to determine if it should drop a PowerUp or not
     *
     * @return if true, has PowerUp
     */
    public boolean hasPowerUp() {
        return powerUp;
    }
    private void destroy(){ }
}
