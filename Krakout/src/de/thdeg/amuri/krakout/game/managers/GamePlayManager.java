package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.moving.alien.Face;

import java.util.Random;

/**
 * Manager of the Game process
 */
public class GamePlayManager {
    private final GameView gameView;
    private final GameObjectManager gameObjectManager;
    private Face face;
    private boolean spawnFace;
    private boolean destroyFace;
    private boolean listHasBeenDeleted;
    private Random random;

    protected GamePlayManager(GameView gameView, GameObjectManager gameObjectManager) {
        this.gameView = gameView;
        this.gameObjectManager = gameObjectManager;
        this.random = new Random();
        this.face = new Face(gameView);
    }
    protected void spawnAndDestroyFace(){
        this.spawnFace = false;
        this.destroyFace = false;
        this.listHasBeenDeleted =false;
        if (this.gameView.timerExpired("Destroy","GamePlayManager")){
            this.gameView.setTimer("Destroy","GamePlayManager",5000);
            this.destroyFace=!this.destroyFace;
        }
        if(gameView.timerExpired("Spawn","GamePlayManager")){
            gameView.setTimer("Spawn","GamePlayManager",1000);
            this.spawnFace = !this.spawnFace;
        }
        if(gameView.getGameTimeInMilliseconds()==10000){
            this.listHasBeenDeleted = !this.listHasBeenDeleted;
        }
        if(this.spawnFace){
            this.gameObjectManager.getFaces().add(face);
        }
        if(this.destroyFace &&!this.gameObjectManager.getFaces().isEmpty() ){
                this.gameObjectManager.getFaces().remove(random.nextInt(gameObjectManager.getFaces().size()));
        }
        if(this.listHasBeenDeleted){
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
