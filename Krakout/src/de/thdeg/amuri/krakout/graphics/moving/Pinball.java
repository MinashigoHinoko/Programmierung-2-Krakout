package de.thdeg.amuri.krakout.graphics.moving;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.MovingGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidingGameObject;
import de.thdeg.amuri.krakout.graphics.staticobject.*;
import de.thdeg.amuri.krakout.movement.Position;

import java.util.ArrayList;

/**
 * This is the pinball the player needs to play with, it will destroy {@link Brick} and collect PowerUp {@link Item}
 */
public class Pinball extends CollidingGameObject implements MovingGameObject {
    private final int ammount;
    private boolean flyFromLeftToRight;
    private GameBorderTop gameBorderTop;
    private GameBorderBottom gameBorderBottom;
    private GameBorderLeft gameBorderLeft;
    private ArrayList<CollidableGameObject> collideObject;

    /**
     * Creates a new pinball
     *
     * @param gameView             this is for Initialising the ball
     * @param objectsToCollideWith List of Collidable
     * @see GameView
     */
    public Pinball(GameView gameView, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, objectsToCollideWith);
        this.collideObject = new ArrayList<>();
        this.collideObject.addAll(objectsToCollideWith);
        this.gameBorderLeft = (GameBorderLeft) objectsToCollideWith.get(2);
        this.gameBorderTop = (GameBorderTop) objectsToCollideWith.get(0);
        this.gameBorderBottom = (GameBorderBottom) objectsToCollideWith.get(1);
        this.position = new Position(100, 100);
        this.size = 2;
        this.flyFromLeftToRight = true;
        super.width = 10;
        this.height = 10;
        this.ammount = 0;
        this.speedInPixel = 2;
        this.hitBox.width = (int) (this.width * this.size);
        this.hitBox.height = (int) (this.height * this.size);
    }

    @Override
    protected void updateHitBoxPosition() {
        this.hitBox.x = (int) this.position.x;
        this.hitBox.y = (int) this.position.y;
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
    public void reactToCollision(CollidableGameObject otherObject) {
            if (this.flyFromLeftToRight == true) {
                this.flyFromLeftToRight = false;
            } else if (this.flyFromLeftToRight == false) {
                this.flyFromLeftToRight = true;
            }
    }
    @Override
    public void updatePosition() {
        if (collidesWith(gameBorderLeft)) {
            this.gamePlayManager.destroyPinball(this);
        }
        if (collidesWith(gameBorderTop)) {
            this.position.down(this.speedInPixel);
        }
        if (collidesWith(gameBorderBottom)) {
            this.position.up(this.speedInPixel);
        }

        if (this.flyFromLeftToRight == true) {
            this.position.right(this.speedInPixel);
        } else if (this.flyFromLeftToRight == false) {
            this.position.left(this.speedInPixel);
        }
    }

    @Override
    public void updateStatus() {
        if (collidesWith(gameBorderLeft)) {
            this.gamePlayManager.destroyPinball(this);
        }

    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("Pinball.png", this.position.x, this.position.y, this.size, this.rotation);
    }
}

