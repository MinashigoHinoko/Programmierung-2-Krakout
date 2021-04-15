package de.thdeg.amuri.krakout.graphics;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.movement.Position;

/**
 * This is the pinball the player needs to play with, it will destroy {@link Brick} and collect PowerUp {@link Item}
 */
public class Pinball extends GameObject {
    private final int ammount;
    private boolean flyFromLeftToRight;

    /**
     * Creates a new pinball
     *
     * @param gameView this is for Initialising the ball
     * @see GameView
     */
    public Pinball(GameView gameView) {
        super(gameView);
        this.position = new Position(100, 100);
        this.size = 2;
        this.flyFromLeftToRight = true;
        super.width = 20;
        this.height = 20;
        this.ammount = 0;
        this.speedInPixel = 3;
    }

    /**
     * Outputs the Speed in pixel for the Items
     *
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
    public void updatePosition() {
        if (position.x >= GameView.WIDTH - width || position.x >= 860 - width) {
            this.flyFromLeftToRight = false;
        } else if (position.x <= (GameView.WIDTH - GameView.WIDTH) - width || position.x <= 25 - width) { // 25 is the not yet inputted Object of the Player
            this.flyFromLeftToRight = true;
        }
        if (this.flyFromLeftToRight == true) {
            position.right(speedInPixel);
        } else if (this.flyFromLeftToRight == false) {
            position.left(speedInPixel);
        }
    }


    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("Pinball.png", position.x, position.y, size, rotation);
    }

    @Override

    public String toString() {
        return "Pinball: " + position;
    }
}
