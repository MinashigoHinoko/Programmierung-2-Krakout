package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.GameObject;
import de.thdeg.amuri.krakout.graphics.moving.Bat;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.moving.alien.*;
import de.thdeg.amuri.krakout.graphics.staticobject.*;

import java.awt.*;
import java.util.LinkedList;

/**
 * Manager of all Game Object Visuals
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

    private final LinkedList<GameObject> gameObjects;
    private final LinkedList<Item> items;
    private final LinkedList<Pinball> balls;
    private final LinkedList<Astronaut> astronauts;
    private final LinkedList<Bee> bees;
    private final LinkedList<BeeHive> beeHives;
    private final LinkedList<BonusShip> bonusShips;
    private final LinkedList<Cannibal> cannibals;
    private final LinkedList<Egg> eggs;
    private final LinkedList<Exit> exits;
    private final LinkedList<Face> faces;
    private final LinkedList<Flash> flashes;
    private final LinkedList<TimeOut> timeOuts;
    private final LinkedList<TwinBall> twinBalls;
    private final LinkedList<PlayerLive> playerLives;
    private final LinkedList<Brick> bricks;

    protected GameObjectManager(GameView gameView) {
        this.gameObjects = new LinkedList<>();
        this.items = new LinkedList<>();
        this.balls = new LinkedList<>();
        this.astronauts = new LinkedList<>();
        this.bees = new LinkedList<>();
        this.beeHives = new LinkedList<>();
        this.bonusShips = new LinkedList<>();
        this.cannibals = new LinkedList<>();
        this.eggs = new LinkedList<>();
        this.exits = new LinkedList<>();
        this.faces = new LinkedList<>();
        this.flashes = new LinkedList<>();
        this.timeOuts = new LinkedList<>();
        this.twinBalls = new LinkedList<>();
        this.playerLives = new LinkedList<>();
        this.bricks = new LinkedList<>();

        this.gameView = gameView;
        this.brick = new Brick(gameView);
        this.ball = new Pinball(gameView);
        this.background = new Background(gameView);
        this.item = new Item(gameView);
        this.item.setPosition(this.brick.getPosition());
        this.item.setSpeedInPixel(this.ball.getSpeedInPixel());
        this.bat = new Bat(gameView);
        this.face = new Face(gameView);
        this.ball.setBouncePosition(this.bat.getPosition());
        this.ball.setBounceBrickPosition(this.brick.getPosition());
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

        this.items.add(this.item);
        this.balls.add(this.ball);
        this.astronauts.add(this.astronaut);
        this.bees.add(this.bee);
        this.beeHives.add(this.beeHive);
        this.bonusShips.add(this.bonusShip);
        this.cannibals.add(this.cannibal);
        this.eggs.add(this.egg);
        this.exits.add(this.exit);
        this.faces.add(this.face);
        this.flashes.add(this.flash);
        this.timeOuts.add(this.timeOut);
        this.twinBalls.add(this.twinBall);
        this.playerLives.add(this.playerLive);
        this.bricks.add(this.brick);
    }

    protected Bat getBat() {
        return bat;
    }

    protected void updateGameObjects() {
        this.gameObjects.clear();
        this.gameObjects.add(background);
        this.gameObjects.addAll(this.bricks);
        this.gameObjects.addAll(this.items);
        this.gameObjects.addAll(this.balls);
        this.gameObjects.addAll(this.astronauts);
        this.gameObjects.addAll(this.bees);
        this.gameObjects.addAll(this.beeHives);
        this.gameObjects.addAll(this.bonusShips);
        this.gameObjects.addAll(this.cannibals);
        this.gameObjects.addAll(this.eggs);
        this.gameObjects.addAll(this.exits);
        this.gameObjects.addAll(this.faces);
        this.gameObjects.addAll(this.flashes);
        this.gameObjects.addAll(this.timeOuts);
        this.gameObjects.addAll(this.twinBalls);
        this.gameObjects.addAll(this.playerLives);
        this.gameObjects.add(score);
        this.gameObjects.add(bat);
        this.gameObjects.add(score);

        //Top red line
        gameView.addLineToCanvas(0, 50, GameView.WIDTH, 50, 5, Color.RED);
        //Bottom red line
        gameView.addLineToCanvas(0, GameView.HEIGHT - 50, GameView.WIDTH, GameView.HEIGHT - 50, 5, Color.RED);
        //Printing to Screen
        for (GameObject gameObject : gameObjects) {
            gameObject.addToCanvas();
            gameObject.updatePosition();
            gameObject.updateStatus();

        }
    }

    /**
     * @return LinkedList getter for {@link GameObject}
     */
    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * @return LinkedList getter for {@link Item}
     */
    public LinkedList<Item> getItems() {
        return items;
    }

    /**
     * @return LinkedList getter for {@link Pinball}
     */
    public LinkedList<Pinball> getBalls() {
        return balls;
    }

    /**
     * @return LinkedList getter for {@link Astronaut}
     */
    public LinkedList<Astronaut> getAstronauts() {
        return astronauts;
    }

    /**
     * @return LinkedList getter for {@link Bee}
     */
    public LinkedList<Bee> getBees() {
        return bees;
    }

    /**
     * @return LinkedList for {@link BeeHive}
     */
    public LinkedList<BeeHive> getBeeHives() {
        return beeHives;
    }

    /**
     * @return LinkedList getter for {@link BonusShip}
     */
    public LinkedList<BonusShip> getBonusShips() {
        return bonusShips;
    }

    /**
     * @return LinkedList getter for {@link Cannibal}
     */
    public LinkedList<Cannibal> getCannibals() {
        return cannibals;
    }

    /**
     * @return LinkedList getter for {@link Egg}
     */
    public LinkedList<Egg> getEggs() {
        return eggs;
    }

    /**
     * @return LinkedList getter for {@link Exit}
     */
    public LinkedList<Exit> getExits() {
        return exits;
    }

    /**
     * @return LinkedList getter for {@link Face}
     */
    public LinkedList<Face> getFaces() {
        return faces;
    }

    /**
     * @return LinkedList getter for {@link Flash}
     */
    public LinkedList<Flash> getFlashes() {
        return flashes;
    }

    /**
     * @return LinkedList getter for {@link TimeOut}
     */
    public LinkedList<TimeOut> getTimeOuts() {
        return timeOuts;
    }

    /**
     * @return LinkedList getter for {@link TwinBall}
     */
    public LinkedList<TwinBall> getTwinBalls() {
        return twinBalls;
    }

    /**
     * @return LinkedList getter for {@link PlayerLive}
     */
    public LinkedList<PlayerLive> getPlayerLives() {
        return playerLives;
    }

    /**
     * @return LinkedList getter for {@link Brick}
     */
    public LinkedList<Brick> getBricks() {
        return bricks;
    }
}
