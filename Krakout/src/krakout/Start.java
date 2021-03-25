package krakout;

import krakout.gameview.GameLoopManager;

public class Start {

    public static void main(String[] args) {

        GameLoopManager gameLoopManager = new GameLoopManager();
        gameLoopManager.startGame();

    }
}
