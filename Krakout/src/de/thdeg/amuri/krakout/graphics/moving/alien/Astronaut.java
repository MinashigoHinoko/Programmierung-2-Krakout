package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.graphics.basicobject.MovingGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;

/**
 * Flies around and blocks the {@link Pinball}, Not a real Danger
 */
public class Astronaut extends AlienObject implements MovingGameObject {

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Astronaut(GameView gameView) {
        super(gameView);
        this.live = 1;
        this.width = 32;
        this.height = 35;
        this.size = 1.5;
        this.hit = false;
    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("Astronaut.png", this.position.x, this.position.y, this.size, this.rotation);
    }

    @Override
    public void updateStatus() {

    }
}
