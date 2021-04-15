package de.thdeg.amuri.krakout.graphics;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.movement.Position;

import java.awt.*;

public class Player extends GameObject {
    private static String PLAYEROBJECT="X";
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
        this.width = 1;
        this.height = 1;
        this.size = 50;
        this.bounceBall = false;
        this.hasPowerUp = false;
        this.speedInPixel = 3.5;
        this.position = new Position(GameView.WIDTH/2,GameView.HEIGHT/2);
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
    public void hasShot(boolean shooting) {
        this.shooting = shooting;
    }
    public void shoot() {
        if (shooting==false) {
            this.PLAYEROBJECT = "X";
        }else{
            this.PLAYEROBJECT = "O";
        }
    }

    @Override
    public void updatePosition() {
    }

    @Override
    public void addToCanvas() {
        this.gameView.addTextToCanvas(this.PLAYEROBJECT,this.position.x,this.position.y,this.size, Color.WHITE,this.rotation);
        //gameView.addImageToCanvas("Player.png", -20, 100, 2, 90);
    }
}
