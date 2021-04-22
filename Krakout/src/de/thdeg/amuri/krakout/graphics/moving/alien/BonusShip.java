package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.LiveObject;
import de.thdeg.amuri.krakout.graphics.moving.Bat;
import de.thdeg.amuri.krakout.graphics.staticobject.Item;

/**
 * When colides with {@link Bat}, will give one of following: 10.000 Points, Shield, Transmutating Bricks (which will possibly give them {@link Item}) or Bomb
 */
public class BonusShip extends LiveObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected BonusShip(GameView gameView) {
        super(gameView);
    }
}
