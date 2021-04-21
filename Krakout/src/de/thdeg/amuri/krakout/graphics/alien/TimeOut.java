package de.thdeg.amuri.krakout.graphics.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.GameObject;

/**
 * All aliens will not move for 30 seconds
 *
 * @see Astronaut
 * @see Bee
 * @see BeeHive
 * @see BonusShip
 * @see Cannibal
 * @see Egg
 * @see Exit
 * @see Face
 * @see Flash
 * @see TimeOut
 * @see TwinBall
 */
public class TimeOut extends GameObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected TimeOut(GameView gameView) {
        super(gameView);
    }
}
