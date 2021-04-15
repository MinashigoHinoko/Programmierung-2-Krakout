package de.thdeg.amuri.krakout.game.nonplayerobject;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.movement.Position;

/**
 * Super of {@link Pinball}, {@link Background}, {@link Brick} and {@link Item}
 * acts as extension of these Classes to include the shared Informations
 */
  class GameObject {
    protected final  GameView gameView;
    protected  Position position;
    protected double size;
    protected double speedInPixel;
    protected double rotation;
    protected int width;
    protected int height;

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     * @param gameView this is for Initialising the game object
     */
     GameObject(GameView gameView) {
        this.gameView = gameView;
        this.position = new Position(0,0);
        this.size = 0;
        this.speedInPixel = 0;
        this.rotation = 0;
        this.width = 0;
        this.height = 0;
    }
    /**
     * Updates Visual Movement
     */
    public void updatePosition(){}
    /**
     * Draws to the canvas.
     */
    public void addToCanvas(){}

    /**
     * Gets the Position of the game object, is used for adjusting the Position of a game object to another
     * @return position of the game object
     * @see Position
     */
    public Position getPosition() {
        return position;
    }
}

