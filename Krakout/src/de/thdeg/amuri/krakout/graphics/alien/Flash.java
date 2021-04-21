package de.thdeg.amuri.krakout.graphics.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.Live;

/**
 * Pierces {@link de.thdeg.amuri.krakout.graphics.Brick} on collision with {@link de.thdeg.amuri.krakout.graphics.Pinball}
 */
public class Flash extends Live {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected Flash(GameView gameView) {
        super(gameView);
    }
}
