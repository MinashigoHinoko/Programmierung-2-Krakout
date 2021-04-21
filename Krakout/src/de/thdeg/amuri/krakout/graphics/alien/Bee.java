package de.thdeg.amuri.krakout.graphics.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.Live;

/**
 * Will glue the {@link de.thdeg.amuri.krakout.graphics.Bat}, so it cant move
 */
public class Bee extends Live {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected Bee(GameView gameView) {
        super(gameView);
    }
}
