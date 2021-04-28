package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.moving.alien.*;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.moving.Bat;
import de.thdeg.amuri.krakout.graphics.staticobject.*;

import java.awt.*;

/**
 * Manager of all Game Objects
 */
 class GameObjectManager {
    private final Pinball ball;
    private final Background background;
    private final Brick brick;
    private final Item item;
    private final Bat bat;
    private final Face face;
    private final PlayerLive playerLive;
    private final GameView gameView;
    private final Astronaut astronaut;
    private final Bee bee;
    private final BeeHive beeHive;
    private final BonusShip bonusShip;
    private final Cannibal cannibal;
    private final Egg egg;
    private final Exit exit;
    private final Flash flash;
    private final TimeOut timeOut;
    private final TwinBall twinBall;
    private final Score score;

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
        this.astronaut = new Astronaut(gameView);
        this.bee = new Bee(gameView);
        this.beeHive = new BeeHive(gameView);
        this.bonusShip = new BonusShip(gameView);
        this.cannibal = new Cannibal(gameView);
        this.egg = new Egg(gameView);
        this.exit = new Exit(gameView);
        this.flash = new Flash(gameView);
        this.timeOut = new TimeOut(gameView);
        this.twinBall = new TwinBall(gameView);
        this.score = new Score(gameView);
    }

    protected Bat getBat() {
        return bat;
    }

    protected void updateGameObjects() {
        //Update Health Up
        item.updatePosition();
        //Update Position ball
        ball.updatePosition();
        //Update Astronaut
        astronaut.updatePosition();
        //Update Bee
        bee.updatePosition();
        //Update BeeHive
        beeHive.updatePosition();
        //Update BonusShip
        bonusShip.updatePosition();
        //Update Cannibal
        cannibal.updatePosition();
        //Update Egg
        egg.updatePosition();
        //Update Exit
        exit.updatePosition();
        //Update Position Face
        face.updatePosition();
        //Update Position Flash
        flash.updatePosition();
        //Update Position TimeOut
        timeOut.updatePosition();
        //Update Position TwinBall
        twinBall.updatePosition();
        //Update Live
        playerLive.updatePosition();
        //Update Score
        score.updatePosition();
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
        //Print Astronaut
        astronaut.addToCanvas();
        //print Bee
        bee.addToCanvas();
        //print BeeHive
        beeHive.addToCanvas();
        //print BonusShip
        bonusShip.addToCanvas();
        //print Cannibal
        cannibal.addToCanvas();
        //print Egg
        egg.addToCanvas();
        //print Exit
        exit.addToCanvas();
        //Print Face
        face.addToCanvas();
        //Print Flash
        flash.addToCanvas();
        //Print TimeOut
        timeOut.addToCanvas();
        //Print TwinBall
        twinBall.addToCanvas();
        //Print Live
        playerLive.addToCanvas();
        //Print Score
        score.addToCanvas();
        //Top red line
        gameView.addLineToCanvas(0, 50, GameView.WIDTH, 50, 5, Color.RED);
        //Bottom red line
        gameView.addLineToCanvas(0, GameView.HEIGHT - 50, GameView.WIDTH, GameView.HEIGHT - 50, 5, Color.RED);
        //Printing to Screen
    }
}
