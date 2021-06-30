package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.game.utilities.Level;
import de.thdeg.amuri.krakout.game.utilities.Player;
import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Bat;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.moving.alien.Astronaut;
import de.thdeg.amuri.krakout.graphics.moving.alien.*;
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
    private boolean levelOver;
    private Player player;
    private boolean gameOver;
    private boolean generateWorld;
    private int gameSound;
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
        gameSound = this.gameView.playSound("GameSound.wav", true);
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
        for (int i = 1; i <= this.level.numberOfBricks; i++) {
            int x = 450;
            int y = 300;
            if (i != 0) {
                x += 23 * i;
                y += 0 * i;
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
            endScreen.showEndScreen(player.score);
            this.initializeGame();
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
            if (ballCall && this.gameObjectManager.getBalls().size() < 1) {
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
            this.playerLive.manipulateTotalLive(0);
            if (this.gameObjectManager.getPlayerLives().isEmpty()) {
                this.playerLive.setLive(this.level.playerLive);
            }
            int live = playerLive.getLive();
            for (int x = 0; x < playerLive.getTotalLive(); x++) {
                this.gameObjectManager.getPlayerLives().add(playerLive);
            }
            int liveHelp = 0;
            for (int y = 0; y < liveHelp; y++) {
                live += 1;
                this.playerLive.setLive(live);
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
        if (object.getClass() == BeeHive.class) {
            this.gameObjectManager.getBeeHives().remove(object);
            this.gameView.playSound("BallHitAlien.wav", false);
        }
        if (object.getClass() == Bee.class) {
            this.gameObjectManager.getBees().remove(object);
            this.gameView.playSound("BallHitAlien.wav", false);
        }
        if (object.getClass() == Pinball.class) {
            this.gameObjectManager.getBalls().remove(object);
            this.level.playerLive -= 1;
            this.gameObjectManager.getBat().resetBat(false);
        }
        this.managePoints(object);
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

        if (object.getClass() == Astronaut.class) {
            this.gameObjectManager.getScore().getFirst().plusScore(100);
        }
        if (object.getClass() == BeeHive.class) {
            this.gameObjectManager.getScore().getFirst().plusScore(600);
        }
        if (object.getClass() == Bee.class) {
            this.gameObjectManager.getScore().getFirst().plusScore(400);
        }

        if (object.getClass() == Pinball.class) {
            this.gameObjectManager.getScore().getFirst().minusScore(200);
        }
        this.player.score = this.gameObjectManager.getScore().getFirst().getScore();
    }

    protected void spawnAndDestroyFace() {
        Face face = new Face(this.gameView);
        boolean spawn = false;
        boolean destroy = false;
        if (this.gameView.timerExpired("SpawnFace", "GamePlayManager") /*&& gameView.timerExpired("Destroy","GamePlayManager")*/) {
            this.gameView.setTimer("SpawnFace", "GamePlayManager", 1000);
            spawn = !spawn;
        }
        if (this.gameView.timerExpired("DestroyFace", "GamePlayManager")) {
            this.gameView.setTimer("DestroyFace", "GamePlayManager", 5000);
            destroy = !destroy;
        }
        if (this.gameView.getGameTimeInMilliseconds() / 1000 == 10) {
            listHasBeenDeleted = !listHasBeenDeleted;
        }
        if (spawn) {
            if (this.gameObjectManager.getFaces().size() != 5){
                face.setGamePlayManager(this);
            this.gameObjectManager.getFaces().add(face);
        }
        }
        if (destroy && !this.gameObjectManager.getFaces().isEmpty()) {
            this.gameObjectManager.getFaces().remove(this.random.nextInt(this.gameObjectManager.getFaces().size()));
        }
        if (listHasBeenDeleted) {
            this.gameObjectManager.getFaces().clear();
        }else if (this.gameObjectManager.getFaces().size() > 6) {
            this.gameObjectManager.getFaces().removeFirst();
        }
        face.setTimer(this.gameObjectManager.getFaces().size());
    }
    protected void spawnAndDestroyHive() {
        BeeHive hive = new BeeHive(this.gameView);
        boolean spawn = false;
        boolean destroy = false;
        if (this.gameView.timerExpired("SpawnHive", "GamePlayManager") /*&& gameView.timerExpired("Destroy","GamePlayManager")*/) {
            this.gameView.setTimer("SpawnHive", "GamePlayManager", 1000);
            spawn = !spawn;
        }
        if (this.gameView.timerExpired("DestroyHive", "GamePlayManager")) {
            this.gameView.setTimer("DestroyHive", "GamePlayManager", 5000);
            destroy = !destroy;
        }
        if (this.gameView.getGameTimeInMilliseconds() / 1000 == 10) {
            listHasBeenDeleted = !listHasBeenDeleted;
        }
        if (spawn) {
            if (this.gameObjectManager.getBeeHives().size() != 2){
                hive.setGamePlayManager(this);
                this.gameObjectManager.getBeeHives().add(hive);
            }
        }
        if (destroy && !this.gameObjectManager.getFaces().isEmpty()) {
            this.gameObjectManager.getBeeHives().remove(this.random.nextInt(this.gameObjectManager.getBeeHives().size()));
        }
        if (listHasBeenDeleted) {
            this.gameObjectManager.getBeeHives().clear();
        }else if (this.gameObjectManager.getBeeHives().size() > 3) {
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
            this.gameObjectManager.getItems().clear();
            this.gameObjectManager.getFaces().clear();
            this.gameObjectManager.getPlayerLives().clear();
            this.gameObjectManager.getBalls().clear();
            this.gameObjectManager.getScore().clear();
            this.gameObjectManager.getScore().add(new Score(gameView));
            this.brickSpawned = false;
            this.generateWorld = true;
        }
        if (this.gameObjectManager.getScore().getFirst().getScore() > this.gameObjectManager.getScore().getFirst().getHighScore()) {
            this.highScore=this.gameObjectManager.getScore().getFirst().getScore();
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
