package krakout.gameview;
import java.awt.Color;
public class GameLoopManager {
    private GameView gameView;

    public GameLoopManager() {
        this.gameView = new GameView();
        this.gameView.setWindowTitle("Krakout");
        this.gameView.setStatusText("Amir(mHiko), Amuri - Java Programmierung SS 2021");
        this.gameView.setWindowIcon("Target.png");
    }

    public void startGame() {
        gameView.addTextToCanvas("Oben links",0,0,18,Color.YELLOW,0);
        gameView.addTextToCanvas("Unten rechts",744,522,18,Color.YELLOW,0);
        gameView.addLineToCanvas(0,30,960,30,3,Color.RED);
        gameView.addLineToCanvas(0,512,960,512,3,Color.RED);
        gameView.addOvalToCanvas(0,80,60,70,5,true,Color.BLUE);
        gameView.addOvalToCanvas(220,80,250,70,10,false,Color.GREEN);
        gameView.addRectangleToCanvas(440,50,130,70,5,false,Color.YELLOW);
        gameView.addPolyLineToCanvas(new double[]{610, 697.5, 785, 872.5,950},new double[]{80,150,80,150,80},8,Color.GREEN);
        gameView.printCanvas();

    }
}
