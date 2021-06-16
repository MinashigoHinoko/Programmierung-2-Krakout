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
    public final String name;
    /**
     * Number of Bricks in the level.
     */
    public final int numberOfBricks;
    /**
     * Number of Bricks that have item.
     */
    public final int numberOfItems;
    /**
     * Number of Aliens that will spawn in the level.
     */
    public final int numberOfAliens;
    /**
     * Amount of Live the player has in the level.
     */
    public final int playerLive;
    /**
     * Positions of all the Bricks that need to be spawned.
     */
    public final LinkedList<Position> brickPositions;

    /**
     * Creates a level
     *
     * @param name              Name of the level.
     * @param numberOfBricks    Number of Bricks in the level.
     * @param numberOfItems     Number of Bricks that have a Item.
     * @param numberOfAliens    Number of Aliens that will spawn in the level
     * @param playerLive        Amount of Live the player has in the level.
     * @param brickPositions    Positions of all th Bricks that need to be spawned.
     */
    public Level(String name, int numberOfBricks, int numberOfItems, int numberOfAliens, int playerLive, LinkedList<Position> brickPositions) {
        this.name = name;
        this.numberOfBricks = numberOfBricks;
        this.numberOfItems = numberOfItems;
        this.numberOfAliens = numberOfAliens;
        this.playerLive = playerLive;
        this.brickPositions = brickPositions;

    }
}