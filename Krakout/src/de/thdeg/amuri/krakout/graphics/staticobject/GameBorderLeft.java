package de.thdeg.amuri.krakout.graphics.staticobject;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;

import java.awt.*;

public class GameBorderLeft extends CollidableGameObject {
    public GameBorderLeft(GameView gameView) {
        super(gameView);
        this.position.x=0;
        this.position.y=56;
        this.size=1;
        this.width=5;
        this.height= GameView.HEIGHT-106;
        this.hitBox.width = (int) (this.width * this.size);
        this.hitBox.height = (int) (this.height * this.size);
    }

    @Override
    public void addToCanvas() {
    }

    @Override
    protected void updateStatus() {

    }

    @Override
    protected void updateHitBoxPosition() {
        this.hitBox.x = (int) this.position.x;
        this.hitBox.y = (int) this.position.y;
    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {

    }
}
