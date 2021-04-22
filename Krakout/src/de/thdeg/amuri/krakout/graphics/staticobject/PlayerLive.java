package de.thdeg.amuri.krakout.graphics.staticobject;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.LiveObject;
import de.thdeg.amuri.krakout.movement.Position;

public class PlayerLive extends LiveObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public PlayerLive(GameView gameView) {
        super(gameView);
        this.isHit = false;
        this.position = new Position(30,0);
        this.size = 3;
    }
    @Override
    public void updatePosition() {
    }

    @Override
    public void addToCanvas() {
        for(int x=0;x<this.live;x++) {
            this.gameView.addImageToCanvas("Leben.png", this.position.x, this.position.y, this.size, this.rotation);
        }
        for(int x=0;x<this.oldLive;x++) {
            this.gameView.addImageToCanvas("Herzrahmen.png", this.position.x, this.position.y, this.size, this.rotation);
        }
        }
}
