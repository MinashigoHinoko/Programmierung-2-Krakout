package de.thdeg.amuri.krakout.graphics.basicobject;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.movement.Position;

import java.util.Objects;
import java.util.Random;

/**
 * Object for every Alien class
 */
public abstract class AlienObject extends LiveObject implements MovingGameObject, Cloneable {
    private double x;
    private boolean endOfScreenRight;
    private boolean endOfScreenLeft;
    private boolean endOfScreenUp;
    private boolean endOfScreenDown;
    private final Random random;

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected AlienObject(GameView gameView) {
        super(gameView);
        this.live = 1;
        this.speedInPixel = 2.5;
        this.hit = false;
        this.random = new Random();
        this.position = new Position(random.nextInt(GameView.WIDTH), random.nextInt(GameView.HEIGHT - (GameView.HEIGHT / 10)));
    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        if (otherObject.getClass() == Pinball.class) {
            this.gamePlayManager.destroy(this);
        }
    }

    @Override
    protected void updateHitBoxPosition() {
        this.hitBox.x = (int) this.position.x;
        this.hitBox.y = (int) this.position.y;
    }

    @Override
    public void updatePosition() {
        x = Math.random() * 10;
        while ((int) x == 0) {
            x = Math.random() * 10;
            if ((int) x != 0) {
                x = (10 % (int) x);
            }
        }
        switch ((int) x) {
            case 1:
                if (this.position.x >= GameView.WIDTH - this.width * this.size) {
                    this.endOfScreenRight = true;
                    this.endOfScreenLeft = false;
                }
                if (this.endOfScreenRight == true) {
                    this.position.left(this.speedInPixel);
                    break;
                } else {
                    this.position.right(this.speedInPixel);
                    break;
                }
            case 2:
                if (this.position.x <= (GameView.WIDTH - GameView.WIDTH) + this.width * this.size) {
                    this.endOfScreenLeft = true;
                    this.endOfScreenRight = false;
                }
                if (this.endOfScreenLeft == true) {
                    this.position.right(this.speedInPixel);
                    break;
                } else {
                    this.position.left(this.speedInPixel);
                    break;
                }
            case 3:
                if (this.position.y <= (50) + (this.height * this.size)) {
                    this.endOfScreenUp = true;
                    this.endOfScreenDown = false;
                }
                if (this.endOfScreenUp == true) {
                    this.position.down(this.speedInPixel);
                    break;
                } else {
                    this.position.up(this.speedInPixel);
                    break;
                }
            case 4:
                if (this.position.y >= (GameView.HEIGHT - 50 - (this.height * this.size))) {
                    this.endOfScreenDown = true;
                    this.endOfScreenUp = false;
                }
                if (this.endOfScreenDown == true) {
                    this.position.up((this.speedInPixel));
                    break;
                } else {
                    this.position.down(this.speedInPixel);
                    break;
                }
        }


    }

    @Override
    public CollidableGameObject clone() {
        AlienObject alienObject = null;
        alienObject = (AlienObject) super.clone();
        alienObject.position = position.clone();
        return alienObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameObject that = (GameObject) o;
        return Double.compare(that.speedInPixel, speedInPixel) == 0
                && Double.compare(that.rotation, rotation) == 0
                && Double.compare(that.size, size) == 0 && width == that.width
                && height == that.height && position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, speedInPixel, rotation, size, width, height);
    }
}
