package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.gameview.GameView;

/**
 * This is the GameLoopManager, here we will Input what the player sees. This includes animations and the game itself
 */
public class GameLoopManager {
    private final GameView gameView;
    private final InputManager inputManager;
    private final GameObjectManager gameObjectManager;
    private final GamePlayManager gamePlayManager;
    private final int gameSound;

    /**
     * Creates the main loop
     */
    public GameLoopManager() {
        this.gameView = new GameView();
        this.gameView.setWindowTitle("Krakout");
        this.gameView.setStatusText("Amir(mHiko), Amuri - Java Programmierung SS 2021");
        this.gameView.setWindowIcon("Target.png");
        this.gameObjectManager = new GameObjectManager(gameView);
        this.inputManager = new InputManager(gameView, gameObjectManager.getBat(), gameObjectManager.getBatTopHitbox());
        this.gamePlayManager = new GamePlayManager(gameView, gameObjectManager);
        this.gameSound = gameView.playSound("GameSound.wav", true);

    }


    /**
     * Initialisation of the game and Building Game screen {@link GameView}
     */
    public void startGame() {

        while (true) { // The "Game-Loop"
            gamePlayManager.updateGamePlay();
            inputManager.updateUserInputs();
            gameObjectManager.updateGameObjects();
            gameView.printCanvas();
        }

    }
}
