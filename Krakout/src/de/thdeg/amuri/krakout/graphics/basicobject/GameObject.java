package de.thdeg.amuri.krakout.graphics.basicobject;

import de.thdeg.amuri.krakout.game.managers.GamePlayManager;
import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.staticobject.Background;
import de.thdeg.amuri.krakout.graphics.staticobject.Brick;
import de.thdeg.amuri.krakout.movement.Position;

import java.util.Objects;

/**
 * Super of {@link Pinball}, {@link Background}, {@link Brick}
 * acts as extension of these Classes to include the shared Informations
 */
public abstract class GameObject implements Cloneable {
    protected final GameView gameView;
    protected Position position;
    protected double size;
    protected double speedInPixel;
    protected double rotation;
    protected int width;
    protected int height;
    protected GamePlayManager gamePlayManager;

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected GameObject(GameView gameView) {
        this.gameView = gameView;
        this.position = new Position(0, 0);
        this.size = 0;
        this.speedInPixel = 0;
        this.rotation = 0;
        this.width = 0;
        this.height = 0;
    }

    /**
     * We need to know the size of the Objects to track the Hitbox correctly
     *
     * @return size of an Object
     */
    public double getSize() {
        return size;
    }

    /**
     * Needed for Interaction into the Game
     *
     * @param gamePlayManager the Manager who rules the Game
     */
    public void setGamePlayManager(GamePlayManager gamePlayManager) {
        this.gamePlayManager = gamePlayManager;
    }


    /**
     * Draws to the canvas.
     */
    public abstract void addToCanvas();

    /**
     * Update objects status.
     */
    protected abstract void updateStatus();

    /**
     * Updates
     */
    public void update() {
        if (this instanceof MovingGameObject) {
            ((MovingGameObject) this).updatePosition();
        }
        this.updateStatus();
    }

    /**
     * Moves the Objects
     *
     * @param adaptX so it keeps its x coordinate
     * @param adaptY so it keeps its y coordinate
     */
    public void adaptPosition(double adaptX, double adaptY) {
        position.x += adaptX;
        position.y += adaptY;
    }


    /**
     * To transfer the Width of the Object for Hitbox calculation.
     *
     * @return Width in pixel
     */
    public int getWidth() {
        return width;
    }

    /**
     * To transfer the Height of the Object for Hitbox calculation.
     *
     * @return Height in pixel
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the {@link Position} of the game object, is used for adjusting the Position of a game object to another
     *
     * @return position of the game object
     * @see Position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * To visualize the Hitbox.
     */
    public void addHitboxToCanvas() {
    }

    @Override
    public GameObject clone() {
        GameObject gameObject = null;
        try {
            gameObject = (GameObject) super.clone();
            gameObject.position = position.clone();
        } catch (CloneNotSupportedException ignored) {
        }
        return gameObject;
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

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + position;
    }
}



