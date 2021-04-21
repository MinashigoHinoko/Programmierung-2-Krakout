package de.thdeg.amuri.krakout.graphics.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.GameObject;

/**
 * When colides with {@link de.thdeg.amuri.krakout.graphics.Bat}, will give one of following: 10.000 Points, Shield, Transmutating Bricks (which will possibly give them {@link de.thdeg.amuri.krakout.graphics.Item}) or Bomb
 */
public class BonusShip extends GameObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected BonusShip(GameView gameView) {
        super(gameView);
    }
}
