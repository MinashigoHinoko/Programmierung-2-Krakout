package de.thdeg.amuri.krakout.graphics.staticobject;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;

import java.awt.*;

/**
 * Game Border on the Top(for bouncing)
 */
public class GameBorderTop extends CollidableGameObject {

    /**
     * @param gameView Visual of the Border
     */
    public GameBorderTop(GameView gameView) {
        super(gameView);
        this.position.x = 0;
        this.position.y = 50;
        this.size = 5;
        this.width = GameView.WIDTH;
        this.height = 1;
        this.hitBox.width = (int) (this.width * this.size);
        this.hitBox.height = (int) (this.height * this.size);
    }

    @Override
    public void addToCanvas() {
        gameView.addRectangleToCanvas(this.position.x, this.position.y, this.width, this.height, this.size, true, Color.RED);
    }

    @Override
    protected void updateStatus() {

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
