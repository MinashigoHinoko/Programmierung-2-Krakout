package krakout.gameview;

public class GameLoopManager {
    private GameView gameView;

    public GameLoopManager() {
        this.gameView = new GameView();
        this.gameView.setWindowTitle("Krakout");
        this.gameView.setStatusText("Java Programmierung SS 2021");
        this.gameView.setWindowIcon("Target.png");
    }

    public void startGame() {

    }
}
