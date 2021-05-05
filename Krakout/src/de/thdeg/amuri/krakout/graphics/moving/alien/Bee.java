package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.graphics.basicobject.MovingGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Bat;

/**
 * Will glue the {@link Bat} when Hit, so it cant move
 */
public class Bee extends AlienObject implements MovingGameObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Bee(GameView gameView) {
        super(gameView);
        this.live = 1;
        this.width = 35;
        this.height = 31;
        this.size = 1.5;
        this.hit = false;
    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("Bee.png", this.position.x, this.position.y, this.size, this.rotation);
    }

    @Override
    public void updateStatus() {

    }
}
