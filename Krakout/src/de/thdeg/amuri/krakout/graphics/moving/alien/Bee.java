package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.LiveObject;
import de.thdeg.amuri.krakout.graphics.moving.Bat;

/**
 * Will glue the {@link Bat}, so it cant move
 */
public class Bee extends LiveObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected Bee(GameView gameView) {
        super(gameView);
    }
}
