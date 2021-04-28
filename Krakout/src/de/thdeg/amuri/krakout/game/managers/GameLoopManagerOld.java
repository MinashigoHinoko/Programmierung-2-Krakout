package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.moving.Bat;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.moving.alien.Face;
import de.thdeg.amuri.krakout.graphics.staticobject.Background;
import de.thdeg.amuri.krakout.graphics.staticobject.Brick;
import de.thdeg.amuri.krakout.graphics.staticobject.Item;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * This is the GameLoopManager, here we will Input what the player sees. This includes animations and the game itself
 */
class GameLoopManagerOld {
    private final GameView gameView;
    private final Pinball ball;
    private final Background background;
    private final Brick brick;
    private final Item item;
    private final Bat bat;
    private final Face face;
    private final boolean diagonalMovement;
    private boolean shootOnce;

    /**
     * Creates the main loop
     */
    protected GameLoopManagerOld() {
        this.gameView = new GameView();
        this.gameView.setWindowTitle("Krakout");
        this.gameView.setStatusText("Amir(mHiko), Amuri - Java Programmierung SS 2021");
        this.gameView.setWindowIcon("Target.png");
        this.brick = new Brick(gameView);
        this.ball = new Pinball(gameView);
        this.background = new Background(gameView);
        this.item = new Item(gameView);
        this.item.setPosition(this.brick.getPosition());
        this.item.setSpeedInPixel(ball.getSpeedInPixel());
        this.bat = new Bat(gameView);
        this.face = new Face(gameView);
        this.diagonalMovement = true;
        this.ball.setBouncePosition(bat.getPosition());
        this.ball.setBounceBrickPosition(brick.getPosition());
        this.shootOnce = true;

    }

    void updateUserInputs() {
        Integer[] gedruekteTasten = gameView.getKeyCodesOfCurrentlyPressedKeys();
        for (int keyCode : gedruekteTasten) {
            if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
                bat.up();
            }
            if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
                bat.down();
            }
            if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
                bat.left();
            }
            if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
                bat.right();
            }
            if (keyCode == KeyEvent.VK_SPACE) {
                if (!shootOnce) {
                    bat.shoot();
                    shootOnce = true;
                }
            }
            if (!diagonalMovement) {
                break;
            }
        }
        shootOnce = false;
    }


    /**
     * Initialisation of the game and Building Game screen {@link GameView}
     */
    public void startGame() {

        while (true) { // The "Game-Loop"
            //Controlls of the Game
            updateUserInputs();
            //Update Health Up
            item.updatePosition();
            //Update Position ball
            ball.updatePosition();
            //Update Position Face
            face.updatePosition();
            //background.updatePosition();
            //Print Background
            background.addToCanvas();
            //Print Item
            item.addToCanvas();
            //Print Ball
            ball.addToCanvas();
            //Print Brick
            brick.addToCanvas();
            //Print Player
            bat.addToCanvas();
            //Print Face
            face.addToCanvas();
            //Score Top Right
            gameView.addTextToCanvas("100", GameView.WIDTH - 3 * 18, 0, 18, Color.WHITE, 0);
            //Live Border 1
            gameView.addImageToCanvas("Herzrahmen.png", 30, 0, 3, 0);
            //Remaining Live 1
            gameView.addImageToCanvas("Leben.png", 30, 0, 3, 0);
            //Live Border 2
            gameView.addImageToCanvas("Herzrahmen.png", 55, 0, 3, 0);
            //Remaining Live 2
            gameView.addImageToCanvas("Leben.png", 55, 0, 3, 0);
            //Live Border 3
            gameView.addImageToCanvas("Herzrahmen.png", 80, 0, 3, 0);
            //Remaining Live 3
            //gameView.addImageToCanvas("Leben.png", 80, 0, 3, 0);
            //Top red line
            gameView.addLineToCanvas(0, 50, GameView.WIDTH, 50, 5, Color.RED);
            //Bottom red line
            gameView.addLineToCanvas(0, GameView.HEIGHT - 50, GameView.WIDTH, GameView.HEIGHT - 50, 5, Color.RED);
            //Printing to Screen
            gameView.printCanvas();
        }

    }
}
