package de.thdeg.amuri.krakout.graphics.staticobject.screen;

import de.thdeg.amuri.krakout.gameview.GameView;

/**
 * Displays a start screen that enables the player chose between "Easy" and "Standard" difficulty. The player is also
 * able to end the game.
 */
public class StartScreen {
    private final GameView gameView;
    private boolean isDifficultySetToEasy;

    /**
     * Creates the screen;
     *
     * @param gameView GameView to show the screen on.
     */
    public StartScreen(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Shows the screen.
     */
    public void showStartScreen() {
        //@formatter:off
        String title =
                        " __   __   _______  _______  __   __   ________  __    __  _______ \n" +
                        "|  | |  | |   __  ||   _   ||  | |  | |        ||  |  |  ||       |\n" +
                        "|  | |  | |  |__| ||  | |  ||  | |  | |   __   ||  |  |  ||_     _|\n" +
                        "|  |_/  | |      | |  |_|  ||  |_/  | |  |  |  ||  |  |  |  |   |  \n" +
                        "|   _  |  |   _ |  |   _   ||   _  |  |  |__|  ||  |__|  |  |   |  \n" +
                        "|  | |  | |  | | | |  | |  ||  | |  | |        ||        |  |   |  \n" +
                        "|__| |___||__| |__||__| |__||__| |___||________||________|  |___|  \n\n "+
                                "                           Infinity                                \n";

        String description =
                  "              Krakout remake by Amir(Hiko)               \n\n"
                +                                                          "\n\n\n"
                + "            Destroy the Bricks with your Ball             \n\n"
                + "               They get defended by Aliens!              \n\n\n\n\n"
                + "   Use Up and Down to move the Bat and space to shoot!   ";
        //@formatter:on
        isDifficultySetToEasy = gameView.showSimpleStartScreen(title, description);
    }

    /**
     * Returns the choice of the player.
     *
     * @return <code>true</code>, if the player has chosen "Easy", <code>false</code> otherwise.
     */
    public boolean isDifficultySetToEasy() {
        return isDifficultySetToEasy;
    }
}
