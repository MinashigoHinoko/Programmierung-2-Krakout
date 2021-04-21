package de.thdeg.amuri.krakout.graphics;

import de.thdeg.amuri.krakout.gameview.GameView;

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

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
