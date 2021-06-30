package de.thdeg.amuri.krakout.graphics.staticobject;


import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.movement.Position;

/**
 * This is the Brick the player has to hit with his {@link Pinball}.
 * Every Brick has to be destroyed to finish the game
 */
public class Brick extends CollidableGameObject {

    /**
     * Creates a new Brick
     *
     * @param gameView this is for Initialising the Brick
     * @see GameView
     */
    public Brick(GameView gameView) {
        super(gameView);
        this.position = new Position(860, 100);
        this.size = 2;
        this.rotation = 0;
        this.width = 12;
        this.height = 25;
        this.hitBox.width = (int) (this.width * this.size);
        this.hitBox.height = (int) (this.height * this.size);
    }

    @Override
    protected void updateHitBoxPosition() {
        this.hitBox.x = (int) this.position.x;
        this.hitBox.y = (int) this.position.y;
    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        if (otherObject.getClass() == Pinball.class) {
            this.gamePlayManager.destroy(this);
        }
    }

    /**
     * This is for generating new Bricks as Map Generation.
     *
     * @param position as  Position of the Brick
     */
    public void setPosition(Position position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("RED.png", this.position.x, this.position.y, this.size, this.rotation);
    }

    @Override
    public void updateStatus() {

    }
}
