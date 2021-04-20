package de.thdeg.amuri.krakout.graphics;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.movement.Position;

import java.awt.*;

public class Player extends GameObject {
    private static String PLAYEROBJECT = "X";
    public final boolean diagonalMovement;
    private final boolean playerGraphic;
    private int oldlive;
    private int live;
    private boolean bounceBall;
    private boolean hasPowerUp;
    private boolean shooting;


    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Player(GameView gameView) {
        super(gameView);
        this.oldlive = 3;
        this.live = oldlive;
        this.width = 50;
        this.height = 50;
        this.size = 50;
        this.bounceBall = false;
        this.hasPowerUp = false;
        this.speedInPixel = 3.5;
        this.playerGraphic = true;
        this.diagonalMovement = true;
        this.position = new Position(GameView.WIDTH / 2, GameView.HEIGHT / 2);
    }

    public void left() {
        this.position.left(speedInPixel);
    }

    public void right() {
        this.position.right(speedInPixel);
    }

    public void up() {
        this.position.up(speedInPixel);
    }

    public void down() {
        this.position.down(speedInPixel);
    }

    public void shoot() {
        shooting = true;
    }

    @Override
    public void updatePosition() {
    }

    @Override
    public void addToCanvas() {
        if (playerGraphic == false) {
            if (shooting) {
                this.PLAYEROBJECT = "O";
                this.gameView.addTextToCanvas(this.PLAYEROBJECT, this.position.x, this.position.y, this.size, Color.WHITE, this.rotation);
                shooting = false;
            } else {
                this.PLAYEROBJECT = "X";
                this.gameView.addTextToCanvas(this.PLAYEROBJECT, this.position.x, this.position.y, this.size, Color.WHITE, this.rotation);
            }
        } else {
            this.width = 35;
            this.height = 12;
            this.size = 2;
            this.rotation = 90;
            gameView.addImageToCanvas("Player.png", this.position.x, this.position.y, this.size, this.rotation);
        }
    }
}
