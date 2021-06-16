package de.thdeg.amuri.krakout.graphics.moving;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;

import java.awt.*;

/**
 * Top Hitbox of {@link Bat} for bouncing up and down
 */
public class BatTopHitbox extends Bat {
        private Bat bat;

    /**
     * @param gameView initialise visiable Hitbox
     */
    public BatTopHitbox(GameView gameView) {
        super(gameView);
        this.bat= new Bat(gameView);
        this.size = 1.5;
        this.width = (int) (6*this.size);
        this.height = (int) (1*this.size);
        this.rotation = 0;
        this.speedInPixel = 3.5;
        this.position= this.bat.getPosition();
        this.hitBox.width = (int) (this.width * this.size);
        this.hitBox.height = (int) (this.height * this.size);
    }

    @Override
    public void addToCanvas() {
        //this.gameView.addRectangleToCanvas(this.position.x, this.position.y, this.width, this.height, 1, true, Color.GREEN);
    }

    @Override
    public void updateStatus() {
    this.position = this.bat.getPosition();
    }

    @Override
    protected void updateHitBoxPosition() {
        this.hitBox.x = (int) this.position.x;
        this.hitBox.y = (int) this.position.y;
    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {

    }
}
