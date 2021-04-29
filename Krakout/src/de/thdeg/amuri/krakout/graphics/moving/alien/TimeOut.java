package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.movement.Position;

/**
 * All aliens will not move for 30 seconds when Hit.
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
public class TimeOut extends AlienObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public TimeOut(GameView gameView) {
        super(gameView);
        this.live = 1;
        this.width = 29;
        this.height = 46;
        this.size = 1;
        this.position = new Position(850, 360);
        this.hit = false;
    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("TimeOut.png", this.position.x, this.position.y, this.size, this.rotation);
    }
}