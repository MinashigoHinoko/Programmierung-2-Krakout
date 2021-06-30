package de.thdeg.amuri.krakout.game.utilities;


import de.thdeg.amuri.krakout.movement.Position;

import java.util.LinkedList;

/**
 * A level of the game.
 */
public class Level {
    /**
     * Name of the level.
     */
    public String name;
    /**
     * Number of Bricks in the level.
     */
    public int numberOfBricks;
    /**
     * Number of Bricks that have item.
     */
    public int numberOfItems;
    /**
     * Number of Aliens that will spawn in the level.
     */
    public int numberOfAliens;
    /**
     * Amount of Live the player has in the level.
     */
    public int playerLive;
    /**
     * Positions of all the Bricks that need to be spawned.
     */
    public LinkedList<Position> brickPositions;

    /**
     * Creates a level
     *
     * @param name           Name of the level.
     * @param numberOfBricks Number of Bricks in the level.
     * @param numberOfItems  Number of Bricks that have a Item.
     * @param numberOfAliens Number of Aliens that will spawn in the level
     * @param playerLive     Amount of Live the player has in the level.
     */
    public Level(String name, int numberOfBricks, int numberOfItems, int numberOfAliens, int playerLive) {
        this.name = name;
        this.numberOfBricks = numberOfBricks;
        this.numberOfItems = numberOfItems;
        this.numberOfAliens = numberOfAliens;
        this.playerLive = playerLive;

    }

    /**
     * @param brickPositions all positions of the brick
     */
    public void setBrickPositions(LinkedList<Position> brickPositions) {
        this.brickPositions = brickPositions;
    }
}