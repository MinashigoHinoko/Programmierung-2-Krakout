package de.thdeg.amuri.krakout.game.utilities;


/**
 * Represents the player of the game
 */
public class Player {
    /**
     * The number of lives, the player starts with.
     */
    public static final int MAXIMUM_NUMBER_OF_LIVES = 3;
    /**
     * Current number of lives of the player.
     */
    public int lives;
    /**
     * Current score of the player.
     */
    public int score;

    /**
     * Creates a player with lives set to maximum an a score of 0.
     */
    public Player() {
        this.lives = MAXIMUM_NUMBER_OF_LIVES;
    }

}

