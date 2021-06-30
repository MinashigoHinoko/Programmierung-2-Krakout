package de.thdeg.amuri.krakout.graphics.staticobject.screen;

import de.thdeg.amuri.krakout.gameview.GameView;

/**
 * Displays an end screen with points that enables the player to play another game. The player is also able to end the
 * game.
 */
public class EndScreen {
    private final GameView gameView;

    /**
     * Creates the screen;
     *
     * @param gameView GameView to show the screen on.
     */
    public EndScreen(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Shows the screen.
     */
    public void showEndScreen(int score) {
        gameView.stopAllSounds();
        gameView.showEndScreen("You have " + score + " Points!");
    }
}
