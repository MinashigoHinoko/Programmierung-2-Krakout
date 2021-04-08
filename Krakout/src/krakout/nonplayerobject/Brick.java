package krakout.nonplayerobject;

import krakout.movement.Position;

/**
 * This is the Brick the player has to hit with his Pinball {@link Pinball}.
 * Every Brick has to be destroyed to finish the game
 */
public class Brick {
    private final boolean isHit;
    //initiating Position
    private final Position position;
    private final int live;
    //x Position of the Brick
    private double x;
    //y Position of the Brick
    private double y;
    //Ammount of Bricks for Win condition
    private int ammount;
    private double size;
    private String color;
    //Has PowerUP?
    private boolean powerUp;

    /**
     * Brick needs pre Constructed parameters as the live and if it already has been hit
     */
    public Brick() {
        this(1, false);

    }

    /**
     * Constructer with Initialisation
     *
     * @param live  as how damaged the brick is
     * @param isHit if it has been hit, true means live reduced
     */
    public Brick(int live, boolean isHit) {
        this.live = live;
        this.isHit = isHit;
        position = new Position(this.x, this.y);
    }

    /**
     * Positions {@link Position} of the Brick
     *
     * @param x as Position x
     * @see Position
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Positions {@link Position} of the Brick
     *
     * @param y as Position y
     * @see Position
     */
    public void setY(double y) {
        this.y = y;
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
     * gets Position {@link Position} to see if ball collides with it
     *
     * @return Position of Brick as String Position ("X","Y")
     * @see Position
     */
    public Position getPosition() {
        return position;
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
     * Get Size of Brick to determine Hitbox
     *
     * @return Size of Brick
     */
    public double getSize() {
        return size;
    }

    /**
     * Get the PowerUp Status to determine if it should drop a PowerUp or not
     *
     * @return if true, has PowerUp
     */
    public boolean hasPowerUp() {
        return powerUp;
    }
}
