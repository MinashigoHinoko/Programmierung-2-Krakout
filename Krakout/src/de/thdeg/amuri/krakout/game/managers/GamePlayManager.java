package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.moving.alien.Astronaut;
import de.thdeg.amuri.krakout.graphics.moving.alien.Face;
import de.thdeg.amuri.krakout.movement.Position;

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
    private boolean listHasBeenDeleted;

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
        if (this.gameView.timerExpired("BallCoolDown", "GamePlayManager")) {
            this.gameView.setTimer("BallCoolDown", "GamePlayManager", 300);
            ballCoolDown = !ballCoolDown;
        }
        if (ballCoolDown || this.gameObjectManager.getBalls().isEmpty()) {
            ballCall = !ballCall;
            if (ballCall) {
                this.ball = new Pinball(this.gameView);
                this.ball.getPosition().x = startPosition.x + this.ball.getWidth() * this.ball.getSize();
                this.ball.getPosition().y = startPosition.y;
                ball.setGamePlayManager(this);
                this.gameObjectManager.getBalls().add(this.ball);
            }
        }
    }

    protected void spawnAndDestroyAstronaut() {
        this.astronaut = new Astronaut(this.gameView);
        boolean spawnAstronaut = false;
        boolean destroyAstronaut = false;
        if (this.gameView.timerExpired("SpawnAstronaut", "GamePlayManager")) {
            this.gameView.setTimer("SpawnAstronaut", "GamePlayManager", 3000);
            spawnAstronaut = !spawnAstronaut;
        }
        if (this.gameView.timerExpired("DestroyAstronaut", "GamePlayManager")) {
            this.gameView.setTimer("DestroyAstronaut", "GamePlayManager", 8000);
            destroyAstronaut = !destroyAstronaut;
        }
        if (spawnAstronaut) {
            this.gameObjectManager.getAstronauts().add(this.astronaut);
        }
        if (destroyAstronaut && !this.gameObjectManager.getAstronauts().isEmpty()) {
            this.gameObjectManager.getAstronauts().remove(this.random.nextInt(this.gameObjectManager.getAstronauts().size()));
        }
        if (this.gameObjectManager.getAstronauts().size() > 10) {
            this.gameObjectManager.getAstronauts().removeFirst();
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
    }

    public void batMovingLeft(double speedInPixel) {
        this.gameObjectManager.moveWorld(speedInPixel, 0);
    }

    public void batMovingRight(double speedInPixel) {
        this.gameObjectManager.moveWorld(-speedInPixel, 0);
    }

    protected void updateGamePlay() {
        spawnAndDestroyFace();
        spawnAndDestroyAstronaut();
    }
}
