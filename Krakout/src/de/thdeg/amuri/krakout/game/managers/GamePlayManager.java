package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.graphics.moving.alien.Face;
import de.thdeg.amuri.krakout.movement.Position;

import java.util.Random;

/**
 * Manager of the Game process
 */
public class GamePlayManager {
    private final GameView gameView;
    private final GameObjectManager gameObjectManager;
    private Pinball ball;
    private Face face;
    private Random random;

    protected GamePlayManager(GameView gameView, GameObjectManager gameObjectManager) {
        this.gameView = gameView;
        this.gameObjectManager = gameObjectManager;
        this.random = new Random();
        this.gameObjectManager.getBat().setGamePlayManager(this);
    }
    public void shootPinball(Position startPosition){
        boolean ballCoolDown = false;
        if(this.gameObjectManager.getBalls().isEmpty()){
            this.ball = new Pinball(this.gameView);
            this.ball.getPosition().x = startPosition.x + this.ball.getWidth() * this.ball.getSize();
            this.ball.getPosition().y = startPosition.y;
            this.gameObjectManager.getBalls().add(this.ball);
        }
        if(this.gameView.timerExpired("ballCoolDown","GamePlayManager")) {
            this.gameView.setTimer("BallCoolDown", "GamePlayManager", 300);
            ballCoolDown = !ballCoolDown;
        }
        if(!ballCoolDown){
            this.ball = new Pinball(this.gameView);
            this.ball.getPosition().x = startPosition.x + this.ball.getWidth() * this.ball.getSize();
            this.ball.getPosition().y = startPosition.y;
            this.gameObjectManager.getBalls().add(this.ball);
        }
    }
    public void destroy(Pinball ball){
        this.gameObjectManager.getBalls().remove(ball);
    }

    protected void spawnAndDestroyFace(){
        this.face = new Face(this.gameView);
        boolean spawnFace = false;
        boolean destroyFace = false;
        boolean listHasBeenDeleted =false;
        if(gameView.timerExpired("Spawn","GamePlayManager") /*&& gameView.timerExpired("Destroy","GamePlayManager")*/){
            gameView.setTimer("Spawn","GamePlayManager",1000);
            spawnFace = !spawnFace;
        }
        if (this.gameView.timerExpired("Destroy","GamePlayManager")){
            this.gameView.setTimer("Destroy","GamePlayManager",5000);
            destroyFace=!destroyFace;
        }
        if(gameView.getGameTimeInMilliseconds()==10000){
            listHasBeenDeleted = !listHasBeenDeleted;
        }
        if(spawnFace){
            this.gameObjectManager.getFaces().add(face);
        }
        if(destroyFace &&!this.gameObjectManager.getFaces().isEmpty() ){
                this.gameObjectManager.getFaces().remove(random.nextInt(gameObjectManager.getFaces().size()));
        }
        if(listHasBeenDeleted){
            this.gameObjectManager.getFaces().clear();
        }
        if (this.gameObjectManager.getFaces().size()>30){
            this.gameObjectManager.getFaces().removeFirst();
        }
    }
    protected void updateGamePlay() {
        spawnAndDestroyFace();
    }
}
