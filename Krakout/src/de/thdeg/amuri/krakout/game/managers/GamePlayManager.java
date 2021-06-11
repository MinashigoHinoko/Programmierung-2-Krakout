package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.moving.alien.Astronaut;
import de.thdeg.amuri.krakout.graphics.moving.alien.Face;
import de.thdeg.amuri.krakout.graphics.staticobject.PlayerLive;
import de.thdeg.amuri.krakout.movement.Position;

import java.util.ArrayList;
import java.util.Random;

/**
 * Manager of the Game process
 */
public class GamePlayManager {
    private final GameView gameView;
    private final GameObjectManager gameObjectManager;
    private final Random random;
    private Pinball ball;
    private Face face;
    private Astronaut astronaut;
    private PlayerLive playerLive;
    private boolean listHasBeenDeleted;
    public boolean destroyAstronaut = false;

    protected GamePlayManager(GameView gameView, GameObjectManager gameObjectManager) {
        this.gameView = gameView;
        this.gameObjectManager = gameObjectManager;
        this.random = new Random();
        this.gameObjectManager.getBat().setGamePlayManager(this);
        this.listHasBeenDeleted = false;
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
            if (ballCall) {
                collidableGameObjects.remove(this.ball);
                this.ball = new Pinball(this.gameView, collidableGameObjects);
                this.ball.getPosition().x = startPosition.x + this.ball.getWidth() * this.ball.getSize();
                this.ball.getPosition().y = startPosition.y;
                this.ball.setGamePlayManager(this);
                this.gameObjectManager.getBalls().add(this.ball);
                this.gameView.playSound("BallShot.wav", false);
            }
        }
    }

    public void destroyAstronaut(CollidableGameObject otherObject) {
        this.gameObjectManager.getAstronauts().remove(otherObject);
    }

    protected void spawnAndDestroyAstronaut() {
        this.astronaut = new Astronaut(this.gameView);
        boolean spawnAstronaut = false;
        if (this.gameView.timerExpired("SpawnAstronaut", "GamePlayManager")) {
            this.gameView.setTimer("SpawnAstronaut", "GamePlayManager", 3000);
            spawnAstronaut = !spawnAstronaut;
        }
        if (this.gameView.timerExpired("DestroyAstronaut", "GamePlayManager")) {
            this.gameView.setTimer("DestroyAstronaut", "GamePlayManager", 8000);
            this.destroyAstronaut = true;
        }
        if (spawnAstronaut) {
            this.gameObjectManager.getAstronauts().add(this.astronaut);
        }
        if (this.destroyAstronaut && !this.gameObjectManager.getAstronauts().isEmpty()) {
            this.gameObjectManager.getAstronauts().remove(this.random.nextInt(this.gameObjectManager.getAstronauts().size()));
            this.destroyAstronaut = false;
        }
        if (this.gameObjectManager.getAstronauts().size() > 10) {
            this.gameObjectManager.getAstronauts().removeFirst();
        }
    }

    protected void generateHealth() {
        this.playerLive = new PlayerLive(this.gameView);
        if (this.gameObjectManager.getPlayerLives().size() <= this.playerLive.getTotalLive()) {
            this.playerLive.manipulateTotalLive(2);
            if (this.gameObjectManager.getPlayerLives().isEmpty()) {
                this.playerLive.manipulateLive(0);
            }
            int live = this.playerLive.getLive();
            for (int x = 0; x < this.playerLive.getTotalLive(); x++) {
                this.gameObjectManager.getPlayerLives().add(this.playerLive);
            }
            int liveHelp = 3;
            for (int y = 0; y < liveHelp; y++) {
                live += 1;
                this.playerLive.setLive(live);
            }
        }
    }

    /**
     * @param ball removes the ball once end of map reached.
     */
    public void destroyPinball(Pinball ball) {

        if (ball.getClass() == Pinball.class) {
            this.gameObjectManager.getBalls().remove(ball);
        }
    }

    protected void spawnAndDestroyFace() {
        this.face = new Face(this.gameView);
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
            this.gameObjectManager.getFaces().add(this.face);
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

    protected void updateGamePlay() {
        generateHealth();
        //spawnAndDestroyFace();
        spawnAndDestroyAstronaut();
    }
}
