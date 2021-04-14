package de.thdeg.amuri.krakout.gameview;

import de.thdeg.amuri.krakout.nonplayerobject.Background;
import de.thdeg.amuri.krakout.nonplayerobject.Brick;
import de.thdeg.amuri.krakout.nonplayerobject.Item;
import de.thdeg.amuri.krakout.nonplayerobject.Pinball;

import java.awt.*;

/**
 * This is the GameLoopManager, here we will Input what the player sees. This includes animations and the game itself
 */
public class GameLoopManager {
    private final GameView gameView;
    private final Pinball ball;
    private final Background background;
    private final Brick brick;
    private final Item item;

    /**
     * Creates the main loop
     */
    public GameLoopManager() {
        this.gameView = new GameView();
        this.gameView.setWindowTitle("Krakout");
        this.gameView.setStatusText("Amir(mHiko), Amuri - Java Programmierung SS 2021");
        this.gameView.setWindowIcon("Target.png");
        this.ball = new Pinball(gameView);
        this.background = new Background(gameView);
        this.brick = new Brick(gameView);
        this.item = new Item(gameView);
        this.item.setPosition(brick.getPosition());
        this.item.setFallSpeedInPixel(ball.getSpeedInPixel());

    }

    /**
     * Initialisation of the game and Building Game screen {@link GameView}
     */
    public void startGame() {

        while (true) { // Der "Game-Loop"
            //Update Position ball
            ball.updatePosition();
            //Update Background();
            background.updatePosition();
            //Print Background
            background.addToCanvas();
            //Print Item
            item.addToCanvas();
            //Print ball
            ball.addToCanvas();
            //Print Brick
            brick.addToCanvas();
            //Print Player
            gameView.addImageToCanvas("Player.png", -20, 100, 2, 90);
            //Placeholder
            gameView.addTextToCanvas("Placeholder", GameView.WIDTH - 11 * 18, 0, 18, Color.YELLOW, 0);
            //Placeholder
            gameView.addTextToCanvas("Placeholder", GameView.WIDTH - 11 * 18, GameView.HEIGHT - 18, 18, Color.YELLOW, 0);
            //Top red line
            gameView.addLineToCanvas(0, 50, GameView.WIDTH, 50, 5, Color.RED);
            //Bottom red line
            gameView.addLineToCanvas(0, GameView.HEIGHT - 50, GameView.WIDTH, GameView.HEIGHT - 50, 5, Color.RED);
            //Printing to Screen
            gameView.printCanvas();
        }

    }
}
