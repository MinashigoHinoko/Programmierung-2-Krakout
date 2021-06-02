package de.thdeg.amuri.krakout.graphics.staticobject;


import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.LiveObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.movement.Position;

import java.util.LinkedList;

/**
 * This is the Brick the player has to hit with his {@link Pinball}.
 * Every Brick has to be destroyed to finish the game
 */
public class Brick extends LiveObject {
    //Ammount of Bricks for Win condition
    private final int ammount;
    private final String color;
    //Has PowerUP?
    private boolean powerUp;
    LinkedList<Brick> bricks;

    /**
     * Creates a new Brick
     *
     * @param gameView this is for Initialising the Brick
     * @see GameView
     */
    public Brick(GameView gameView) {
        super(gameView);
        this.hit = false;
        this.position = new Position(860, 100);
        this.live = 2;
        this.ammount = 1;
        this.size = 2;
        this.color = "RED";
        this.powerUp = false;
        this.rotation = 90;
        this.width = 25;
        this.height = 12;
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

    /**
     * Determines if the Brick has a PowerUp Stored
     *
     * @param haspowerUp if true, has PowerUp
     */
    public void setHasPowerUp(boolean haspowerUp) {
        this.powerUp = haspowerUp;
    }

    /**
     * If being Hit, the Live of the Brick will be reduced according to the Damage
     *
     * @return if being Hit, true = Damage
     */
    public boolean isHit() {
        return this.hit;
    }

    /**
     * Get Ammount of Bricks to determine how many have to be destroyed to finish game
     *
     * @return Number of Bricks
     */
    public int getAmmount() {
        return this.ammount;
    }

    /**
     * Get the PowerUp Status to determine if it should drop a PowerUp or not
     *
     * @return if true, has PowerUp
     */
    private boolean hasPowerUp() {
        return this.powerUp;
    }

    private void destroy() {
    }

    @Override
    public void addToCanvas() {
        switch (this.color) {
            case "RED":
                this.gameView.addImageToCanvas("RED.png", this.position.x, this.position.y, this.size, this.rotation);
                break;
        }
    }

    @Override
    public void updateStatus() {

    }
}
