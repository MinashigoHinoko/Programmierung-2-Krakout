package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.graphics.basicobject.MovingGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Bat;
import de.thdeg.amuri.krakout.graphics.staticobject.Item;

/**
 * When collides with {@link Bat}, will give one of following: 10.000 Points, Shield, Transmutating Bricks (which will possibly give them {@link Item}) or Bomb
 */
public class BonusShip extends AlienObject implements MovingGameObject {
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public BonusShip(GameView gameView) {
        super(gameView);
        this.live = 1;
        this.width = 42;
        this.height = 33;
        this.size = 1.5;
        this.hit = false;
    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("BonusShip.png", this.position.x, this.position.y, this.size, this.rotation);
    }

    @Override
    public void updateStatus() {

    }
}