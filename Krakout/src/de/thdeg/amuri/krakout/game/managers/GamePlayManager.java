package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.gameview.GameView;

/**
 * Manager of the Game process
 */
public class GamePlayManager {
    private final GameView gameView;
    private final GameObjectManager gameObjectManager;

    protected GamePlayManager(GameView gameView, GameObjectManager gameObjectManager) {
        this.gameView = gameView;
        this.gameObjectManager = gameObjectManager;
    }

    protected void updateGamePlay() {

    }
}
