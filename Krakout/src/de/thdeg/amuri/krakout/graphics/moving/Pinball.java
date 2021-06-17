package de.thdeg.amuri.krakout.graphics.moving;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.MovingGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidingGameObject;
import de.thdeg.amuri.krakout.graphics.moving.alien.*;
import de.thdeg.amuri.krakout.graphics.staticobject.*;
import de.thdeg.amuri.krakout.movement.Position;

import java.util.ArrayList;

/**
 * This is the pinball the player needs to play with, it will destroy {@link Brick} and collect PowerUp {@link Item}
 */
public class Pinball extends CollidingGameObject implements MovingGameObject {
    private final int ammount;
    private final ArrayList<CollidableGameObject> collideObject;
    private boolean exist;
    private boolean bounce;
    private boolean allowBounceUpDown;
    private boolean bounceUpDown;
    private boolean isColliding;


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
        this.position = new Position(100, 100);
        this.size = 2;
        super.width = 10;
        this.height = 10;
        this.ammount = 0;
        this.speedInPixel = 2;
        this.hitBox.width = (int) (this.width * this.size);
        this.hitBox.height = (int) (this.height * this.size);
        this.exist = false;
        this.bounce = false;
        this.bounceUpDown = false;
        this.allowBounceUpDown = false;
        this.isColliding = false;
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
     * @param exist For telling the Pinball, it may move.
     */
    public void setExist(boolean exist) {
        this.exist = exist;
    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        if (otherObject.getClass() == Pinball.class) {

        } else {
            this.gamePlayManager.ballSound(otherObject);
            if (otherObject.getClass() == GameBorderLeft.class) {
                this.gamePlayManager.destroy(this);
            } else if (otherObject.getClass() == GameBorderTop.class || otherObject.getPosition().y < this.getPosition().y && (this.getPosition().x - otherObject.getPosition().x) == 0) {
                this.allowBounceUpDown = true;
                this.bounceUpDown = true;
            } else if (otherObject.getClass() == GameBorderBottom.class || otherObject.getPosition().y > this.getPosition().y && (this.getPosition().x - otherObject.getPosition().x) == 0) {
                this.allowBounceUpDown = true;
                this.bounceUpDown = false;
            }
            if (!this.isColliding) {
                if (otherObject.getClass() == GameBorderRight.class || otherObject.getClass() == Bat.class || otherObject.getClass() == Brick.class|| otherObject.getClass()== Astronaut.class||otherObject.getClass()== Face.class) {
                    this.bounce = !this.bounce;
                    if (this.gameView.timerExpired("DebugBall", "Pinball")) {
                        this.gameView.setTimer("DebugBall", "Pinball", 3000);
                        this.isColliding = !this.isColliding;
                    }
                }
            }
        }
    }

    @Override
    public void updatePosition() {
        if (this.isColliding) {
            if (this.gameView.timerExpired("DebugBall", "Pinball")) {
                this.isColliding = !this.isColliding;
            }
        }
        if (!bounce) {
            if (exist) {
                this.position.right(this.speedInPixel);
            }
        } else {
            if (exist) {
                this.position.left(this.speedInPixel);
            }
        }
        if (allowBounceUpDown) {
            if (bounceUpDown) {
                if (exist) {
                    this.position.down(this.speedInPixel);
                }
            } else {
                if (exist) {
                    this.position.up(this.speedInPixel);
                }
            }
        }
    }

    @Override
    public void updateStatus() {

    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("Pinball.png", this.position.x, this.position.y, this.size, this.rotation);
    }
}

