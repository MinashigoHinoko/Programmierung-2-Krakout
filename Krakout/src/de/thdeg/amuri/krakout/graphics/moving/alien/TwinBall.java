package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.movement.Position;

/**
 * On collision with {@link Pinball}, second {@link Pinball} will be activated.
 */
public class TwinBall extends AlienObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public TwinBall(GameView gameView) {
        super(gameView);
        this.live = 1;
        this.width = 21;
        this.height = 19;
        this.size = 1;
        this.position = new Position(450, 360);
        this.hit = false;
    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("TwinBall.png", this.position.x, this.position.y, this.size, this.rotation);
    }
}