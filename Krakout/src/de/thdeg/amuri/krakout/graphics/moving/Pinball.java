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
    private Position bouncePosition;
    private Position bounceBrickPosition;
    private GameBorderRight gameBorderRight;
    private GameBorderLeft gameBorderLeft;
    private Bat bat;

    /**
     *      Creates a new pinball
     *
     * @param gameView this is for Initialising the ball
     * @param objectsToCollideWith List of Collidable
     *
     * @see GameView
     */
    public Pinball(GameView gameView, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, objectsToCollideWith);
        this.gameBorderRight = (GameBorderRight)objectsToCollideWith.get(3);
        this.gameBorderLeft = (GameBorderLeft)objectsToCollideWith.get(2);
        this.position = new Position(100, 100);
        this.bouncePosition = new Position(0, 0);
        this.bounceBrickPosition = new Position(0, 0);
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

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        this.position.right(this.speedInPixel);
    }

    /**
     * needed for tracking Position of the {@link Bat}
     *
     * @param bouncePosition as the Position (x,y) of the {@link Bat} so the ball bounces off
     */
    public void setBouncePosition(Position bouncePosition) {
        this.bouncePosition = bouncePosition;
    }

    /**
     * needed for tracking Position of the {@link Brick
     *
     * @param bounceBrickPosition as the Position (x,y) of the {@link Brick} so the ball bounces off
     */
    public void setBounceBrickPosition(Position bounceBrickPosition) {
        this.bounceBrickPosition = bounceBrickPosition;
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

        if (collidesWith(this.gameBorderRight)) {
            this.flyFromLeftToRight = false;
        } else if (collidesWith(this.gameBorderLeft)) {
            this.flyFromLeftToRight = true;
        }
        if (this.flyFromLeftToRight == true) {
            System.out.println("It should fly right");
            this.position.right(this.speedInPixel);
        } else if(this.flyFromLeftToRight == false) {
            System.out.println("It should fly left");
            this.position.left(this.speedInPixel);
        }
    }

    @Override
    public void updateStatus() {
        if (collidesWith(this.gameBorderLeft)) {
            this.gamePlayManager.destroyPinball(this);
        }

    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("Pinball.png", this.position.x, this.position.y, this.size, this.rotation);
    }
}
