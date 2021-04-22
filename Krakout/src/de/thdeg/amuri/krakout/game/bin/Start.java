package de.thdeg.amuri.krakout.game.bin;

import de.thdeg.amuri.krakout.game.managers.GameLoopManager;

/**
 * Here all begins
 */
public class Start {

    public static void main(String[] args) {
        /*
          Initialising GameLoopManager to have a Game Screen
         */
        GameLoopManager gameLoopManager = new GameLoopManager();
        gameLoopManager.startGame();

    }
}
