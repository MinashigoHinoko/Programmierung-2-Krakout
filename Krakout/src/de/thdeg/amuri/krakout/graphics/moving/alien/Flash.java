package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.LiveObject;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.staticobject.Brick;

/**
 * Pierces {@link Brick} on collision with {@link Pinball}
 */
public class Flash extends LiveObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected Flash(GameView gameView) {
        super(gameView);
    }
}
