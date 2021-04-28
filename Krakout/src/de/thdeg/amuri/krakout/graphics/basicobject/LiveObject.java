package de.thdeg.amuri.krakout.graphics.basicobject;

import de.thdeg.amuri.krakout.gameview.GameView;

/**
 * Acts as a Parent for classes that need a live system
 */
public class LiveObject extends GameObject {
    protected boolean hit;
    protected int oldLive;
    protected int live;

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected LiveObject(GameView gameView) {
        super(gameView);
        this.hit = false;
        this.oldLive = 3;
        this.live = 3;
    }

    /**
     * @param live as the current Live of the Object
     */
    public void setLive(int live) {
        this.live = live;
    }

    /**
     * determines if got hit
     *
     */
    public void hasHit() {
        this.hit = true;
    }
}
