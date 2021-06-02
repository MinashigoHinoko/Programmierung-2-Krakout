package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.graphics.basicobject.MovingGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;

/**
 * Will eat the {@link Pinball} on collision
 */
public class Cannibal extends AlienObject implements MovingGameObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Cannibal(GameView gameView) {
        super(gameView);
        this.live = 1;
        this.width = 10;
        this.height = 10;
        this.size = 2;
        this.hit = false;
        this.hitBox.width = (int) (this.width * this.size);
        this.hitBox.height = (int) (this.height * this.size);
    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {

    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("Cannibal.png", this.position.x, this.position.y, this.size, this.rotation);
    }

    @Override
    public void updateStatus() {

    }
}