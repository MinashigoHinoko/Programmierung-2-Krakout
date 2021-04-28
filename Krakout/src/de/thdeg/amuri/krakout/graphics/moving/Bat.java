package de.thdeg.amuri.krakout.graphics.moving;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.LiveObject;
import de.thdeg.amuri.krakout.graphics.staticobject.Brick;
import de.thdeg.amuri.krakout.movement.Position;

import java.awt.*;

/**
 * This is the Player Figure, that the Player controls. He uses it to manipulate the {@link Pinball} to break {@link Brick}
 */
public class Bat extends LiveObject {
    private final boolean playerGraphic;
    private final boolean bounceBall;
    private final boolean hasPowerUp;
    private String playerObject;
    private boolean shooting;


    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Bat(GameView gameView) {
        super(gameView);
        this.live = oldLive;
        this.width = 50;
        this.height = 50;
        this.size = 50;
        this.bounceBall = false;
        this.hasPowerUp = false;
        this.speedInPixel = 3.5;
        this.playerGraphic = false;
        this.position = new Position(GameView.WIDTH / 2, GameView.HEIGHT / 2);
    }

    /**
     * interacts with {@link Position} to move left when called
     */
    public void left() {
        this.position.left(speedInPixel);
    }

    /**
     * interacts with {@link Position} to move right when called
     */
    public void right() {
        this.position.right(speedInPixel);
    }

    /**
     * interacts with {@link Position} to move up when called
     */
    public void up() {
        this.position.up(speedInPixel);
    }

    /**
     * interacts with {@link Position} to move down when called
     */
    public void down() {
        this.position.down(speedInPixel);
    }

    /**
     * Manipulates the Output of the playerObject for the task
     */
    public void shoot() {
        this.shooting = true;
    }

    @Override
    public void updatePosition() {
    }

    @Override
    public void addToCanvas() {
        if (playerGraphic == false) {
            if (this.shooting) {
                this.playerObject = "O";
                this.shooting = false;
            } else {
                this.playerObject = "X";
            }
            this.gameView.addTextToCanvas(this.playerObject, this.position.x, this.position.y, this.size, Color.WHITE, this.rotation);
        } else {
            this.size = 1.5;
            this.width = 35;
            this.height = 12;
            this.rotation = 90;
            gameView.addImageToCanvas("Player.png", this.position.x, this.position.y, this.size, this.rotation);
        }
    }
}
