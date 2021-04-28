package de.thdeg.amuri.krakout.graphics.staticobject;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.LiveObject;
import de.thdeg.amuri.krakout.graphics.moving.alien.Astronaut;
import de.thdeg.amuri.krakout.movement.Position;


/**
 * Visual of the remaining Live of the player. he controls the {@link de.thdeg.amuri.krakout.graphics.moving.Bat} */
public class PlayerLive extends LiveObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public PlayerLive(GameView gameView) {
        super(gameView);
        this.position = new Position(30,0);
        this.size = 3;
    }

    @Override
    public void hasHit() {
        this.hit = true;
    }

    @Override
    public void updatePosition() {
        if (hit){
            this.live= live +1;
        }
    }

    @Override
    public void addToCanvas() {
        position.x = 30;
        for (int x = 0; x < this.oldLive; x++) {
            this.gameView.addImageToCanvas("Herzrahmen.png", this.position.x, this.position.y, this.size, this.rotation);
            position.x += 40;
        }
        position.x = 30;
            for (int x = 0; x < this.live; x++) {
                this.gameView.addImageToCanvas("Leben.png", this.position.x, this.position.y, this.size, this.rotation);
                position.x += 40;
            }

    }

}
