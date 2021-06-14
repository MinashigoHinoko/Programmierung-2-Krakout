package de.thdeg.amuri.krakout.graphics.basicobject.collide;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.GameObject;

import java.awt.*;
import java.util.Objects;

/**
 * Represents all game objects that are able to collide with something.
 */
public abstract class CollidableGameObject extends GameObject implements Cloneable {
    protected Rectangle hitBox;

    protected CollidableGameObject(GameView gameView) {
        super(gameView);
        this.hitBox = new Rectangle(-100_000, -100_000, 0, 0);
    }

    @Override
    public void update() {
        super.update();
        updateHitBoxPosition();
    }

    /**
     * Used to update the hitBox position of the game object.
     */
    protected abstract void updateHitBoxPosition();

    /**
     * If a GameObject has collided with something, it is able to react to the collision.
     *
     * @param otherObject The other GameObject that is involved in the collision.
     */
    public abstract void reactToCollision(CollidableGameObject otherObject);

    @Override
    public void adaptPosition(double adaptX, double adaptY) {
        super.adaptPosition(adaptX, adaptY);
        updateHitBoxPosition();
    }

    @Override
    public void addHitboxToCanvas() {
        //this.gameView.addRectangleToCanvas(this.hitBox.x, this.hitBox.y, this.hitBox.width, this.hitBox.height, 1, false, Color.red);
    }

    /**
     * Clones the object.
     *
     * @return
     */
    @Override
    public CollidableGameObject clone() {
        return (CollidableGameObject) super.clone();
    }


    /**
     * Compares the objects hitboxes.
     *
     * @param o object.
     * @return true or false if equal or not equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CollidableGameObject that = (CollidableGameObject) o;
        return Objects.equals(hitBox, that.hitBox);
    }

    /**
     * Creates hashcode.
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hitBox);
    }
}
