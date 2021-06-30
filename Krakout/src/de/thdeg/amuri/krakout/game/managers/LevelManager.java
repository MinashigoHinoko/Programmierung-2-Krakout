package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.game.utilities.Level;

import java.util.Random;

/**
 * Manages the levels of the game.
 */
class LevelManager {
    Level level;

    LevelManager(boolean difficultyIsSetToEasy) {
        Random random = new Random();
        int maxnumberofBricks = difficultyIsSetToEasy ? 11 : 22;
        int randomNumberOfBricks = 0;
        while (randomNumberOfBricks <= (maxnumberofBricks / 2)) {
            randomNumberOfBricks = random.nextInt(maxnumberofBricks);
        }
        int baseNumberOfLive = difficultyIsSetToEasy ? 3 : 2;
        this.level = new Level("   Level ∞\n\nInfinityMode", randomNumberOfBricks, baseNumberOfLive);
    }

    /**
     * @return current and only level
     */
    public Level getLevel() {
        return this.level;
    }
}
