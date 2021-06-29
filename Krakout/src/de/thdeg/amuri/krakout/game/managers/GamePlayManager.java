package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.game.utilities.Level;
import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Bat;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.moving.alien.Astronaut;
import de.thdeg.amuri.krakout.graphics.moving.alien.Bee;
import de.thdeg.amuri.krakout.graphics.moving.alien.Face;
import de.thdeg.amuri.krakout.graphics.staticobject.*;
import de.thdeg.amuri.krakout.movement.Position;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Manager of the Game process
 */
public class GamePlayManager {
    private final GameView gameView;
    private final GameObjectManager gameObjectManager;
    private final Random random;
    private Pinball ball;
    private Level level;
    private boolean listHasBeenDeleted;
    private LinkedList<Position> brickPositions;
    private String name;
    private int numberOfBricks;
    private int numberOfItems;
    private int numberOfAliens;
    private int liveOfPLayer;
    private boolean brickSpawned;
    private Score score;
    private LevelManager levelManager;
    private PlayerLive playerLive;
    private boolean levelOver;

    protected GamePlayManager(GameView gameView, GameObjectManager gameObjectManager) {
        this.gameView = gameView;
        this.gameObjectManager = gameObjectManager;
        this.random = new Random();
        this.gameObjectManager.getBat().setGamePlayManager(this);
        this.listHasBeenDeleted = false;
        this.playerLive = new PlayerLive(this.gameView);
        this.levelManager = new LevelManager(true);
    }
    private void initializeLevel() {
        this.score = new Score(gameView);
        this.gameObjectManager.getScore().add(score);
        this.brickPositions = new LinkedList<>();
        this.name = this.levelManager.getNextLevel().name;
        this.numberOfBricks = this.levelManager.getNextLevel().numberOfBricks;
        this.numberOfItems = this.levelManager.getNextLevel().numberOfItems;
        this.numberOfAliens = this.levelManager.getNextLevel().numberOfAliens;
        this.liveOfPLayer = this.levelManager.getNextLevel().playerLive;
        for (int i = 1; i <= this.numberOfBricks; i++) {
            int x = 450;
            int y = 300;
            if (i != 0) {
                x += 23 * i;
                y += 0 * i;
            }
            brickPositions.add(new Position(x, y));
        }

        this.level = new Level(this.name, this.numberOfBricks, this.numberOfItems, this.numberOfAliens, liveOfPLayer, this.brickPositions);
        this.spawnBrick();
        this.spawnAndDestroyFace();
        this.generateHealth();
    }

    private void nextLevel() {
        if (!levelOver) {
            gameView.setTimer("level", "GamePlayManager", 3000);
            levelOver = true;
            gameObjectManager.getBat().setInvisible();
            gameObjectManager.getOverlay().showMessage("Great job!");
        }
        if (gameView.timerExpired("level", "GamePlayManager")) {
            levelOver = false;
            initializeLevel();
        }
    }
    /**
     * @param startPosition Shoots a ball, there can only be up to two at the same time.
     */
    public void shootPinball(Position startPosition) {
        boolean ballCoolDown = false;
        boolean ballCall = false;
        ArrayList<CollidableGameObject> collidableGameObjects = gameObjectManager.getCollidableGameObjects();
        if (this.gameView.timerExpired("BallCoolDown", "GamePlayManager")) {
            this.gameView.setTimer("BallCoolDown", "GamePlayManager", 300);
            ballCoolDown = !ballCoolDown;
        }
        if (ballCoolDown || this.gameObjectManager.getBalls().isEmpty()) {
            ballCall = !ballCall;
            if (ballCall && this.gameObjectManager.getBalls().size() < 2) {
                collidableGameObjects.remove(this.ball);
                this.ball = new Pinball(this.gameView, collidableGameObjects);
                this.ball.getPosition().x = startPosition.x + this.ball.getWidth() * this.ball.getSize();
                this.ball.getPosition().y = startPosition.y + this.ball.getHeight() * this.ball.getSize();
                this.ball.setGamePlayManager(this);
                this.gameObjectManager.getBalls().add(this.ball);
                this.ball.setExist(true);
                this.gameView.playSound("BallShot.wav", false);
            }
        }
    }

    protected void generateHealth() {
        this.gameObjectManager.getPlayerLives().clear();
        if (this.gameObjectManager.getPlayerLives().size() <= playerLive.getTotalLive()) {
            playerLive.manipulateTotalLive(3);
            if (this.gameObjectManager.getPlayerLives().isEmpty()) {
                playerLive.setLive(this.level.playerLive);
            }
            int live = playerLive.getLive();
            for (int x = 0; x < playerLive.getTotalLive(); x++) {
                this.gameObjectManager.getPlayerLives().add(playerLive);
            }
            int liveHelp = 0;
            for (int y = 0; y < liveHelp; y++) {
                live += 1;
                playerLive.setLive(live);
            }
        }
    }

    /**
     * Sounds for Ball when colliding with different objects
     *
     * @param object object to collide with
     */
    public void ballSound(Object object) {
        if (object.getClass() == GameBorderLeft.class) {
            this.gameView.playSound("BallLost.wav", false);
        }
        if (object.getClass() == GameBorderTop.class) {
            this.gameView.playSound("BallBounce.wav", false);
        }
        if (object.getClass() == GameBorderBottom.class) {
            this.gameView.playSound("BallBounce.wav", false);
        }
        if (object.getClass() == GameBorderRight.class) {
            this.gameView.playSound("BallBounce.wav", false);
        }
        if (object.getClass() == Bat.class) {
            this.gameView.playSound("BallBatBounce.wav", false);
        }
    }

    /**
     * @param object as Object to be deleted
     */
    public void destroy(Object object) {
        if (object.getClass() == Brick.class) {
            this.gameObjectManager.getBricks().remove(object);
            this.gameView.playSound("BallHitBrick.wav", false);
        }
        if (object.getClass() == Face.class) {
            this.gameObjectManager.getFaces().remove(object);
            this.gameView.playSound("BallHitAlien.wav", false);
        }
        if (object.getClass() == Astronaut.class) {
            this.gameObjectManager.getAstronauts().remove(object);
            this.gameView.playSound("BallHitAlien.wav", false);
        }
        if (object.getClass() == Bee.class) {
            this.gameObjectManager.getBees().remove(object);
            this.gameView.playSound("BallHitAlien.wav", false);
        }
        if (object.getClass() == Pinball.class) {
            this.gameObjectManager.getBalls().remove(object);
        }
        this.managePoints(object);
    }

    public void managePoints(Object object) {
        if (object.getClass() == Brick.class) {
            this.gameObjectManager.getScore().getFirst().plusScore(200);
        }
        if (object.getClass() == Face.class) {
            this.gameObjectManager.getScore().getFirst().plusScore(100);
        }

        if (object.getClass() == Astronaut.class) {
            this.gameObjectManager.getScore().getFirst().plusScore(100);
        }

        if (object.getClass() == Bee.class) {
            this.gameObjectManager.getScore().getFirst().plusScore(400);
        }

        if (object.getClass() == Pinball.class) {
            this.gameObjectManager.getScore().getFirst().minusScore(200);
        }
    }

    protected void spawnAndDestroyFace() {
        Face face = new Face(this.gameView);
        boolean spawnFace = false;
        boolean destroyFace = false;
        if (this.gameView.timerExpired("SpawnFace", "GamePlayManager") /*&& gameView.timerExpired("Destroy","GamePlayManager")*/) {
            this.gameView.setTimer("SpawnFace", "GamePlayManager", 1000);
            spawnFace = !spawnFace;
        }
        if (this.gameView.timerExpired("DestroyFace", "GamePlayManager")) {
            this.gameView.setTimer("DestroyFace", "GamePlayManager", 5000);
            destroyFace = !destroyFace;
        }
        if (this.gameView.getGameTimeInMilliseconds() / 1000 == 10) {
            listHasBeenDeleted = !listHasBeenDeleted;
        }
        if (spawnFace) {
            face.setGamePlayManager(this);
            this.gameObjectManager.getFaces().add(face);
        }
        if (destroyFace && !this.gameObjectManager.getFaces().isEmpty()) {
            this.gameObjectManager.getFaces().remove(this.random.nextInt(this.gameObjectManager.getFaces().size()));
        }
        if (listHasBeenDeleted) {
            this.gameObjectManager.getFaces().clear();
        }
        if (this.gameObjectManager.getFaces().size() > 30) {
            this.gameObjectManager.getFaces().removeFirst();
        }
        if (!this.gameObjectManager.getFaces().isEmpty()) {
        }
        face.setFaceTimer(this.gameObjectManager.getFaces().size());
    }

    protected void spawnBrick() {
        if (this.gameObjectManager.getBricks().isEmpty()) {
            //this.brickSpawned = false;
        }
        if (!this.brickSpawned) {
            for (Position position : this.level.brickPositions) {
                Brick brick = new Brick(this.gameView);
                brick.getPosition().x = position.x;
                brick.getPosition().y = position.y;
                brick.setGamePlayManager(this);
                this.gameObjectManager.getBricks().add(brick);
            }
        }
        this.brickSpawned = true;
    }

    protected void updateGamePlay() {
        this.gameObjectManager.getBricks().clear();
        this.gameObjectManager.getItems().clear();
        this.gameObjectManager.getAlienObjects().clear();
        this.gameObjectManager.getPlayerLives().clear();
        // if (this.brickSpawned && this.gameObjectManager.getBricks().isEmpty()) {
        //nextGame();
        /* } else*/
        if (this.playerLive.getLive() <= 0) {
            if (levelManager.hasNextLevel()) {
                nextLevel();

                //} else {
                //nextGame();
            }
        }
    }

    /**
     * When Bat touches World limit, moves whole world, so it seems not ot move
     *
     * @param speedInPixel the speed of the Bat
     */
    public void batMovingUp(double speedInPixel) {
        this.gameObjectManager.moveWorld(0, speedInPixel);
    }

    /**
     * When Bat touches World limit, moves whole world, so it seems not ot move
     *
     * @param speedInPixel the speed of the Bat
     */
    public void batMovingDown(double speedInPixel) {
        this.gameObjectManager.moveWorld(0, -speedInPixel);
    }

    /**
     * When Bat touches World limit, moves whole world, so it seems not ot move
     *
     * @param speedInPixel the speed of the Bat
     */
    public void batMovingLeft(double speedInPixel) {
        this.gameObjectManager.moveWorld(speedInPixel, 0);
    }

    /**
     * When Bat touches World limit, moves whole world, so it seems not ot move
     *
     * @param speedInPixel the speed of the Bat
     */
    public void batMovingRight(double speedInPixel) {
        this.gameObjectManager.moveWorld(-speedInPixel, 0);
    }
}
