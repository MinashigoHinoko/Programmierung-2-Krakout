package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.staticobject.Brick;

/**
 * Pierces {@link Brick} on collision with {@link Pinball}
 */
public class Flash extends AlienObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Flash(GameView gameView) {
        super(gameView);
        this.live = 1;
        this.width = 51;
        this.height = 70;
        this.size = 1;
        this.hit = false;
    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("Flash.png", this.position.x, this.position.y, this.size, this.rotation);
    }
}