package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.graphics.staticobject.Brick;
import de.thdeg.amuri.krakout.movement.Position;

/**
 * On contact with {@link Brick}, will damage them
 */
public class Egg extends AlienObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Egg(GameView gameView) {
        super(gameView);
        this.live = 1;
        this.width = 20;
        this.height = 31;
        this.size = 1.5;
        this.position = new Position(250, 200);
        this.hit = false;
    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("Egg.png", this.position.x, this.position.y, this.size, this.rotation);
    }
}