package de.thdeg.amuri.krakout.graphics.staticobject;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;

/**
 * Game Border on the Right(for bouncing)
 */
public class GameBorderRight extends CollidableGameObject {
    /**
     * @param gameView Visual of the Border
     */
    public GameBorderRight(GameView gameView) {
        super(gameView);
        this.position.x = GameView.WIDTH - 6;
        this.position.y = 56;
        this.size = 1;
        this.width = 5;
        this.height = GameView.HEIGHT - 56;
        this.hitBox.width = (int) (this.width * this.size);
        this.hitBox.height = (int) (this.height * this.size);
    }

    @Override
    public void addToCanvas() {
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
