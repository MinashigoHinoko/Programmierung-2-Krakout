package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.graphics.basicobject.MovingGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;

import java.util.ArrayList;

/**
 * On collision with {@link Pinball}, second {@link Pinball} will be activated.
 */
public class TwinBall extends AlienObject implements MovingGameObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public TwinBall(    GameView gameView, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView,objectsToCollideWith);
        this.width = 21;
        this.height = 19;
        this.size = 1;
        this.hitBox.width = (int) (this.width * this.size);
        this.hitBox.height = (int) (this.height * this.size);
    }


    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("TwinBall.png", this.position.x, this.position.y, this.size, this.rotation);
    }

    @Override
    public void updateStatus() {

    }
}