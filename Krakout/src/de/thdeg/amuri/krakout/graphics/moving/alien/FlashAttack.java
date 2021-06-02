package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidingGameObject;

import java.util.ArrayList;

/**
 * Porjectile of {@link Flash}
 */
public class FlashAttack extends CollidingGameObject {

    protected FlashAttack(GameView gameView, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, objectsToCollideWith);
    }

    @Override
    public void addToCanvas() {

    }

    @Override
    protected void updateStatus() {

    }

    @Override
    protected void updateHitBoxPosition() {

    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {

    }
}
