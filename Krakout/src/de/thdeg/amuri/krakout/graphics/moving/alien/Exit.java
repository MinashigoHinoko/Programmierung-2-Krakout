package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.graphics.basicobject.MovingGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;

/**
 * SKIP Level on collision
 */
public class Exit extends AlienObject implements MovingGameObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Exit(GameView gameView) {
        super(gameView);
        this.live = 1;
        this.width = 19;
        this.height = 19;
        this.size = 1.5;
        this.hit = false;
        this.hitBox.width = (int) (this.width * this.size);
        this.hitBox.height = (int) (this.height * this.size);
    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {

    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("Exit.png", this.position.x, this.position.y, this.size, this.rotation);
    }

    @Override
    public void updateStatus() {

    }
}