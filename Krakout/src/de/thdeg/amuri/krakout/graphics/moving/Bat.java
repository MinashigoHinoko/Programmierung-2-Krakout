package de.thdeg.amuri.krakout.graphics.moving;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.LiveObject;
import de.thdeg.amuri.krakout.graphics.staticobject.Brick;
import de.thdeg.amuri.krakout.movement.Position;

/**
 * This is the Player Figure, that the Player controls. He uses it to manipulate the {@link Pinball} to break {@link Brick}
 */
public class Bat extends LiveObject {
    private final boolean playerGraphic;
    private final boolean bounceBall;
    private final boolean hasPowerUp;
    private boolean shooting;


    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Bat(GameView gameView) {
        super(gameView);
        this.live = totalLive;
        this.size = 1.5;
        this.width = 12;
        this.height = 33;
        this.rotation = 0;
        this.bounceBall = false;
        this.hasPowerUp = false;
        this.speedInPixel = 3.5;
        this.playerGraphic = true;
        this.position = new Position(20, 280);
    }

    /**
     * interacts with {@link Position} to move left when called
     */
    public void left() {
        if (this.position.x > 20) {
            this.position.left(this.speedInPixel);
        } else {
            this.gamePlayManager.batMovingLeft(speedInPixel);
        }
    }

    /**
     * interacts with {@link Position} to move right when called
     */
    public void right() {
        if (this.position.x + this.width * this.size <= 20) {
            this.position.right(this.speedInPixel);
        } else {
            this.gamePlayManager.batMovingRight(this.speedInPixel);
        }
    }

    /**
     * interacts with {@link Position} to move up when called
     */
    public void up() {
        if (this.position.y  >= 53 ) {
            this.position.up(this.speedInPixel);
        } else {
            this.gamePlayManager.batMovingUp(this.speedInPixel);
        }
    }

    /**
     * interacts with {@link Position} to move down when called
     */
    public void down() {
        if (this.position.y + this.height * this.size <= this.gameView.HEIGHT - 51.5) {
            this.position.down(this.speedInPixel);
        } else {
            this.gamePlayManager.batMovingDown(this.speedInPixel);
        }
    }

    /**
     * Manipulates the Output of the playerObject for the task
     */
    public void shoot() {
        this.shooting = true;
    }

    @Override
    public void addToCanvas() {
        if (this.shooting) {
            gamePlayManager.shootPinball(this.position);
            this.shooting = false;
        }
        this.gameView.addImageToCanvas("Player.png", this.position.x, this.position.y, this.size, this.rotation);
    }

    @Override
    public void updateStatus() {

    }
}
