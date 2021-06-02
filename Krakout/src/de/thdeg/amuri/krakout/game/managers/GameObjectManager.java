package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.graphics.basicobject.GameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.LiveObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidingGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Bat;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.moving.alien.*;
import de.thdeg.amuri.krakout.graphics.staticobject.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Manager of all Game Object Visuals
 */
class GameObjectManager {
    private final GameView gameView;
    private final Bat bat;
    private final Background background;
    private final Score score;

    private final LinkedList<GameObject> gameObjects;
    private final LinkedList<AlienObject> alienObjects;
    private final LinkedList<LiveObject> liveObjects;
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
    private final LinkedList<FlashAttack> flashAttacks;
    private final LinkedList<TimeOut> timeOuts;
    private final LinkedList<TwinBall> twinBalls;
    private final LinkedList<PlayerLive> playerLives;
    private final LinkedList<Brick> bricks;
    private final ArrayList<CollidableGameObject> collidableGameObjects;
    private final ArrayList<CollidingGameObject> collidingGameObjects;

    protected GameObjectManager(GameView gameView) {
        this.gameView = gameView;
        this.bat = new Bat(gameView);
        this.background = new Background(gameView);
        this.score = new Score(gameView);

        this.gameObjects = new LinkedList<>();
        this.alienObjects = new LinkedList<>();
        this.liveObjects = new LinkedList<>();
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
        this.flashAttacks = new LinkedList<>();
        this.timeOuts = new LinkedList<>();
        this.twinBalls = new LinkedList<>();
        this.playerLives = new LinkedList<>();
        this.bricks = new LinkedList<>();
        this.collidableGameObjects = new ArrayList<>();
        this.collidingGameObjects = new ArrayList<>();

    }

    protected Bat getBat() {
        return bat;
    }

    protected void moveWorld(double adaptX, double adaptY) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.getClass() != Face.class
                    && gameObject.getClass() != Astronaut.class
                    && gameObject.getClass() != Bat.class
                    && gameObject.getClass() != Brick.class
                    && gameObject.getClass() != Background.class
                    && gameObject.getClass() != Pinball.class
                    && gameObject.getClass() != Bee.class
                    && gameObject.getClass() != FlashAttack.class
                    && gameObject.getClass() != Score.class
                    && gameObject.getClass() != PlayerLive.class) {

                gameObject.adaptPosition(adaptX, adaptY);
            }
        }
    }

    protected void updateGameObjects() {
        this.gameObjects.clear();
        this.alienObjects.clear();
        this.liveObjects.clear();
        this.collidableGameObjects.clear();
        this.collidingGameObjects.clear();

        this.alienObjects.addAll(this.astronauts);
        this.alienObjects.addAll(this.beeHives);
        this.alienObjects.addAll(this.bonusShips);
        this.alienObjects.addAll(this.cannibals);
        this.alienObjects.addAll(this.eggs);
        this.alienObjects.addAll(this.exits);
        this.alienObjects.addAll(this.faces);
        this.alienObjects.addAll(this.flashes);
        this.alienObjects.addAll(this.timeOuts);
        this.alienObjects.addAll(this.twinBalls);

        this.liveObjects.addAll(this.playerLives);
        this.liveObjects.addAll(this.alienObjects);

        this.collidingGameObjects.addAll(this.bees);
        this.collidingGameObjects.addAll(this.flashAttacks);
        this.collidingGameObjects.addAll(this.balls);

        this.collidableGameObjects.addAll(this.alienObjects);
        this.collidableGameObjects.addAll(this.collidingGameObjects);

        this.gameObjects.add(this.background);
        this.gameObjects.add(this.score);
        this.gameObjects.add(this.bat);

        this.gameObjects.addAll(this.liveObjects);
        this.gameObjects.addAll(this.bricks);
        this.gameObjects.addAll(this.items);
        this.gameObjects.addAll(this.balls);

        //Top red line
        gameView.addLineToCanvas(0, 50, GameView.WIDTH, 50, 5, Color.RED);
        //Bottom red line
        gameView.addLineToCanvas(0, GameView.HEIGHT - 50, GameView.WIDTH, GameView.HEIGHT - 50, 5, Color.RED);
        //Printing to Screen
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
            gameObject.addToCanvas();
            gameObject.addHitboxToCanvas();
        }
    }

    /**
     * @return LinkedList getter for {@link GameObject}
     */
    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * @return LinkedList getter for {@link AlienObject}
     */
    public LinkedList<AlienObject> getAlienObjects() {
        return alienObjects;
    }

    /**
     * @return LinkedList getter for {@link LiveObject}
     */
    public LinkedList<LiveObject> getLiveObjects() {
        return liveObjects;
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
     * @return LinkedList getter for {@link FlashAttack}
     */
    public LinkedList<FlashAttack> getFlashAttacks() {
        return flashAttacks;
    }

    /**
     * @return ArrayList getter for {@link CollidableGameObject}
     */
    public ArrayList<CollidableGameObject> getCollidableGameObjects() {
        return collidableGameObjects;
    }

    /**
     * @return ArrayList getter for {@link CollidingGameObject}
     */
    public ArrayList<CollidingGameObject> getCollidingGameObjects() {
        return collidingGameObjects;
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
