package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;

/**
 * SKIP Level on collision
 */
public class Exit extends AlienObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Exit(GameView gameView) {
        super(gameView);
        this.live = 1;
        this.width = 19;
        this.height = 19;
        this.size = 1.5;
        this.hit = false;
    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("Exit.png", this.position.x, this.position.y, this.size, this.rotation);
    }
}