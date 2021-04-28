package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.movement.Position;


/**
 * This is an Enemy Face that will give you Bonus points and destroys itself if living too long.
 * It moves into random directions
 *
 * @see Position
 */
public class Face extends AlienObject {

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Face(GameView gameView) {
        super(gameView);
        this.live = 2;
        this.width = 23;
        this.height = 23;
        this.size = 1.5;
        this.speedInPixel = 3.5;
        this.position = new Position(860, 160);
        this.hit = false;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("Face.png", this.position.x, this.position.y, this.size, this.rotation);
    }
}
