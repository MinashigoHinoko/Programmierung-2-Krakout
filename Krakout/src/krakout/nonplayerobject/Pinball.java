package krakout.nonplayerobject;

import krakout.gameview.GameView;
import krakout.movement.Position;

/**
 * This is the pinball the player needs to play with, it will destroy {@link Brick} and collect PowerUp {@link Item}
 */
public class Pinball {
    private final GameView gameView;
    //Initiating Position
    private final Position position;
    private final double size;
    private final double rotation;
    private final double width;
    private final double height;
    private final int ammount;
    private double speedInPixel;
    private boolean flyFromLeftToRight;
    //x Position of the ball
    private double x;
    //y Position of the ball
    private double y;

    /**
     * Creates a new pinball
     *
     * @param gameView this is for Initialising the ball
     * @see GameView
     */
    public Pinball(GameView gameView) {
        this.gameView = gameView;
        this.position = new Position(100, 100);
        this.size = 2;
        this.flyFromLeftToRight = true;
        this.rotation = 0;
        this.width = 20;
        this.height = 20;
        this.x = position.x;
        this.y = position.y;
        this.ammount = 0;
        this.speedInPixel = 3;
    }

    /**
     * Draws the Pinball to the canvas.
     */
    public void addToCanvas() {
        gameView.addImageToCanvas("Pinball.png", position.x, position.y, size, rotation);
    }

    /**
     * Takes Input to determine where the ball gets respawned
     * {@link Position}
     *
     * @param x as Position x
     * @see Position
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Takes Input to determine where the ball gets respawned
     * <p>
     * {@link Position}
     *
     * @param y as Position y
     * @see Position
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Outputs the Speed in pixel for the Items
     * @return Speed of the Ball in Pixel
     */
    public double getSpeedInPixel() {
        return speedInPixel;
    }

    /**
     * Declares the Speed of the Ball if it gets manipulated by an item
     *
     * @param speedInPixel How fast the ball moves
     */
    public void setSpeedInPixel(double speedInPixel) {
        this.speedInPixel = speedInPixel;
    }

    /**
     * If the Ball hits something, this turns to true and triggers the Hit
     *
     * @return if the Ball has Hit anything
     */
    public boolean hasHit() {
        return flyFromLeftToRight;
    }

    /**
     * to track the Ammount of balls is necessary to run the game as smooth as possible
     *
     * @return how many balls there are at the same time
     */
    public int getAmmount() {
        return ammount;
    }

    /**
     * We need to know the size of the balls to track the Hitbox correctly
     *
     * @return size of ball
     */
    public double getSize() {
        return size;
    }

    /**
     * We need the Position {@link Position} of the ball to determine if it hits something
     *
     * @return position of ball as String Position ("X","Y")
     * @see Position
     */
    public Position getPosition() {
        return position;
    }


    /**
     * Updating Visual Movement of the ball
     */
    public void updatePosition() {
        if (position.x >= GameView.WIDTH - width || position.x >= (860) - width) {
            this.flyFromLeftToRight = false;
        } else if (position.x <= (GameView.WIDTH - GameView.WIDTH) - width || position.x <= 25 - width) { // 20 is the not yet inputted Object of the Player
            this.flyFromLeftToRight = true;
        }
        if (this.flyFromLeftToRight == true) {
            position.right(speedInPixel);
            this.x = position.x;
        } else if (this.flyFromLeftToRight == false) {
            position.left(speedInPixel);
            this.x = position.x;
        }
    }


    /**
     * detemines if pinball hits something to call bounce
     */
    private void damage() {
        if (this.flyFromLeftToRight = true) {
            bounce();
        }
    }

    /**
     * ball bounces of hitted element.
     */
    private void bounce() {

    }

    @Override

    public String toString() {
        return "Pinball: " + position;
    }
}
