package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.LiveObject;
import de.thdeg.amuri.krakout.graphics.moving.alien.Face;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.moving.Bat;
import de.thdeg.amuri.krakout.graphics.staticobject.Background;
import de.thdeg.amuri.krakout.graphics.staticobject.Brick;
import de.thdeg.amuri.krakout.graphics.staticobject.Item;
import de.thdeg.amuri.krakout.graphics.staticobject.PlayerLive;

import java.awt.*;

/**
 * Manager of all Game Objects
 */
public class GameObjectManager {
    private final Pinball ball;
    private final Background background;
    private final Brick brick;
    private final Item item;
    private final Bat bat;
    private final Face face;
    private final PlayerLive playerLive;
    private final GameView gameView;

    protected GameObjectManager(GameView gameView) {
        this.gameView = gameView;
        this.brick = new Brick(gameView);
        this.ball = new Pinball(gameView);
        this.background = new Background(gameView);
        this.item = new Item(gameView);
        this.item.setPosition(this.brick.getPosition());
        this.item.setSpeedInPixel(ball.getSpeedInPixel());
        this.bat = new Bat(gameView);
        this.face = new Face(gameView);
        this.ball.setBouncePosition(bat.getPosition());
        this.ball.setBounceBrickPosition(brick.getPosition());
        this.playerLive = new PlayerLive(gameView);
    }

    protected Bat getBat() {
        return bat;
    }

    protected void updateGameObjects() {
        //Update Health Up
        item.updatePosition();
        //Update Position ball
        ball.updatePosition();
        //Update Position Face
        face.updatePosition();
        //Update Live
        playerLive.updatePosition();
        //background.updatePosition();
        //Print Background
        background.addToCanvas();
        //Print Item
        item.addToCanvas();
        //Print Ball
        ball.addToCanvas();
        //Print Brick
        brick.addToCanvas();
        //Print Bat
        bat.addToCanvas();
        //Print Face
        face.addToCanvas();
        //Print Liveborder
        playerLive.addToCanvas();
        //Score Top Right
        gameView.addTextToCanvas("100", GameView.WIDTH - 3 * 18, 0, 18, Color.WHITE, 0);
        //Top red line
        gameView.addLineToCanvas(0, 50, GameView.WIDTH, 50, 5, Color.RED);
        //Bottom red line
        gameView.addLineToCanvas(0, GameView.HEIGHT - 50, GameView.WIDTH, GameView.HEIGHT - 50, 5, Color.RED);
        //Printing to Screen
    }
}
