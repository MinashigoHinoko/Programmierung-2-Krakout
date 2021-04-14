package de.thdeg.amuri.krakout.game;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.movement.Position;

public class GameObject {
    private  GameView gameview;
    private  Position position;
    private int size;
    private double speedInPixel;
    private int rotation;
    private double width;
    private double height;

    public GameObject(GameView gameview) {
        this.gameview = gameview;
        this.position = new Position(0,0);
        this.size = 0;
        this.speedInPixel = 0;
        this.rotation = 0;
        this.width = 0;
        this.height = 0;
    }
    public void updatePosition(){}
    public void addToCanvas(){}

    public GameObject(Position position) {
        this.position = position;
    }
}

