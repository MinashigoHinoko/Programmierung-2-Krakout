package de.thdeg.amuri.krakout.graphics.basicobject;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.GameObject;

/**
 * Acts as a Parent for classes that need a live system
 */
public class LiveObject extends GameObject {
    protected boolean isHit;
    protected int oldLive;
    protected int live;

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected LiveObject(GameView gameView) {
        super(gameView);
        this.isHit = false;
        this.oldLive = 3;
        this.live = this.oldLive;
    }

    public void setLive(int live) {
        this.live = live;
    }

    /**
     * determines if got hit
     *
     * @param hit if true, got hit
     */
    public void setHit(boolean hit) {
        isHit = hit;
    }
}
