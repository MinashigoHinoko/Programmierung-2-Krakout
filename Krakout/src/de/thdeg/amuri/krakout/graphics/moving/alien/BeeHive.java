package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.movement.Position;

import java.util.LinkedList;

/**
 * releases {@link Bee}
 */
public class BeeHive extends AlienObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public BeeHive(GameView gameView) {
        super(gameView);
        this.live = 1;
        this.width = 35;
        this.height = 17;
        this.size = 1.5;
        this.position = new Position(550, 200);
        this.hit = false;
    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("BeeHive.png", this.position.x, this.position.y, this.size, this.rotation);
    }
}
