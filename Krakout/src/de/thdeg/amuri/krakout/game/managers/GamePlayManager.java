package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.game.utilities.Level;
import de.thdeg.amuri.krakout.game.utilities.Player;
import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Bat;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.moving.alien.BeeHive;
import de.thdeg.amuri.krakout.graphics.moving.alien.Face;
import de.thdeg.amuri.krakout.graphics.staticobject.*;
import de.thdeg.amuri.krakout.graphics.staticobject.screen.EndScreen;
import de.thdeg.amuri.krakout.graphics.staticobject.screen.StartScreen;
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
    private boolean listHasBeenDeleted;
    private boolean brickSpawned;
    private Level level;
    private LevelManager levelManager;
    private PlayerLive playerLive;
    private boolean generatedHealth;
    private Player player;
    private boolean gameOver;
    private boolean generateWorld;
    private int highScore;

    protected GamePlayManager(GameView gameView, GameObjectManager gameObjectManager) {
        this.gameView = gameView;
        this.gameObjectManager = gameObjectManager;
        this.random = new Random();
        this.initializeGame();
    }

    private void initializeGame() {
        this.gameObjectManager.getBat().setGamePlayManager(this);
        this.listHasBeenDeleted = false;
        this.playerLive = new PlayerLive(this.gameView);
        this.player = new Player();
        StartScreen startScreen = new StartScreen(gameView);
        startScreen.showStartScreen();
        this.levelManager = new LevelManager(startScreen.isDifficultySetToEasy());
        this.gameView.playSound("GameSound.wav", true);
        this.level = this.levelManager.getLevel();
        this.generateWorld = false;
        this.initializeLevel();
    }

    private void initializeLevel() {
        this.level = this.levelManager.getLevel();
        this.gameObjectManager.getBat().resetBat(true);
        Score score = new Score(this.gameView);
        this.gameObjectManager.getScore().add(score);
        LinkedList<Position> brickPositions = new LinkedList<>();
        int bricksNumber2 = -11;
        for (int bricksNumber = 1; bricksNumber <= this.level.numberOfBricks; bricksNumber++) {
            bricksNumber2++;
            int x = 400;
            int y = 300;

            if (bricksNumber2 >= 1) {
                if (bricksNumber2 % 3 == 0) {
                    int x2 = x;
                    x2 += 24 * bricksNumber2;
                    y += -50;
                    x = x2;
                } else {
                    x += 24 * bricksNumber2;
                    y += -0;
                }
                if (bricksNumber2 % 2 == 0) {
                    int x2 = x;
                    if (bricksNumber2 % 6 == 0) {
                        int y2 = y;
                        x2 += 24 * bricksNumber2;
                        y2 += 50;
                        x = x2;
                        y = y2;
                    } else {
                        x2 += 24 * bricksNumber2;
                        x = x2;
                    }
                } else {
                    x += 24 * bricksNumber2;
                    y += -50;
                }
            } else {
                if (bricksNumber != 0) {
                    if (bricksNumber % 3 == 0) {
                        int x2 = x;
                        x2 += 24 * bricksNumber;
                        y += 50;
                        x = x2;
                    } else {
                        x += 24 * bricksNumber;
                        y += 0;
                    }
                    if (bricksNumber % 2 == 0) {
                        if (bricksNumber % 6 == 0) {
                            int x2 = x;
                            int y2 = y;
                            x2 += 24 * bricksNumber;
                            y2 += 50;
                            x = x2;
                            y = y2;
                        } else {
                            int x2 = x;
                            int y2 = y;
                            x2 += 24 * bricksNumber;
                            y2 += 100;
                            x = x2;
                            y = y2;
                        }
                    } else {
                        x += 24 * bricksNumber;
                    }
                }
            }
            brickPositions.add(new Position(x, y));
        }
        this.level.setBrickPositions(brickPositions);
    }

    private void nextGame() {
        if (!gameOver) {
            gameView.setTimer("game", "GamePlayManager", 3000);
            gameOver = true;
            gameObjectManager.getBat().setInvisible();
            this.gameObjectManager.getBalls().clear();
            gameObjectManager.getOverlay().showMessage("Game Over!");
        }
        if (gameView.timerExpired("game", "GamePlayManager")) {
            gameOver = false;
            gameView.stopAllSounds();
            EndScreen endScreen = new EndScreen(gameView);
            endScreen.showEndScreen(this.player.score);
            this.initializeGame();
        }
    }

    /**
     * @param startPosition Shoots a ball, there can only be up to two at the same time.
     */
    public void shootPinball(Position startPosition) {
        boolean ballCoolDown = false;
        ArrayList<CollidableGameObject> collidableGameObjects = gameObjectManager.getCollidableGameObjects();
        if (this.gameView.timerExpired("BallCoolDown", "GamePlayManager")) {
            this.gameView.setTimer("BallCoolDown", "GamePlayManager", 300);
            ballCoolDown = true;
        }
        if (ballCoolDown || this.gameObjectManager.getBalls().isEmpty()) {
            if (this.gameObjectManager.getBalls().size() < 1) {
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
            if (!this.generatedHealth) {
                if (this.levelManager.level.playerLive < this.playerLive.getTotalLive()) {
                    int lifeDif = this.levelManager.level.playerLive - this.playerLive.getTotalLive();
                    this.playerLive.manipulateTotalLive(lifeDif);
                    this.generatedHealth = true;
                } else {
                    this.playerLive.manipulateTotalLive(0);
                }
            }
            if (this.gameObjectManager.getPlayerLives().isEmpty()) {
                this.playerLive.setLive(this.level.playerLive);
            }
            for (int x = 0; x < playerLive.getTotalLive(); x++) {
                this.gameObjectManager.getPlayerLives().add(playerLive);
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
            this.managePoints(object);
        }
        if (object.getClass() == Face.class) {
            this.gameObjectManager.getFaces().remove(object);
            this.gameView.playSound("BallHitAlien.wav", false);
        }
        if (object.getClass() == BeeHive.class) {
            this.gameObjectManager.getBeeHives().remove(object);
            this.gameView.playSound("BallHitAlien.wav", false);
        }
        if (object.getClass() == Pinball.class) {
            this.gameObjectManager.getBalls().remove(object);
            this.level.playerLive -= 1;
            this.gameObjectManager.getBat().resetBat(false);
            this.managePoints(object);
        }
        if (object.getClass() == Bat.class) {
            this.gameObjectManager.getBat().resetBat(true);
        }
    }

    /**
     * @param object The object, that manipulates the points
     */
    public void managePoints(Object object) {
        if (object.getClass() == Brick.class) {
            this.gameObjectManager.getScore().getFirst().plusScore(200);
        }
        if (object.getClass() == Face.class) {
            this.gameObjectManager.getScore().getFirst().plusScore(100);
        }

        if (object.getClass() == BeeHive.class) {
            this.gameObjectManager.getScore().getFirst().plusScore(600);
        }

        if (object.getClass() == Pinball.class) {
            this.gameObjectManager.getScore().getFirst().minusScore(200);
        }
        this.player.score = this.gameObjectManager.getScore().getFirst().getScore();
    }

    protected void spawnAndDestroyFace() {
        ArrayList<CollidableGameObject> collidableGameObjects = gameObjectManager.getCollidableGameObjects();
        Face face = new Face(this.gameView, collidableGameObjects);
        boolean spawn = false;
        boolean destroy = false;
        if (this.gameView.timerExpired("SpawnFace", "GamePlayManager") /*&& gameView.timerExpired("Destroy","GamePlayManager")*/) {
            this.gameView.setTimer("SpawnFace", "GamePlayManager", 1000);
            spawn = true;
        }
        if (this.gameView.timerExpired("DestroyFace", "GamePlayManager")) {
            this.gameView.setTimer("DestroyFace", "GamePlayManager", 5000);
            destroy = true;
        }
        if (this.gameView.getGameTimeInMilliseconds() / 1000 == 10) {
            listHasBeenDeleted = !listHasBeenDeleted;
        }
        if (spawn) {
            if (this.gameObjectManager.getFaces().size() != 5) {
                face.setGamePlayManager(this);
                this.gameObjectManager.getFaces().add(face);
            }
        }
        if (destroy && !this.gameObjectManager.getFaces().isEmpty()) {
            this.gameObjectManager.getFaces().remove(this.random.nextInt(this.gameObjectManager.getFaces().size()));
        }
        if (listHasBeenDeleted) {
            this.gameObjectManager.getFaces().clear();
        } else if (this.gameObjectManager.getFaces().size() > 6) {
            this.gameObjectManager.getFaces().removeFirst();
        }
        face.setTimer(this.gameObjectManager.getFaces().size());
    }

    protected void spawnAndDestroyHive() {
        ArrayList<CollidableGameObject> collidableGameObjects = gameObjectManager.getCollidableGameObjects();
        BeeHive hive = new BeeHive(this.gameView, collidableGameObjects);
        boolean spawn = false;
        boolean destroy = false;
        if (this.gameView.timerExpired("SpawnHive", "GamePlayManager") /*&& gameView.timerExpired("Destroy","GamePlayManager")*/) {
            this.gameView.setTimer("SpawnHive", "GamePlayManager", 1000);
            spawn = true;
        }
        if (this.gameView.timerExpired("DestroyHive", "GamePlayManager")) {
            this.gameView.setTimer("DestroyHive", "GamePlayManager", 5000);
            destroy = true;
        }
        if (this.gameView.getGameTimeInMilliseconds() / 1000 == 10) {
            listHasBeenDeleted = !listHasBeenDeleted;
        }
        if (spawn) {
            if (this.gameObjectManager.getBeeHives().size() != 2) {
                hive.setGamePlayManager(this);
                this.gameObjectManager.getBeeHives().add(hive);
            }
        }
        if (destroy && !this.gameObjectManager.getBeeHives().isEmpty()) {
            this.gameObjectManager.getBeeHives().remove(this.random.nextInt(this.gameObjectManager.getBeeHives().size()));
        }
        if (listHasBeenDeleted) {
            this.gameObjectManager.getBeeHives().clear();
        } else if (this.gameObjectManager.getBeeHives().size() > 3) {
            this.gameObjectManager.getBeeHives().removeFirst();
        }
        hive.setTimer(this.gameObjectManager.getBeeHives().size());
    }

    protected void spawnBrick() {
        if (this.gameObjectManager.getBricks().isEmpty()) {
            this.brickSpawned = false;
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
        if (!this.generateWorld) {
            this.gameObjectManager.getBat().resetBat(true);
            this.gameObjectManager.getBricks().clear();
            this.gameObjectManager.getFaces().clear();
            this.gameObjectManager.getPlayerLives().clear();
            this.gameObjectManager.getBalls().clear();
            this.gameObjectManager.getScore().clear();
            this.gameObjectManager.getScore().add(new Score(gameView));
            this.generatedHealth = false;
            this.brickSpawned = false;
            this.generateWorld = true;
        }
        if (this.gameObjectManager.getScore().getFirst().getScore() > this.gameObjectManager.getScore().getFirst().getHighScore()) {
            this.highScore = this.gameObjectManager.getScore().getFirst().getScore();
        } else if (this.highScore < this.gameObjectManager.getScore().getFirst().getHighScore()) {
            this.highScore = this.gameObjectManager.getScore().getFirst().getHighScore();
        }
        this.spawnBrick();
        this.spawnAndDestroyFace();
        this.spawnAndDestroyHive();
        this.generateHealth();
        this.gameObjectManager.getScore().getFirst().setHighScore(this.highScore);
        if (level.playerLive == 0) {
            nextGame();
            this.gameObjectManager.getScore().getFirst().setScore(0);
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
