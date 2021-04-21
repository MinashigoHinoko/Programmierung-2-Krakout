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
        super.width = 12;
        this.height = 35;
        this.ammount = 0;
        this.speedInPixel = 3;
    }

    /**
     * Outputs the Speed in pixel for the Items
     *
     * @return Speed of the Ball in Pixel
     */
    public double getSpeedInPixel() {
        return this.speedInPixel;
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
        return this.flyFromLeftToRight;
    }

    /**
     * to track the Ammount of balls is necessary to run the game as smooth as possible
     *
     * @return how many balls there are at the same time
     */
    public int getAmmount() {
        return this.ammount;
    }

    /**
     * We need to know the size of the balls to track the Hitbox correctly
     *
     * @return size of ball
     */
    public double getSize() {
        return this.size;
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
        if (this.position.x >= GameView.WIDTH - this.width * this.size || this.position.x >= 860 - this.width * this.size) {
            this.flyFromLeftToRight = false;
        } else if (this.position.x <= (GameView.WIDTH - GameView.WIDTH) + this.width * this.size || this.position.x <= 25 + this.width * this.size) { // 25 is the not yet inputted Object of the Player
            this.flyFromLeftToRight = true;
        }
        if (this.flyFromLeftToRight == true) {
            this.position.right(this.speedInPixel);
        } else if (this.flyFromLeftToRight == false) {
            this.position.left(this.speedInPixel);
        }
    }


    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("Pinball.png", this.position.x, this.position.y, this.size, this.rotation);
    }

    @Override

    public String toString() {
        return "Pinball: " + this.position;
    }
}
