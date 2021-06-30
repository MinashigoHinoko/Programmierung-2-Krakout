package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.graphics.basicobject.GameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.LiveObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidingGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Bat;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.moving.alien.BeeHive;
import de.thdeg.amuri.krakout.graphics.moving.alien.Face;
import de.thdeg.amuri.krakout.graphics.staticobject.*;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Manager of all Game Object Visuals
 */
class GameObjectManager {
    private final Bat bat;
    private final Background background;
    private final GameBorderTop gameBorderTop;
    private final GameBorderBottom gameBorderBottom;
    private final GameBorderLeft gameBorderLeft;
    private final GameBorderRight gameBorderRight;
    private final Overlay overlay;

    private final LinkedList<GameObject> gameObjects;
    private final LinkedList<AlienObject> alienObjects;
    private final LinkedList<LiveObject> liveObjects;
    private final LinkedList<Pinball> balls;
    private final LinkedList<BeeHive> beeHives;
    private final LinkedList<Face> faces;

    private final LinkedList<PlayerLive> playerLives;
    private final LinkedList<Brick> bricks;
    private final LinkedList<Score> score;
    private final ArrayList<CollidableGameObject> collidableGameObjects;
    private final ArrayList<CollidingGameObject> collidingGameObjects;

    protected GameObjectManager(GameView gameView) {
        this.bat = new Bat(gameView);
        this.background = new Background(gameView);
        this.gameBorderTop = new GameBorderTop(gameView);
        this.gameBorderBottom = new GameBorderBottom(gameView);
        this.gameBorderLeft = new GameBorderLeft(gameView);
        this.gameBorderRight = new GameBorderRight(gameView);
        this.overlay = new Overlay(gameView);

        this.gameObjects = new LinkedList<>();
        this.alienObjects = new LinkedList<>();
        this.liveObjects = new LinkedList<>();
        this.balls = new LinkedList<>();
        this.beeHives = new LinkedList<>();
        this.faces = new LinkedList<>();
        this.playerLives = new LinkedList<>();
        this.bricks = new LinkedList<>();
        this.score = new LinkedList<>();
        this.collidableGameObjects = new ArrayList<>();
        this.collidingGameObjects = new ArrayList<>();
    }

    protected Bat getBat() {
        return bat;
    }

    protected void moveWorld(double adaptX, double adaptY) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.getClass() != Face.class
                    && gameObject.getClass() != Bat.class
                    && gameObject.getClass() != Brick.class
                    && gameObject.getClass() != Background.class
                    && gameObject.getClass() != Pinball.class
                    && gameObject.getClass() != Overlay.class
                    && gameObject.getClass() != Score.class
                    && gameObject.getClass() != GameBorderTop.class
                    && gameObject.getClass() != GameBorderBottom.class
                    && gameObject.getClass() != GameBorderLeft.class
                    && gameObject.getClass() != GameBorderRight.class
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

        this.alienObjects.addAll(this.beeHives);
        this.alienObjects.addAll(this.faces);

        this.liveObjects.addAll(this.playerLives);

        this.collidingGameObjects.addAll(this.balls);

        this.collidableGameObjects.add(this.gameBorderTop);
        this.collidableGameObjects.add(this.gameBorderBottom);
        this.collidableGameObjects.add(this.gameBorderLeft);
        this.collidableGameObjects.add(this.gameBorderRight);
        this.collidableGameObjects.addAll(this.collidingGameObjects);
        this.collidableGameObjects.addAll(this.alienObjects);
        this.collidableGameObjects.add(this.bat);
        this.collidableGameObjects.addAll(this.bricks);

        this.gameObjects.add(this.background);
        this.gameObjects.add(this.bat);

        this.gameObjects.addAll(this.alienObjects);
        this.gameObjects.addAll(this.score);
        this.gameObjects.addAll(this.liveObjects);
        this.gameObjects.addAll(this.bricks);
        this.gameObjects.addAll(this.balls);
        this.gameObjects.add(this.gameBorderTop);
        this.gameObjects.add(this.gameBorderBottom);
        this.gameObjects.add(this.gameBorderLeft);
        this.gameObjects.add(this.gameBorderRight);
        this.gameObjects.add(this.overlay);
        //Printing to Screen
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
            gameObject.addToCanvas();
            gameObject.addHitboxToCanvas();
        }
    }


    /**
     * @return LinkedList getter for {@link Pinball}
     */
    public LinkedList<Pinball> getBalls() {
        return balls;
    }

    /**
     * @return LinkedList getter for {@link Face}
     */
    public LinkedList<Face> getFaces() {
        return faces;
    }

    /**
     * @return ArrayList getter for {@link CollidableGameObject}
     */
    public ArrayList<CollidableGameObject> getCollidableGameObjects() {
        return collidableGameObjects;
    }

    /**
     * @return LinkedList getter for {@link PlayerLive}
     */
    public LinkedList<PlayerLive> getPlayerLives() {
        return playerLives;
    }

    public LinkedList<BeeHive> getBeeHives() {
        return beeHives;
    }

    /**
     * @return LinkedList getter for {@link Overlay}
     */
    public Overlay getOverlay() {
        return overlay;
    }

    /**
     * @return LinkedList getter for {@link Brick}
     */
    public LinkedList<Brick> getBricks() {
        return bricks;
    }

    /**
     * @return LinkedList getter for {@link Score}
     */
    public LinkedList<Score> getScore() {
        return score;
    }
}
