package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.LiveObject;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;

/**
 * Will eat the {@link Pinball} on collision
 */
public class Cannibal extends LiveObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected Cannibal(GameView gameView) {
        super(gameView);
    }
}
