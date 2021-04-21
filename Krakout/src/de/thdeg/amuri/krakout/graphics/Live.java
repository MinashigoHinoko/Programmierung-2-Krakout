package de.thdeg.amuri.krakout.graphics;

import de.thdeg.amuri.krakout.gameview.GameView;

/**
 * Acts as a Parent for classes that need a live system
 */
public class Live extends GameObject{
    protected boolean isHit;
    protected int live;
    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected Live(GameView gameView) {
        super(gameView);
        this.isHit =false;
        this.live = 3;
    }

    /**
     * determines if got hit
     * @param hit if true, got hit
     */
    public void setHit(boolean hit) {
        isHit = hit;
    }
}
