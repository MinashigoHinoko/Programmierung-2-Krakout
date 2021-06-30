package de.thdeg.amuri.krakout.graphics.basicobject;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidingGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.movement.Position;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Object for every Alien class
 */
public abstract class AlienObject extends CollidingGameObject implements MovingGameObject, Cloneable {
    private final Random random;
    private boolean endOfScreenRight;
    private boolean endOfScreenLeft;
    private boolean endOfScreenUp;
    private boolean endOfScreenDown;
    private int y;
    private int x;

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected AlienObject(GameView gameView, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView,objectsToCollideWith);
        this.speedInPixel = 2.5;
        this.random = new Random();
        while (y < 70) {
            this.y = random.nextInt(460);
        }
        while (x < 60) {
            this.x = random.nextInt((int) (GameView.WIDTH - (this.width * this.size)));
        }
        this.position = new Position(this.x, this.y);
    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        if(otherObject.getClass() == this.getClass()){
            if (otherObject.getHitBox().intersectsLine(otherObject.getPosition().x, otherObject.getPosition().y, otherObject.getPosition().x, otherObject.getPosition().y + otherObject.getHeight())) {
                this.endOfScreenRight = true;
                this.endOfScreenLeft = false;
            }
            if (otherObject.getHitBox().intersectsLine(otherObject.getPosition().x + otherObject.getWidth(), otherObject.getPosition().y, otherObject.getPosition().x + otherObject.getWidth(), otherObject.getPosition().y + otherObject.getHeight())) {
                this.endOfScreenRight = true;
                this.endOfScreenLeft = false;
            }
            if (this.getHitBox().intersectsLine(otherObject.getPosition().x, otherObject.getPosition().y - otherObject.getHeight() * otherObject.getSize(), otherObject.getPosition().x + otherObject.getWidth() * otherObject.getSize(), otherObject.getPosition().y)) {
                this.endOfScreenUp = true;
                this.endOfScreenDown = false;
            }
            if (this.getHitBox().intersectsLine(otherObject.getPosition().x, otherObject.getPosition().y + otherObject.getHeight() * otherObject.getSize(), otherObject.getPosition().x + otherObject.getWidth() * otherObject.getSize(), otherObject.getPosition().y + otherObject.getHeight() * otherObject.getSize())) {
                this.endOfScreenDown = true;
                this.endOfScreenUp = false;
            }
        }else{
            this.gamePlayManager.destroy(this);
        }
        if(otherObject.getClass() == Pinball.class){
            this.gamePlayManager.managePoints(this);
        }
    }

    @Override
    protected void updateHitBoxPosition() {
        this.hitBox.x = (int) this.position.x;
        this.hitBox.y = (int) this.position.y;
    }

    @Override
    public void updatePosition() {
        switch (this.random.nextInt(5)) {
            case 1:
                if (this.endOfScreenRight) {
                    this.position.left(this.speedInPixel);
                    break;
                } else {
                    this.position.right(this.speedInPixel);
                    break;
                }
            case 2:
                if (this.endOfScreenLeft == true) {
                    this.position.right(this.speedInPixel);
                    break;
                } else {
                    this.position.left(this.speedInPixel);
                    break;
                }
            case 3:
                if (this.endOfScreenUp == true) {
                    this.position.down(this.speedInPixel);
                    break;
                } else {
                    this.position.up(this.speedInPixel);
                    break;
                }
            case 4:
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
