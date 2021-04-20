package de.thdeg.amuri.krakout.graphics;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.movement.Position;


public class Face extends GameObject {
    private int live;
    private boolean isHit;

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Face(GameView gameView) {
        super(gameView);
        this.live = 2;
        this.width = 1;
        this.height = 1;
        this.size = 2;
        this.speedInPixel = 3.5;
        this.position = new Position(860, 160);
        this.isHit = false;
    }

    @Override
    public void updatePosition() {
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("Face.png", this.position.x, this.position.y, this.size, this.rotation);
    }
}
