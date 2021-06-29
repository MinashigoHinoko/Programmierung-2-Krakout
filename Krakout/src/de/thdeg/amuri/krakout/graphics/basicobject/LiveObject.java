package de.thdeg.amuri.krakout.graphics.basicobject;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;

import java.util.Objects;

/**
 * Acts as a Parent for classes that need a live system
 */
public abstract class LiveObject extends CollidableGameObject implements Cloneable {
    protected boolean hit;
    protected int totalLive;
    protected int live;

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected LiveObject(GameView gameView) {
        super(gameView);
        this.hit = false;
        this.totalLive = 0;
        this.live = 0;
    }

    /**
     * Manipulate life to increase or decrease values
     *
     * @param live is the value to be removed
     */
    public void manipulateLive(int live) {
        this.live += live;
    }

    /**
     * Get Live of Object to determine how often it has to be hit, to be destroyed
     *
     * @return remaining Live of Brick
     */
    public int getLive() {
        return this.live;
    }

    /**
     * @param live as the current Live of the Object
     */
    public void setLive(int live) {
        this.live = live;
    }

    /**
     * Get Total Live of Object to determine how many Live it should have
     *
     * @return Total Live of Brick
     */
    public int getTotalLive() {
        return this.totalLive;
    }

    /**
     * Manipulate maximum life to increase or decrease values
     *
     * @param totalLive is the value to be removed
     */
    public void manipulateTotalLive(int totalLive) {
        this.totalLive += totalLive;
    }

    /**
     * determines if got hit
     */
    public void hasHit() {
        this.hit = true;
    }

    @Override
    public CollidableGameObject clone() {
        LiveObject liveObject = null;
        liveObject = (LiveObject) super.clone();
        liveObject.position = position.clone();
        return liveObject;
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
