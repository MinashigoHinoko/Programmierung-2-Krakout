package de.thdeg.amuri.krakout.graphics.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.GameObject;

/**
 * On contact with {@link de.thdeg.amuri.krakout.graphics.Brick}, will damage them
 */
public class Egg extends GameObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected Egg(GameView gameView) {
        super(gameView);
    }
}
