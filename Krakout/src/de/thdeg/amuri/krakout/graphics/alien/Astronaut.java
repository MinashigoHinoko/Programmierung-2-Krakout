package de.thdeg.amuri.krakout.graphics.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.Live;

/**
 * Flies around and blocks the {@link de.thdeg.amuri.krakout.graphics.Pinball}, Not a real Danger
 */
public class Astronaut extends Live {

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected Astronaut(GameView gameView) {
        super(gameView);
    }
}
