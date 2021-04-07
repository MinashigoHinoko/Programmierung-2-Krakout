package krakout;

import krakout.gameview.GameLoopManager;

public class Start {

    public static void main(String[] args) {
        /*
          Initialising GameLoopManager to have a Game Screen
         */
        GameLoopManager gameLoopManager = new GameLoopManager();
        gameLoopManager.startGame();

    }
}
