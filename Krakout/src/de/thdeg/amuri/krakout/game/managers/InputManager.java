package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.moving.Bat;
import de.thdeg.amuri.krakout.graphics.staticobject.PlayerLive;

import java.awt.event.KeyEvent;

/**
 * Manager of all user inputs, interacts with {@link Bat}
 */
class InputManager {
    private final GameView gameView;
    private final Bat bat;
    private final boolean diagonalMovement;


    protected InputManager(GameView gameView, Bat bat) {
        this.gameView = gameView;
        this.bat = bat;
        this.diagonalMovement = true;
        new PlayerLive(gameView);
    }

    void updateUserInputs() {
        Integer[] pressedKey = gameView.getKeyCodesOfCurrentlyPressedKeys();
        for (int keyCode : pressedKey) {
            if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
                bat.up();
            }
            if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
                bat.down();
            }
            if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
                bat.left();
            }
            if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
                bat.right();
            }
            if (keyCode == KeyEvent.VK_SPACE) {
                bat.shoot();
            }
            if (!diagonalMovement) {
                break;
            }
        }
    }
}
