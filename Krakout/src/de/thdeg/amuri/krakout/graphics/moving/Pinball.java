package de.thdeg.amuri.krakout.graphics.moving;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.MovingGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidingGameObject;
import de.thdeg.amuri.krakout.graphics.moving.alien.Astronaut;
import de.thdeg.amuri.krakout.graphics.moving.alien.Face;
import de.thdeg.amuri.krakout.graphics.staticobject.*;
import de.thdeg.amuri.krakout.movement.Position;

import java.util.ArrayList;

/**
 * This is the pinball the player needs to play with, it will destroy {@link Brick} and collect PowerUp {@link Item}
 */
public class Pinball extends CollidingGameObject implements MovingGameObject {
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
        this.position = new Position(100, 100);
        this.size = 2;
        super.width = 10;
        this.height = 10;
        this.speedInPixel = 2;
        this.hitBox.width = (int) (this.width * this.size);
        this.hitBox.height = (int) (this.height * this.size);
        this.exist = false;
        this.bounce = false;
        this.allowBounceUpDown = false;
        this.bounceUpDown = false;
        this.isColliding = false;
    }

    @Override
    protected void updateHitBoxPosition() {
        this.hitBox.x = (int) this.position.x;
        this.hitBox.y = (int) this.position.y;
    }

    @Override
    public double getSize() {
        return super.getSize();
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
            if (this.isColliding) {
            } else {
                this.gamePlayManager.ballSound(otherObject);
                if (otherObject.getClass() == GameBorderLeft.class) {
                    this.gamePlayManager.destroy(this);
                    this.gamePlayManager.managePoints(this);
                } else if (this.getHitBox().intersectsLine(otherObject.getPosition().x, otherObject.getPosition().y + otherObject.getHeight() * otherObject.getSize(), otherObject.getPosition().x + otherObject.getWidth() * otherObject.getSize(), otherObject.getPosition().y + otherObject.getHeight() * otherObject.getSize())
                ) {
                    if (otherObject.getClass() != GameBorderBottom.class) {
                        this.allowBounceUpDown = true;
                        this.bounceUpDown = true;
                    }
                } else if (this.getHitBox().intersectsLine(otherObject.getPosition().x, otherObject.getPosition().y - otherObject.getHeight() * otherObject.getSize(), otherObject.getPosition().x + otherObject.getWidth() * otherObject.getSize(), otherObject.getPosition().y)) {
                    this.allowBounceUpDown = true;
                    this.bounceUpDown = false;
                }
                if (!this.isColliding) {
                    if (otherObject.getClass() == GameBorderRight.class || otherObject.getClass() == Bat.class || otherObject.getClass() == Brick.class || otherObject.getClass() == Astronaut.class || otherObject.getClass() == Face.class) {
                        this.bounce = !this.bounce;
                    }
                }
                if (this.bounce || this.allowBounceUpDown) {
                    if (this.gameView.timerExpired("DebugBall", "Pinball")) {
                        this.gameView.setTimer("DebugBall", "Pinball", 60);
                        this.isColliding = !this.isColliding;
                    }
                }
            }
        }
    }

    private void rollingAnimation() {
        if (this.bounce) {
            this.rotation -= this.speedInPixel * this.size;
        } else if (!this.bounce) {
            this.rotation += this.speedInPixel * this.size;
        } else {
            this.rotation = 0;
        }
    }

    @Override
    public void updatePosition() {
        if (this.isColliding) {
            if (this.gameView.timerExpired("DebugBall", "Pinball")) {
                this.isColliding = !this.isColliding;
            }
        }
        if (!this.bounce) {
            if (this.exist) {
                this.position.right(this.speedInPixel);
            }
        } else {
            if (this.exist) {
                this.position.left(this.speedInPixel);
            }
        }
        if (this.allowBounceUpDown) {
            if (this.bounceUpDown) {
                if (exist) {
                    this.position.down(this.speedInPixel);
                }
            } else {
                if (this.exist) {
                    this.position.up(this.speedInPixel);
                }
            }
        }
    }

    @Override
    public void updateStatus() {
        this.rollingAnimation();
    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("Pinball.png", this.position.x, this.position.y, this.size, this.rotation);
    }
}

