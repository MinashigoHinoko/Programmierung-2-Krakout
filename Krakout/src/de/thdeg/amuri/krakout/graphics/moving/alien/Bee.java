package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.MovingGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidingGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Bat;

import java.util.ArrayList;

/**
 * Will glue the {@link Bat} when Hit, so it cant move
 */
public class Bee extends CollidingGameObject implements MovingGameObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView             this is for Initialising the game object
     * @param objectsToCollideWith List of Collidable
     */
    public Bee(GameView gameView, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, objectsToCollideWith);
        this.width = 35;
        this.height = 31;
        this.size = 1.5;
        this.hitBox.width = (int) (this.width * this.size);
        this.hitBox.height = (int) (this.height * this.size);
    }

    @Override
    protected void updateHitBoxPosition() {

    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {

    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("Bee.png", this.position.x, this.position.y, this.size, this.rotation);
    }

    @Override
    public void updateStatus() {

    }

    @Override
    public void updatePosition() {

    }
}
