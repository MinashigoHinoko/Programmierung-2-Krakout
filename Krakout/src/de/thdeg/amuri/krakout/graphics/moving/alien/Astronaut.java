package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.LiveObject;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;

/**
 * Flies around and blocks the {@link Pinball}, Not a real Danger
 */
public class Astronaut extends LiveObject {

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected Astronaut(GameView gameView) {
        super(gameView);
    }
}
