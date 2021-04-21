package de.thdeg.amuri.krakout.graphics.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.Live;

/**
 * Will eat the {@link de.thdeg.amuri.krakout.graphics.Pinball} on collision
 */
public class Cannibal extends Live {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected Cannibal(GameView gameView) {
        super(gameView);
    }
}
