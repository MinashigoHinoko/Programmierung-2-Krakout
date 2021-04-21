package de.thdeg.amuri.krakout.graphics.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.GameObject;

/**
 * On collision with {@link de.thdeg.amuri.krakout.graphics.Pinball}, second {@link de.thdeg.amuri.krakout.graphics.Pinball} will be activated.
 */
public class TwinBall extends GameObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected TwinBall(GameView gameView) {
        super(gameView);
    }
}
