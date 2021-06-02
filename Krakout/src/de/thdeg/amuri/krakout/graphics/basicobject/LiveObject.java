package de.thdeg.amuri.krakout.graphics.basicobject;

import de.thdeg.amuri.krakout.gameview.GameView;

/**
 * Acts as a Parent for classes that need a live system
 */
public abstract class LiveObject extends GameObject {
    protected boolean hit;
    protected int totalLive;
    protected int live;

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    protected LiveObject(GameView gameView) {
        super(gameView);
        this.hit = false;
        this.totalLive = 3;
        this.live = 3;
    }

    /**
     * @param live as the current Live of the Object
     */
    public void setLive(int live) {
        this.live = live;
    }
    /**
     * Manipulate life to increase or decrease values
     *
     *
     *
     * @param live is the value to be removed
     */
    public void manipulateLive(int live) {
        this.live += live;
    }
    /**
     * Get Live of Object to determine how often it has to be hit, to be destroyed
     *
     * @return remaining Live of Brick
     */
    public int getLive(){
        return this.live;
    }

    /**
     * Get Total Live of Object to determine how many Live it should have
     *
     * @return Total Live of Brick
     */
    public int getTotalLive() {
        return this.totalLive;
    }
    /**
     * Manipulate maximum life to increase or decrease values
     *
     * @param totalLive is the value to be removed
     */
    public void manipulateTotalLive(int totalLive) {
        this.totalLive += totalLive;
    }

    public void manipulateLive(int live) {
        this.live += live;
    }
    public int getLive(){
        return this.live;
    }

    public int getOldLive() {
        return this.oldLive;
    }
    public void manipulateOldLive(int oldLive) {
        this.oldLive += oldLive;
    }

    /**
     * determines if got hit
     */
    public void hasHit() {
        this.hit = true;
    }
}
